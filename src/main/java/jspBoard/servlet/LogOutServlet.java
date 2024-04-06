package jspBoard.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");	
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
        
		if(session != null) {
			//세션이 있으면 세션에서 사용자 정보를 제거한다.
			session.invalidate();
		}
		String scr = "<script>alert('로그아웃 되었습니다. 안녕히 가세요.');"
	   		         + "location.href='index.jsp';</script>";   
        out.println(scr);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
