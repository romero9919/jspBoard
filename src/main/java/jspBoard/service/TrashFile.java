package jspBoard.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardImgDao;
import jspBoard.dto.ImgDto;

public class TrashFile {
    
	private static final Logger logger = Logger.getLogger(TrashFile.class.getName());
	private ServletContext context;
	
	public TrashFile(ServletContext cont) throws SQLException, NamingException {
	   this.context = cont;
	   cleanupFiles();
	}	
	
	private void cleanupFiles() {
	   try(Connection conn = new DBConnect().getConnection()){
		   JBoardImgDao idao = new JBoardImgDao(conn);
		   ArrayList<ImgDto> iList = idao.selectDB("jboard_id", "0");
		   if(!iList.isEmpty()) {
			   for(ImgDto dto : iList) {
				   deleteFile(dto.getNfilename());  //파일 삭제
				   idao.deleteDB("nfilename", dto.getNfilename()); //db삭제
			   }
		   }
	   }catch(SQLException | NamingException e) {
		   logger.log(Level.SEVERE, "정리파일 실행 중 에러", e);   
	   }   
	}
	
	private void deleteFile(String filename) {
		//절대경로
		
		String realPath = context.getRealPath("/uploads");
		String filePath = realPath + File.separator + filename;
		File file = new File(filePath);
		//파일이 존재하는지 확인한 후, 존재하면 삭제
		if(file.exists() && !file.isDirectory()) {
			if(file.delete()) {
				logger.info("파일 삭제 성공 :"+filePath);
			}else {
				logger.info("파일 삭제 실패 :" + filePath);
			}
		}
 
	}
	
}
