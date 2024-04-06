package jspBoard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardCommentDao;
import jspBoard.dao.JBoardDao;
import jspBoard.dto.BDto;
import jspBoard.dto.CDto;

public class DbWorks { 
	private int limitPage;
	private int listCount;
	private String sname;
	private String svalue;
	private Connection conn;
	private DBConnect db = new DBConnect();
	private String id;
	
	public DbWorks() {}
	public DbWorks(int limitPage, int listCount, String sname, String svalue) {
		this.limitPage = limitPage;
		this.listCount = listCount;
		this.sname = sname;
		this.svalue = svalue;
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//전체 글 갯수
	public int getAllSelect() {
		int allCount = 0;
		try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			if(sname == null) {
			   allCount = dao.AllSelectDB();
			}else {
			   allCount = dao.AllSelectDB(sname, svalue);	
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return allCount;
	}
	
	//일반 페이징이 있는 목록
	public ArrayList<BDto> getList(){
		ArrayList<BDto> lists = null;
		try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			lists = dao.selectDB(limitPage, listCount);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return lists;
	}
	
	//검색 목록
	public ArrayList<BDto> getSearchList(){
		ArrayList<BDto> lists = null;
		try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			lists = dao.selectDB(limitPage, listCount, sname, svalue);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return lists;
	}
	
	//contents 보기
	public BDto getSelectOne() {
		BDto list = null;
		try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			list = dao.viewDB(getId());
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return list;
	}
	
	//코멘트 목록보기 
	public ArrayList<CDto> getCommentList(String jboard_id) {
		int jid = Integer.parseInt(jboard_id);
		ArrayList<CDto> list = null;
		try {
			conn = db.getConnection();
			JBoardCommentDao cdao = new JBoardCommentDao(conn);
			list = cdao.selectDB(jid);
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
		return list;
	}
	
	
	//update (조회수 증가)
	public void getUpdate(int count) {   
		int uid = Integer.parseInt(getId());
		try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			dao.updateDB(uid, count, "hit");
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}
}
