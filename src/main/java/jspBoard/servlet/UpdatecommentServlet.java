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

@WebServlet("/updatecomment")
public class UpdatecommentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String userid = (String) session.getAttribute("userid");
		CDto dto = new CDto();
		dto.setUsername(request.getParameter("username"));
		dto.setComment(request.getParameter("comment"));
	    String id = request.getParameter("id");
	    String txt, jboard_id;
		int rs = 0;
		DBConnect db = new DBConnect();
		Connection conn;
        System.out.println(dto.toString());
        int newId = 0;
		try {
			conn = db.getConnection();
			JBoardCommentDao cdao = new JBoardCommentDao(conn);
			JBoardDao dao = new JBoardDao(conn);
			CDto cdto = cdao.selectDB(id);
			newId = cdto.getJboard_id();
			if(cdto.getUserid().equals(userid) || "admin".equals(userid)) {
			   	//세션아이디와 코멘트 작성한 아이디가 같으면 수정 작업을 진행한다. 또 관리자도 수정할 수 있다
				rs = cdao.updateDB(dto, id);  //코멘트 수정
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		if(rs == 0) {
			txt = "문제가 발생했습니다.";
		}else {
			txt = "수정했습니다.";
		}
		String str = "<script>alert('"+txt+"'); location.href='contents.jsp?id="+newId+"'; </script>";
		out.println(str);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
