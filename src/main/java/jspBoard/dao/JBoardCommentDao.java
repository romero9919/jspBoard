package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspBoard.dto.CDto;

public class JBoardCommentDao {

   PreparedStatement pstmt = null;
   ResultSet rs = null;
   Connection conn = null;
   
   public JBoardCommentDao(Connection conn) {
	   this.conn = conn;
   }
	
   //select
   public ArrayList<CDto> selectDB(int jboard_id){
	   ArrayList<CDto> dtos = new ArrayList<>();
	   String sql = "select * from jboard_comment where jboard_id=?";
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, jboard_id);
		   rs = pstmt.executeQuery();
		   
		   while(rs.next()) {
		      CDto dto = new CDto();
			  dto.setId(rs.getInt("id"));
			  dto.setJboard_id(rs.getInt("jboard_id"));
			  dto.setUserid(rs.getString("userid"));
			  dto.setUsername(rs.getString("username"));
			  dto.setComment(rs.getString("comment"));
			  dto.setWdate(rs.getTimestamp("wdate"));
			  dtos.add(dto);
		   }
		   
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally {
		  try {
			  if(rs != null) rs.close();
			  if(pstmt != null) pstmt.close();
		  }catch(SQLException e) {}
	   }
	   
	   return dtos;
   }
   
   //select view
   public CDto selectDB(String id){
	   int cid = Integer.parseInt(id);
	   CDto dto = new CDto();
	   String sql = "select * from jboard_comment where id=?";
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, cid);
		   rs = pstmt.executeQuery();	   
		   if(rs.next()) {
			  dto.setId(rs.getInt("id"));
			  dto.setJboard_id(rs.getInt("jboard_id"));
			  dto.setUserid(rs.getString("userid"));
			  dto.setUsername(rs.getString("username"));
			  dto.setComment(rs.getString("comment"));
			  dto.setWdate(rs.getTimestamp("wdate"));
		   }
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally {
		  try {
			  if(rs != null) rs.close();
			  if(pstmt != null) pstmt.close();
		  }catch(SQLException e) {}
	   }
	   
	   return dto;
   }
   
   //코멘트 쓰기
   public int insertDB(CDto dto, int chit) {
	   JBoardDao dao = new JBoardDao(conn);
	   int rs = 0;
	   String sql = "insert into jboard_comment (jboard_id, userid, username, comment) "
	   		+ "values(?,?,?,?)";
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1,  dto.getJboard_id());
		   pstmt.setString(2,  dto.getUserid());
		   pstmt.setString(3, dto.getUsername());
		   pstmt.setString(4, dto.getComment());
		   rs = pstmt.executeUpdate();
		   //원글에 코멘트 숫자를 업데이트
		   dao.updateDB(dto.getJboard_id(), chit+1, "chit");
		   
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally {
			  try {
				  if(pstmt != null) pstmt.close();
			  }catch(SQLException e) {}
		   }
	   
	   return rs;
   }
   
   //코멘트 삭제
   public int deleteDB(String id) {
	   int cid = Integer.parseInt(id);
	   int rs = 0;
	   String sql = "delete from jboard_comment where id = ?";
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, cid);
		   rs = pstmt.executeUpdate();
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally {
			  try {
				  if(pstmt != null) pstmt.close();
			  }catch(SQLException e) {}
		   }	   
	   return rs;
   }
   
   //코멘트 수정 
   public int updateDB(CDto dto, String id) {
	   int cid = Integer.parseInt(id);
	   int rs = 0;
	   
	   String sql = "update jboard_comment set username = ?, comment = ? where id = ?";
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, dto.getUsername());
		   pstmt.setString(2, dto.getComment());
		   pstmt.setInt(3, cid);
		   System.out.println(pstmt);
		   rs = pstmt.executeUpdate();
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }finally {
			  try {
				  if(pstmt != null) pstmt.close();
			  }catch(SQLException e) {}
		   }	   
	   return rs;
   }
}
