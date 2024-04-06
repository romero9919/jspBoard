package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import jspBoard.dto.BDto;

public class JBoardDao {
 
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet res = null;
	Connection conn;
		
    public JBoardDao(Connection conn) {
    	this.conn = conn;
    }
     
    public int AllSelectDB() {
    	int rs = 0;
    	String sql = "select count(*) from jboard";   	
    	try {
    		 stmt = conn.createStatement();
    		 res = stmt.executeQuery(sql);
    		    if(res.next()) {
    		    	rs = res.getInt(1);
    		 }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    		   if(res != null) res.close();
    		   if(stmt != null) stmt.close();
    		}catch(SQLException e) {e.printStackTrace();}   
    	}
    	return rs;
    }
    
    public int AllSelectDB(String sname, String svalue) {
    	int rs = 0;
    	String sql = "select count(*) from jboard where "+ sname +" LIKE ?";   	
    	try {
    		 pstmt = conn.prepareStatement(sql);
       	     pstmt.setString(1, "%"+svalue+"%");
    		 res = pstmt.executeQuery();
    		 if(res.next()) {
    		  	rs = res.getInt(1);
    		 }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    		   if(res != null) res.close();
    		   if(pstmt != null) pstmt.close();
    		}catch(SQLException e) {e.printStackTrace();}   
    	}
    	return rs;
    }
    
    
    
    //select
    public ArrayList<BDto> selectDB(int limitPage, int listCount){
    	
    	ArrayList<BDto> dtos = new ArrayList<>();
   
    	String sql = "select * from jboard order by refid desc, renum asc"
    			+ " limit ?, ?";
    	try {
    	  pstmt = conn.prepareStatement(sql);
    	  pstmt.setInt(1, limitPage);
    	  pstmt.setInt(2, listCount);
    	  	  
    	  res = pstmt.executeQuery();
    	
    	  while(res.next()) {
    		 int id = res.getInt("id");
    		 int refid = res.getInt("refid");
    		 int depth = res.getInt("depth");
    		 int renum = res.getInt("renum");
    		 String title = res.getString("title");
    		 String content = res.getString("content");
    		 String writer = res.getString("writer");
    		 String pass = res.getString("pass");
    		 String userid = res.getString("userid");
    		 int hit = res.getInt("hit");
    		 int chit = res.getInt("chit");
    		 Timestamp wdate = res.getTimestamp("wdate");
    		 String imnum = res.getString("imnum");
    		 
    		 BDto bDto = new BDto();
    		 bDto.setId(id);
    		 bDto.setRefid(refid);
    		 bDto.setDepth(depth);
    		 bDto.setRenum(renum);
    		 bDto.setTitle(title);
    		 bDto.setContent(content);
    		 bDto.setWriter(writer);
    		 bDto.setPass(pass);
    		 bDto.setUserid(userid);
    		 bDto.setHit(hit);
    		 bDto.setChit(chit);
    		 bDto.setWdate(wdate);
    		 bDto.setImnum(imnum);
    		 dtos.add(bDto);
    	  }
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    		   if(res != null) res.close();
    		   if(pstmt != null) pstmt.close();
    		}catch(SQLException e) {e.printStackTrace();}   
    	}
    	
    	return dtos;
    }
        
 //select overload
 public ArrayList<BDto> selectDB(int limitPage, int listCount, String name, String value){
    	
    	ArrayList<BDto> dtos = new ArrayList<>();
   
    	String sql = "select * from jboard where "+name+" LIKE ? order by refid desc, renum asc"
    			+ " limit ?, ?";

    	try {
    	  pstmt = conn.prepareStatement(sql);
    	  pstmt.setString(1, "%"+value+"%");
    	  pstmt.setInt(2, limitPage);
    	  pstmt.setInt(3, listCount);
    
    	  System.out.println(pstmt);	  
    	  res = pstmt.executeQuery();
    	
    	  while(res.next()) {
    		 int id = res.getInt("id");
    		 int refid = res.getInt("refid");
    		 int depth = res.getInt("depth");
    		 int renum = res.getInt("renum");
    		 String title = res.getString("title");
    		 String content = res.getString("content");
    		 String writer = res.getString("writer");
    		 String pass = res.getString("pass");
    		 String userid = res.getString("userid");
    		 int hit = res.getInt("hit");
    		 int chit = res.getInt("chit");
    		 Timestamp wdate = res.getTimestamp("wdate");
    		 String imnum = res.getString("imnum");
    		 
    		 BDto bDto = new BDto();
    		 bDto.setId(id);
    		 bDto.setRefid(refid);
    		 bDto.setDepth(depth);
    		 bDto.setRenum(renum);
    		 bDto.setTitle(title);
    		 bDto.setContent(content);
    		 bDto.setWriter(writer);
    		 bDto.setPass(pass);
    		 bDto.setUserid(userid);
    		 bDto.setHit(hit);
    		 bDto.setChit(chit);
    		 bDto.setWdate(wdate);
    		 bDto.setImnum(imnum);
    		 dtos.add(bDto);
    	  }
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    		   if(res != null) res.close();
    		   if(pstmt != null) pstmt.close();
    		}catch(SQLException e) {e.printStackTrace();}   
    	}
    	
    	return dtos;
    }
    
    //비번검증
    public int findPass(String id, String pass) { 

    	int nid = Integer.parseInt(id);
    	int result = 0;
 
    	String sql = "select id from jboard where id=? and pass=?"; 
    	BDto bDto = new BDto();
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, nid);
    		pstmt.setString(2, pass);
    		res = pstmt.executeQuery();
    		if(res.next()) {
    			result = 1;
    		}
    	}catch (SQLException e) {
			e.printStackTrace();
		} finally {
    		try {
     		   if(res != null) res.close();
     		   if(pstmt != null) pstmt.close();
     		}catch(SQLException e) {e.printStackTrace();}   
     	}
    	return result;
    }
 
    //삭제
    public int deleteDB(String id) {
    	int nid = Integer.parseInt(id);
    	int result = 0;
    	String sql = "delete from jboard where id=?";
    	try {
			pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, nid);
	        result = pstmt.executeUpdate();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
    		try {
     		   if(res != null) res.close();
     		   if(pstmt != null) pstmt.close();
     		}catch(SQLException e) {e.printStackTrace();}   
     	}
    	
    	return result;
    }
    
    public BDto viewDB(String nums) {
    	int num = Integer.parseInt(nums);
    	String sql = "select * from jboard where id=?";
		BDto bDto = new BDto();
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			res = pstmt.executeQuery();
			while(res.next()) {    		
	    		int id = res.getInt("id");
	    		int refid = res.getInt("refid");
	    		int depth = res.getInt("depth");
	    		int renum = res.getInt("renum");
	    		String title = res.getString("title");
	    		String content = res.getString("content");
	    		String writer = res.getString("writer");
	    		String pass = res.getString("pass");
	    		String userid = res.getString("userid");
	    		int hit = res.getInt("hit");
	    		int chit = res.getInt("chit");
	    		Timestamp wdate = res.getTimestamp("wdate");
	    		String imnum = res.getString("imnum");
	    		
	    		bDto.setId(id);
	    		bDto.setRefid(refid);
	    		bDto.setDepth(depth);
	    		bDto.setRenum(renum);
	    		bDto.setTitle(title);
	    		bDto.setContent(content);
	    		bDto.setWriter(writer);
	    		bDto.setPass(pass);
	    		bDto.setUserid(userid);
	    		bDto.setHit(hit);
	    		bDto.setChit(chit);
	    		bDto.setWdate(wdate);
	    		bDto.setImnum(imnum);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
    		try {
     		   if(res != null) res.close();
     		   if(pstmt != null) pstmt.close();
     		}catch(SQLException e) {e.printStackTrace();}   
     	}
    	return bDto;
    }
    
    //쓰기
    public int insertDB(BDto dto) {
        JBoardImgDao idao = new JBoardImgDao(conn);
    	int num = 0;
    	String sql = "insert into jboard ( depth,  title, content, writer, pass, userid, imnum) values (  ?, ?, ?, ?, ?, ?, ?)";
    	try {
    		pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    		pstmt.setInt(1, dto.getDepth());  	
    		pstmt.setString(2, dto.getTitle());
    		pstmt.setString(3, dto.getContent());
    		pstmt.setString(4, dto.getWriter());
            pstmt.setString(5, dto.getPass());
            if(dto.getUserid()!=null) {
            	pstmt.setString(6, dto.getUserid());
            }else {
            	pstmt.setString(6, "GUEST");
            }
            pstmt.setString(7, dto.getImnum());
            pstmt.executeUpdate();
            res = pstmt.getGeneratedKeys(); //입력 후 auto increment 값을 반환 받음 	
              if(res.next()) {
            	num = res.getInt(1);    
            	idao.updateDB(num, dto.getImnum());
             }
              
            if(dto.getDepth() == 0){
               updateDB(num, num, "refid");
            }else{
               updateDB(dto.getRefid(), dto.getRenum());
               updateDB(num, dto.getRefid(), dto.getRenum()+1);
            }   

        
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		try {
      		   if(res != null) res.close();
      		   if(pstmt != null) pstmt.close();
      		}catch(SQLException e) {e.printStackTrace();}   
      	}
    	return num;
    }
    
    //업데이트
    public int updateDB(int id, int num, String column) {
        int rs = 0;
        String sql = "update jboard set "+ column +"=? where id=?";
        
        try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, num);
            pstmt.setInt(2, id);
            //System.out.println(pstmt);
            rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
    		try {
      		   if(pstmt != null) pstmt.close();
      		}catch(SQLException e) {e.printStackTrace();}   
      	}
    
    	return rs;
    }
    
    public int updateDB(int id, int num, int renum) {
        int rs = 0;
        String sql = "update jboard set refid =? , renum = ? where id=?";
        
        try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, num);
			pstmt.setInt(2, renum);
            pstmt.setInt(3, id);
            //System.out.println(pstmt);
            rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
    		try {
      		   if(pstmt != null) pstmt.close();
      		}catch(SQLException e) {e.printStackTrace();}   
      	}
    
    	return rs;
    }
    
    
    //업데이트db 오버로드
    public int updateDB(BDto dto) {
        int rs = 0;
        String sql = "update jboard set writer = ? , pass = ?, title =?, content=? where id=?";
        
        try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getPass());
            pstmt.setString(3, dto.getTitle());
            pstmt.setString(4, dto.getContent());
            pstmt.setInt(5, dto.getId());
            rs = pstmt.executeUpdate();
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
    		try {
      		   if(pstmt != null) pstmt.close();
      		}catch(SQLException e) {e.printStackTrace();}   
      	}
    
    	return rs;
    }
    
    //글 번호증가 업데이트
    public int updateDB(int refid, int renum) {
    	String sql = "update jboard set renum = renum + 1 where refid=? and renum > ?";
    	int rs = 0;
    	try {
    	   pstmt = conn.prepareStatement(sql); 
		   pstmt.setInt(1, refid);
		   pstmt.setInt(2, renum);
           System.out.println(pstmt);
		   rs = pstmt.executeUpdate();
           
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
   		try {
     		   if(pstmt != null) pstmt.close();
     		}catch(SQLException e) {e.printStackTrace();}   
     	}
   	   return rs;   
    }
    
}
