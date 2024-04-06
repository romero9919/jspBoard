package jspBoard.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnect {
   public Connection conn = null;	
   private InitialContext initContext;
  
   //커넥션 하는 메소드
   public Connection getConnection() throws SQLException, NamingException{
	  if(conn == null || conn.isClosed()) {
  	    initContext = new InitialContext(); //JNDI 컨텍스트 초기화
		DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/javaboard");
		//JNDI에서 이름을 찾아옴. "java:/comp/env/" 이름찾는 데 사용되는 접두어
		conn = ds.getConnection();
		System.out.println("db접속 성공");
	  }
	  return conn;
  }
   
  //커넥션 닫는 메소드 
  public void closeConnection() {
	  try {
		  if(conn != null && !conn.isClosed()) {
			  conn.close();
			  System.out.println("db를 닫았습니다.");
		  }
	  }catch(SQLException e) {
		  e.printStackTrace();
	  }finally {
		  conn = null;
	  }
  }
   
}
