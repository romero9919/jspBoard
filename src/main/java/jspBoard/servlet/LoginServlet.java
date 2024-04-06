package jspBoard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspBoard.dao.DBConnect;
import jspBoard.dao.MembersDao;
import jspBoard.dto.MDto;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Connection conn = null;   
	DBConnect db = new DBConnect();
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");	
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String[] rid = request.getParameterValues("rid");
        String uid = request.getParameter("uid");
        String upass= request.getParameter("upass");
        
        if(rid != null) {
        	Cookie remId = new Cookie("uid", uid);
        	response.addCookie(remId);
        }  
        
        try {
			conn = db.getConnection();
			MembersDao dao = new MembersDao(conn);
			MDto mdto = dao.login(uid, upass);
			if(mdto.getId() != 0 ) {
		       //로그인 성공  , 세션 객체 생성
			  HttpSession session = request.getSession();
			  session.setAttribute("mid", mdto.getId());
			  session.setAttribute("userid", mdto.getUserid());
			  session.setAttribute("useremail", mdto.getUseremail());
			  session.setAttribute("username", mdto.getUsername());
			  session.setAttribute("role", mdto.getRole()); 
			  response.sendRedirect("index.jsp");
			}else {
		       //로그인 실패
			   System.out.println("로그인 실패");
			   String scr = "<script>alert('아이디 또는 비밀번호가 틀렸습니다. 다시 확인하세요.');"
			   		         + "location.href='index.jsp';</script>";   
		       out.println(scr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
	}

}
