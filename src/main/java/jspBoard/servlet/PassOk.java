package jspBoard.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardDao;

@WebServlet("/passok")
public class PassOk extends HttpServlet {   

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 String id = req.getParameter("id");
		 String cpass = req.getParameter("cpass");
		 String mode = req.getParameter("mode");
		 int result = 0;
		 DBConnect db = new DBConnect();
		 Connection conn;
		 
		 try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			result = dao.findPass(id, cpass);
		
		 } catch (SQLException | NamingException e) {
		    e.printStackTrace();
		 } finally {
		   db.closeConnection();
		 }
		 
		 if(result == 0) {
			 RequestDispatcher reqDispatcher = req.getRequestDispatcher("passno.jsp"); 
			 reqDispatcher.forward(req, res);
		 }else {
			 if("edit".equals(mode)) {
				 res.sendRedirect("edit.jsp?id="+id);
			 }else {
				 res.sendRedirect("del?id="+id+"&cpass="+cpass);
			 }
		 }
		 
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
