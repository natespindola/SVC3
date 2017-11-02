package app.model.dao;

import core.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import app.model.entity.Exemplos.Book;

public class BookDao
{
    public ArrayList<Book> findLike(Transacao tr, String title, String author, String publisher, String isbn) throws Exception
    {
            String sql = "SELECT * FROM tb_book WHERE tb_book.title IS NOT NULL";
            if (title != null && !title.isEmpty()) {
                sql += " AND LOWER(tb_book.title) LIKE LOWER(?)";
            }
            if (author != null && !author.isEmpty()) {
                sql += " AND LOWER(tb_book.author) LIKE LOWER(?)";
            }
            if (publisher != null && !publisher.isEmpty()) {
                sql += " AND LOWER(tb_book.publisher) LIKE LOWER(?)";
            }
            if (isbn != null && !isbn.isEmpty()) {
                sql += " AND tb_book.isbn13 LIKE ?";   
            }
        try {
            Connection con = tr.obterConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            Integer index = 0;
            if (title != null && !title.isEmpty()) {
                index++;
                ps.setString(index, "%" + title + "%");
            }
            if (author != null && !author.isEmpty()) {
                index++;
                ps.setString(index, "%" + author + "%");
            }
            if (publisher != null && !publisher.isEmpty()) {
                index++;
                ps.setString(index, "%" + publisher + "%");
            }
            if (isbn != null && !isbn.isEmpty()) {
                index++;
                ps.setString(index, "%" + isbn + "%");
            }
            ResultSet rs = ps.executeQuery();
            ArrayList<Book> results = new ArrayList<Book>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setIsbn10(rs.getString("isbn10"));
                book.setIsbn13(rs.getString("isbn13"));

                results.add(book);
            }
            rs.close();
            ps.close();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Book findById(Transacao tr, String idBook) throws Exception
    {
            String sql = "SELECT * FROM tb_book WHERE `id` = ?";
            
        try {
            Connection con = tr.obterConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idBook);
            ResultSet rs = ps.executeQuery();
            rs.next();
            
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setIsbn10(rs.getString("isbn10"));
                book.setIsbn13(rs.getString("isbn13"));

            rs.close();
            ps.close();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void main(String[] arg){
        
    }
    
    public void add(Book entity, Transacao tr) throws Exception
    {
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO tb_book (title, author, publisher, isbn10, isbn13) VALUE (?, ?, ?, ? , ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getTitle());
        ps.setString(2, entity.getAuthor());
        ps.setString(3, entity.getPublisher());
        ps.setString(4, entity.getIsbn10());
        ps.setString(5, entity.getIsbn13());
        int result = ps.executeUpdate();
    }

    public long findLastInsertId(Transacao tr) throws Exception
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT id FROM tb_book ORDER BY id desc LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("id");
    }
    
    public ArrayList<Book> isBookRegistered(Book bookins, Transacao tr) throws Exception
    {
        ArrayList <String> adds = new ArrayList();
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM db_sebo_virtual.tb_book WHERE title = ? ";
        if(!bookins.getIsbn10().equals("")){
            sql += "OR (isbn10 = ?) ";
            adds.add(bookins.getIsbn10());
        }
        if(!bookins.getIsbn13().equals("")){
            sql += "OR (isbn13 = ?) ";
            adds.add(bookins.getIsbn13());
        }
        sql += ";";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, bookins.getTitle());
        
        for(int i=0; i< adds.size(); i++){
            ps.setString((i+2), adds.get(i));
        }
        
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Book> results = new ArrayList<Book>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setIsbn10(rs.getString("isbn10"));
                book.setIsbn13(rs.getString("isbn13"));

                results.add(book);
            }
            rs.close();
            ps.close();
            return results;
    }

    public void update(Book entity,String id, Transacao tr) throws Exception
    {
        
        Connection con = tr.obterConexao();
        String sql = "UPDATE `db_sebo_virtual`.`tb_book` SET `title`='?', `author`='?', `publisher`='?', `isbn10`='?', `isbn13`='?' WHERE `id`='?'";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getTitle());
        ps.setString(2, entity.getAuthor());
        ps.setString(3, entity.getPublisher());
        ps.setString(4, entity.getIsbn10());
        ps.setString(5, entity.getIsbn13());
        ps.setString(6, id);
        ResultSet rs = ps.executeQuery();
        //if (!rs.isBeforeFirst() ) {
        //    return false;
        //}
        //return true;
        
    }
    
    public String titleFromId(Transacao tr, String id) throws Exception
    {
        try {
            Connection con = tr.obterConexao();
            String sql = "SELECT tb_book.title from tb_book WHERE tb_book.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            String title = "";
            if (rs.next()) {
                title = rs.getString("title");
            }
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
