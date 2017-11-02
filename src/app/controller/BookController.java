package app.controller;

import app.model.entity.Exemplos.Book;
import app.model.dao.BookDao;
import app.model.dao.LogDao;
import app.model.entity.Exemplos.Log;
import app.model.entity.Exemplos.ErrorLog;
import app.model.helper.LogTypeEnum;
import app.model.helper.ProfileEnum;

import core.Transacao;
import core.Utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
// import java.sql.SQLException;
// import java.util.Vector;


public class BookController 
{
    
    public ArrayList<Book> search(String title, String author, String publisher, String isbn) {
        Transacao tr = new Transacao();
        try {
            tr.beginReadOnly();
            BookDao dao = new BookDao();
            ArrayList<Book> results = dao.findLike(tr, title, author, publisher, isbn);
            tr.commit();
            return results;
        } catch(Exception e) {
            System.out.println("erro ao pesquisar");
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList incluir (Book bookIns) throws Exception{

            
        Transacao tr = new Transacao();

        try {
                tr.beginReadOnly();
                BookDao dao = new BookDao();
                ArrayList<Book> posBooks = new ArrayList<Book>();   
                posBooks = dao.isBookRegistered(bookIns,tr);
                tr.commit();
                if (posBooks.isEmpty()){
                    tr.begin();
                    dao.add(bookIns,tr);
                    tr.commit();
                    Log log = new Log();
                    log.setMessage("Livro cadastrado com sucesso.");
                    long id = getLastInsert();
                    log.setRelationshipId(id);
                    log.setLogType(LogTypeEnum.LIVRO.name());
                    LogController.incluir(log);
                    bookIns.setId(id);
                    posBooks.add(bookIns);
                    return posBooks;
                }
                else {
                    return posBooks;
                }
                
            } catch(Exception e) {
                tr.rollback();
                ErrorLog error = new ErrorLog();
                error.setAction("incluir");
                error.setController("Livro");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro ao incluir " + bookIns.getTitle());
                ErrorLogController.incluir(error);
            }
        return null;
            
        } // incluir livro novo, funcionando naquelas duplicado
    
    public Book searchById(String idBook) {
        Transacao tr = new Transacao();
        try {
            tr.beginReadOnly();
            BookDao dao = new BookDao();
            Book results = dao.findById(tr, idBook);
            tr.commit();
            return results;
        } catch(Exception e) {
            System.out.println("erro ao pesquisar");
            e.printStackTrace();
            return null;
        }
    }
    
    
    private long getLastInsert() throws Exception
        {
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                BookDao dao = new BookDao();
                long u = dao.findLastInsertId(tr);
                tr.commit();
                return u;
            } catch (Exception e) {
                tr.rollback();
                System.out.println("erro ao buscar");
                e.printStackTrace();
            }
            return -1;
        }//retorna id do ultimo livro adicionado, funcionando

    
    public ArrayList<Book> isBookRegistered(Book book) {
            Transacao tr = new Transacao();
            try {
                tr.beginReadOnly();
                BookDao dao = new BookDao();
                ArrayList<Book> results = dao.isBookRegistered(book,tr);
                
                tr.commit();
                return results;
            } catch(Exception e) {
                System.out.println("erro ao pesquisar livro" + book.getTitle());
                e.printStackTrace();
                return null;
            }
        }//retorna lista com livros que tem o mesmo titulo ou isbn, funcionando

    public void incluirConflito (Book book) throws Exception{
        Transacao tr = new Transacao();

        try {
                tr.begin();
                BookDao dao = new BookDao();
                dao.add(book,tr);
                tr.commit();
                Log log = new Log();
                log.setMessage("Livro cadastrado com sucesso.");
                log.setRelationshipId(getLastInsert());
                log.setLogType(LogTypeEnum.LIVRO.name());
                LogController.incluir(log);
            } catch(Exception e) {
                tr.rollback();
                ErrorLog error = new ErrorLog();
                error.setAction("incluir");
                error.setController("Livro");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro ao incluir " + book.getTitle());
                ErrorLogController.incluir(error);
            }
       
         
    }//inclui um livro novo, sem verificar se o mesmo ja existe, funcionando

    
//    public  static void main(String[] args)
//    {
//        BookDao dao = new BookDao();
//        Book entity = new Book();
//        Transacao tr = new Transacao();
//            try{
//            tr.begin();
//            //entity.setId(5);
//            entity.setTitle("Anamarak uma historia");
//            entity.setAuthor("Pedro Rosa");
//            entity.setPublisher("casa das editoras");
//            entity.setIsbn10("8581403379");
//            entity.setIsbn13("9788581303079");
//            
//             // System.out.println(entity.getTitle() + entity.getAuthor() + entity.getPublisher());
//            
////            
//            BookController bc = new BookController();
////            bc.incluirConflito(entity);
//            boolean conflito = false;
//            //conflito = bc.incluir(entity);
//            if (conflito){
//               System.out.println("sim");
//            }
//            else{
//               System.out.println("nope");
//            }
//
//    
//            ArrayList<Book> results = bc.isBookRegistered(entity);
//            System.out.println(bc.getLastInsert());
//            //ArrayList<Book> results = dao.isBookRegistered(entity,tr);
//            System.out.println(results);
//        
//            for (Book book : results) {        
//
//            System.out.println(book.getId());
//            System.out.println(book.getTitle());
//            System.out.println(book.getAuthor());
//            System.out.println(book.getPublisher());
//            System.out.println(book.getIsbn10());
//            System.out.println(book.getIsbn13());
//            }
//
//            
//            tr.commit();
//        
//        } catch(Exception e) {
//                System.out.println("erro");
//                e.printStackTrace();}       
//    }
    
    public String titulo(String id) throws Exception
    {
        Transacao tr = new Transacao();
        try {
            BookDao dao = new BookDao();
            tr.beginReadOnly();
            String titulo = dao.titleFromId(tr, id);
            tr.commit();
            return titulo;
        }catch(Exception e){
            System.out.println("erro ao encontrar nome do livro");
            e.printStackTrace();
            return null;
        }
    }
    
    public Book BookById(String id) throws Exception
    {
        Transacao tr = new Transacao();
        Book book = new Book();
        try {
            BookDao dao = new BookDao();
            tr.beginReadOnly();
            book = dao.findById(tr, id);
            tr.commit();
            return book;
        }catch(Exception e){
            System.out.println("erro ao encontrar  livro");
            e.printStackTrace();
            return null;
        }
    }

}
