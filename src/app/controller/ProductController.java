package app.controller;

import app.model.entity.Exemplos.Book;
import app.model.entity.Exemplos.ProductInfos;
import java.util.ArrayList;
import app.model.dao.LogDao;
import app.model.dao.ProductDao;
import app.model.entity.Exemplos.ErrorLog;
import app.model.entity.Exemplos.Log;
import app.model.entity.Exemplos.Pedido;
import core.Transacao;
import app.model.entity.Exemplos.Product;
import core.Utils;
import java.util.ArrayList;

public class ProductController 
{
    
    public ArrayList<ProductInfos> search(String bookId, String description, String priceTypeId, String priceMax, String userId) {
        Transacao tr = new Transacao();
        try {
            tr.beginReadOnly();
            ProductDao dao = new ProductDao();
            ArrayList<ProductInfos> results = dao.findLike(tr, bookId, description, priceTypeId, priceMax, userId);
            tr.commit();
            return results;
        } catch(Exception e) {
            System.out.println("erro ao pesquisar");
            e.printStackTrace();
            return null;            
        }
    }
    
    public Product searchById(String idProduct) {
        Transacao tr = new Transacao();
        try {
            tr.beginReadOnly();
            ProductDao dao = new ProductDao();
            Product results = dao.findById(tr, idProduct);
            tr.commit();
            return results;
        } catch(Exception e) {
            System.out.println("erro ao pesquisar");
            e.printStackTrace();
            return null;            
        }
    }
    
    public ProductInfos searchCart(String idProduct) {
        Transacao tr = new Transacao();
        try {
            tr.beginReadOnly();
            ProductDao dao = new ProductDao();
            ProductInfos results = dao.findCart(tr, idProduct);
            tr.commit();
            return results;
        } catch(Exception e) {
            System.out.println("erro ao pesquisar");
            e.printStackTrace();
            return null;            
        }
    }
    
    public Product[] EncontrarTodosProdutos() throws Exception{
        Transacao tr = new Transacao();
        try{
            ProductDao productdao = new ProductDao();
            Product[] ProductArray;
            tr.beginReadOnly();
            ProductArray = productdao.findAllProducts(tr);
            tr.commit();
            return ProductArray;
        }catch(Exception e){
                System.out.println("erro ao gerar lista");
                e.printStackTrace();
                return null;            
        }
    }
    
    public String EncontrarNomeDoVendedor(Product produto) throws Exception{
        String nome;
        Transacao tr = new Transacao();
        try{
            ProductDao productdao = new ProductDao();
            tr.beginReadOnly();
            nome = productdao.findSellerName(tr, produto);
            tr.commit();
            return nome;
        }catch(Exception e){
            System.out.println("erro ao encontrar nome do vendedor");
            e.printStackTrace();
            return null;
        }
    }
    
    public String EncontrarTituloDoLivro(Product produto){
        String titulo;
        Transacao tr = new Transacao();
        try{
            ProductDao productdao = new ProductDao();
            tr.beginReadOnly();
            titulo = productdao.findBookTitle(tr, produto);
            tr.commit();
            return titulo;
        }catch(Exception e){
            System.out.println("erro ao encontrar nome do livro");
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
    public ArrayList<Pedido> ver_pedido (long id ) throws Exception {
        Transacao tr = new Transacao();
        try{
            tr.beginReadOnly();
            ProductDao productdao = new ProductDao();
            ArrayList<Pedido> p;
            p = productdao.ver_pedido(tr, id);
            tr.commit();
            return p;
        }catch(Exception e){
        tr.rollback();
        }
        return null;
    }






    public long EncontrarId(Product product){
        Transacao tr = new Transacao();
        long result;
        try{
            ProductDao productdao = new ProductDao();
            tr.beginReadOnly();
            result = productdao.findProductId(tr, product);
            tr.commit();
            return result;
        }catch(Exception e){
            System.out.println("erro ao encontrar nome do livro");
            e.printStackTrace();
            return 0;
        }
    } 
    
    public void Incluir(Product produto){
        Transacao tr = new Transacao();
        try{
            ProductDao productdao = new ProductDao();
            tr.begin();
            productdao.save(tr,produto);
            tr.commit();
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
        }
    }

    public void suspendList(ArrayList<Product> plist){
        Transacao tr = new Transacao();
        try{
            ProductDao dao = new ProductDao();
            tr.begin();
            for (int i = 0; i < plist.size(); i++) {
                Product product = new Product();
                product = plist.get(i);
                String productId = Long.toString(product.getId());
                dao.suspend(productId, tr);
            }
            tr.commit();
            //falta fazer logs
        }
        catch (Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
            
        }
    }
    
    public void suspendProduct(Product product){
        Transacao tr = new Transacao();
        try{
            ProductDao dao = new ProductDao();
            tr.begin();
            String productId = Long.toString(product.getId());
            dao.suspend(productId, tr);
            
            tr.commit();
            //falta fazer logs
        }
        catch (Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
            
        }
    }
    
}
