package jspBoard.dto;

import java.sql.Timestamp;

public class CDto {
  private int id;
  private int jboard_id;
  private String userid;
  private String username;
  private String comment;
  private Timestamp wdate;
	  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJboard_id() {
		return jboard_id;
	}
	public void setJboard_id(int jboard_id) {
		this.jboard_id = jboard_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getWdate() {
		return wdate;
	}
	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}
	
	@Override
	public String toString() {
		return "CDto [id=" + id + ", jboard_id=" + jboard_id + ", userid=" + userid + ", username=" + username
				+ ", comment=" + comment + ", wdate=" + wdate + "]";
	}
	  
    
}
