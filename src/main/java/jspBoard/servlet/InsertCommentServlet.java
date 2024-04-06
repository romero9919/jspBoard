package jspBoard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardCommentDao;
import jspBoard.dao.MembersDao;
import jspBoard.dto.BDto;
import jspBoard.dto.CDto;
import jspBoard.dto.MDto;

@WebServlet("/insertcomment")
public class InsertCommentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");	
        request.setCharacterEncoding("utf-8");
        
        DBConnect db = new DBConnect();
        PrintWriter out = response.getWriter();
        
        try {
        	HttpSession session = request.getSession();
			Connection conn = db.getConnection();
			JBoardCommentDao dao = new JBoardCommentDao(conn);
			CDto dto = new CDto();
			String link = request.getHeader("referer");			
			
			int result = 0;
			String userid = (String) session.getAttribute("userid"); 
			String txt = "";
			//String link = "contents.jsp?id="+request.getParameter("jboard_id");
			
	        dto.setJboard_id(Integer.parseInt(request.getParameter("jboard_id")));
	        dto.setUserid(userid);
	        dto.setUsername(request.getParameter("username"));
	        dto.setComment(request.getParameter("comment"));
		    
		    result = dao.insertDB(dto, Integer.parseInt(request.getParameter("chit")));
		       txt = "글을 등록했습니다.";

			
	        if(result == 0) {
	        	txt = "문제가 발생했습니다.";
	        }
	        
		    String str = "<script>alert('"+txt+"'); "
		    	       +    "location.href='"+link+"';"
		    		   + "</script>";
		    out.println(str);
	        
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
        

        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
