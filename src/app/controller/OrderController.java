/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.dao.AdressDao;
import app.model.entity.Exemplos.Order;
import app.model.entity.Exemplos.ProductInfos;
import java.util.ArrayList;
import app.model.dao.LogDao;
import app.model.dao.OrderDao;
import app.model.entity.Exemplos.ErrorLog;
import app.model.entity.Exemplos.Log;
import core.Transacao;
import app.model.entity.Exemplos.Product;
import core.Utils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;

/**
 *
 * @author pc
 */
public class OrderController {
    
    public long addOrder(String userId,String adressId) throws Exception {
        
        Transacao tr = new Transacao();
        try{
            OrderDao dao = new OrderDao();
            Order order = new Order();
            
            long userIdL = Long.valueOf(userId);
            long adressIdL = Long.valueOf(adressId);
            long orderId ;
            
            order.setUserId(userIdL);
            order.setAdressId(adressIdL);
            
            tr.begin();
            dao.add(order,tr);
            orderId = dao.getLastId(tr);
            tr.commit();
            
            return orderId;
            
            //falta os logs de pedido
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
            long zero = 0;
            return zero;
            
            //logs de erro
        }
        
    }
        
    public ArrayList<Order> viewOrders(String orderId) throws Exception{
        Transacao tr = new Transacao();
        try{
            OrderDao dao = new OrderDao();
            ArrayList<Order> results = new ArrayList<Order>();            
            
            tr.begin();
            results = dao.viewByOrder(orderId,tr);
            tr.commit();
            return results;
            
            //falta os logs de pedido
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
            return null;
            
            //logs de erro
        }
    }
    
    public Vector listarTodos() throws Exception{
        
        Vector v;
        
        OrderDao dao = new OrderDao();
        Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                v = dao.mostrarPedidos(tr);
                tr.commit();
                return v;
            }catch(Exception e){
                ErrorLog error = new ErrorLog();
                error.setAction("listarTodos");
                error.setController("Order");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro");
                ErrorLogController.incluir(error);
                }
        return null;
            
    }
    
    public Vector listarDetalhesPedidos(String id) throws Exception{
        
        Vector v;
        
        OrderDao dao = new OrderDao();
        Transacao tr = new Transacao();
        
            try{
                tr.beginReadOnly();
                v = dao.mostrarDetalhesPedido(tr, id);
                tr.commit();
                return v;
            }catch(Exception e){
                ErrorLog error = new ErrorLog();
                error.setAction("listarDetalhesPedido");
                error.setController("Order");//não tenho idea do que deveria estar aqui
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro");
                ErrorLogController.incluir(error);
                }
        return null;
            
    }
    
     
        private boolean isEmpty(String s) {
            if (null == s)
                return true;
            if (s.length() == 0)
                return true;
            return false;
        }

        private long getLastInsert() throws Exception
        {
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                AdressDao dao = new AdressDao();
                long u = dao.findLastInsertId(tr);
                tr.commit();
                return u;
            } catch (Exception e) {
                tr.rollback();
                System.out.println("erro ao buscar");
                e.printStackTrace();
            }
            return -1;
        }
        
     public String adicionarcomentario(String comentario, long orderId) throws Exception
     {
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                OrderDao dao = new OrderDao();
                String erro = dao.adicionarcomentario(tr,comentario,orderId);
                tr.commit();
                return erro;
            } catch (Exception e) {
                tr.rollback();
                return "Não foi possível adicionar o comentário.";
            }

        }
            
    
}