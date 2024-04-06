package jspBoard.dto;

import java.sql.Timestamp;

public class MDto {
   
	private int id;
	private String userid;
	private String userpass;
	private String username;
	private String useremail;
	private String usertel;
	private String addr1;
	private String addr2;
	private String userlink;
	private String role;
	private Timestamp wdate;
	private int zipcode;
	
	public MDto() {}

	public MDto(int id, String userid, String userpass, String username, String useremail, String usertel, String addr1,
			String addr2, String userlink, String role, Timestamp wdate, int zipcode) {
		super();
		this.id = id;
		this.userid = userid;
		this.userpass = userpass;
		this.username = username;
		this.useremail = useremail;
		this.usertel = usertel;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.userlink = userlink;
		this.role = role;
		this.wdate = wdate;
		this.zipcode = zipcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getUserlink() {
		return userlink;
	}

	public void setUserlink(String userlink) {
		this.userlink = userlink;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getWdate() {
		return wdate;
	}

	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "MDto [id=" + id + ", userid=" + userid + ", userpass=" + userpass + ", username=" + username
				+ ", useremail=" + useremail + ", usertel=" + usertel + ", addr1=" + addr1 + ", addr2=" + addr2
				+ ", userlink=" + userlink + ", role=" + role + ", wdate=" + wdate + ", zipcode=" + zipcode + "]";
	}

}
