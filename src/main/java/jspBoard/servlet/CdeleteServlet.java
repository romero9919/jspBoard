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
import jspBoard.dao.JBoardDao;
import jspBoard.dto.CDto;


@WebServlet("/cdel")
public class CdeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String jboard_id = request.getParameter("jboard_id");
		int chit = Integer.parseInt(request.getParameter("chit"));
		String txt, link;
		String userid = (String) session.getAttribute("userid");
		int rs = 0;
		DBConnect db = new DBConnect();
		CDto dto = new CDto();
		Connection conn;
		
		try {
			conn = db.getConnection();
			JBoardCommentDao cdao = new JBoardCommentDao(conn);
			JBoardDao dao = new JBoardDao(conn);
			dto = cdao.selectDB(id);
			if(dto.getUserid().equals(userid) || "admin".equals(userid)) {
			   	//세션아이디와 코멘트 작성한 아이디가 같으면 삭제 작업을 진행한다. 또 관리자도 삭제할 수 있다
				rs = cdao.deleteDB(id);  //코멘트 삭제
				dao.updateDB(Integer.parseInt(jboard_id), chit-1, "chit"); //코멘트 숫자를 하나 뺀다
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		if(rs == 0) {
			txt = "문제가 발생했습니다.";
		}else {
			txt = "삭제했습니다.";
		}
		String str = "<script>alert('"+txt+"'); location.href='contents.jsp?id="+jboard_id+"'; </script>";
		out.println(str);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
