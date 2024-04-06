package jspBoard.dto;

import java.sql.Timestamp;

public class BDto {
	
   private int id;
   private int refid;
   private int depth;
   private int renum;
   private String title;
   private String content;
   private String writer;
   private String pass;
   private String userid;
   private int hit;
   private int chit;
   private Timestamp wdate;
   private String imnum;
   
public String getImnum() {
	return imnum;
}
public void setImnum(String imnum) {
	this.imnum = imnum;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRefid() {
	return refid;
}
public void setRefid(int refid) {
	this.refid = refid;
}
public int getDepth() {
	return depth;
}
public void setDepth(int depth) {
	this.depth = depth;
}
public int getRenum() {
	return renum;
}
public void setRenum(int renum) {
	this.renum = renum;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getWriter() {
	return writer;
}
public void setWriter(String writer) {
	this.writer = writer;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public int getHit() {
	return hit;
}
public void setHit(int hit) {
	this.hit = hit;
}
public int getChit() {
	return chit;
}
public void setChit(int chit) {
	this.chit = chit;
}
public Timestamp getWdate() {
	return wdate;
}
public void setWdate(Timestamp wdate) {
	this.wdate = wdate;
}
@Override
public String toString() {
	return "BDto [id=" + id + ", refid=" + refid + ", depth=" + depth + ", renum=" + renum + ", title=" + title
			+ ", content=" + content + ", writer=" + writer + ", pass=" + pass + ", userid=" + userid + ", hit=" + hit
			+ ", chit=" + chit + ", wdate=" + wdate + ", imnum=" + imnum + "]";
}
    
}
