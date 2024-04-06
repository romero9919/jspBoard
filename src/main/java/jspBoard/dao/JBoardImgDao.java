package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jspBoard.dto.BDto;
import jspBoard.dto.ImgDto;

public class JBoardImgDao {
	
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet res = null;
	Connection conn;
	
    public JBoardImgDao(Connection conn) {
    	this.conn = conn;
    }
    
    //저장
    public String insertDB(ImgDto dto) {
    	String result = "";
    	int rs = 0;
    	String sql = "insert into jboard_img (ofilename, nfilename, ext, filesize, userid, imnum) "
    			+ "values (?,?,?,?,?,?)";	
    	try {  		
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, dto.getOfilename());
    		pstmt.setString(2, dto.getNfilename());
    		pstmt.setString(3, dto.getExt());
    		pstmt.setLong(4, dto.getFilesize());
    		pstmt.setString(5, dto.getUserid());
    		pstmt.setString(6, dto.getImnum());
    		//System.out.println(pstmt);
    		rs = pstmt.executeUpdate(); 	
    		
    		if(rs != 0) {
    			result = dto.getImnum();
    		}
    	}catch(SQLException e) {
    		System.out.println("에러");
    		e.printStackTrace();
    	}finally {
    		try {
    			if(pstmt != null) pstmt.close();
    		}catch(SQLException e) {}
    	}
    	
    	return result;
    }
    
    //업데이트 
    public int updateDB(int jboard_id, String imnum) {
    	int result = 0;
    	String sql = "update jboard_img set jboard_id = ? where imnum = ?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, jboard_id);
    		pstmt.setString(2, imnum);
    		
    		result = pstmt.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(pstmt != null) pstmt.close();
    		}catch(SQLException e) {}
    	}
    	return result;
    }
    
    //삭제
    public int deleteDB(String key, String val) {
    	int result = 0;
    	String sql = "delete from jboard_img where "+key+" = ?"; 
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, val);
    		result = pstmt.executeUpdate();
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(pstmt != null) pstmt.close();
    		}catch(SQLException e) {}
    	}
    	return result;
    }
    
    //삭제를 위한 select
    public ArrayList<ImgDto> selectDB(String key, String val) {
    	String where = " where " + key + " = " + val;
    	String sql = "select * from jboard_img" + where;
    	ArrayList<ImgDto> dtos = new ArrayList<>();
  
    	try {
    		 stmt = conn.createStatement();
    		 System.out.println(sql);
    		 res = stmt.executeQuery(sql);
    		 if(res.next()) {
	    		 while(res.next()) {
	    		    ImgDto dto = new ImgDto();
	    		    dto.setId(res.getInt("id"));
	    		    dto.setJboard_id(res.getInt("jboard_id"));
	    		    dto.setNfilename(res.getString("nfilename"));
	    		    dtos.add(dto);
	    		 }
    		 }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    		   if(res != null) res.close();
    		   if(stmt != null) stmt.close();
    		}catch(SQLException e) {e.printStackTrace();}   
    	}
    	
    	return dtos;
    }
}
