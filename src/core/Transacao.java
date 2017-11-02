/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Connection;

/**
 *
 * @author Flavio
 */
public class Transacao {
    private ConnectionFactory _factory =  new ConnectionFactory();
    private Connection _conexao = null;
  private boolean _readOnly = false;

  public void begin() throws Exception{
      _conexao = _factory.getConnection();
      _conexao.setAutoCommit(false);
	  _readOnly = false;
  } // begin

  public void beginReadOnly() throws Exception{
      _conexao = _factory.getConnection();
	  _readOnly = true;
  } // begin

  public void commit() throws Exception {
      if ( !_readOnly) {
         _conexao.commit();
	  }
	  _conexao.close();
  } // commit

  public void rollback() throws Exception {
      if ( !_readOnly) {
         _conexao.rollback();
	  }
	  _conexao.close();
  } // rollback

  public Connection obterConexao() {
      return _conexao;
  } // obterConexao

} // Transacao
