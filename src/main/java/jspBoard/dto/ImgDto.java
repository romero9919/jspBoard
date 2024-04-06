package jspBoard.dto;

public class ImgDto {
   private int id;
   private int jboard_id;
   private String ofilename;
   private String nfilename;
   private String ext;
   private long filesize;
   private String userid;
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
	public int getJboard_id() {
		return jboard_id;
	}
	public void setJboard_id(int jboard_id) {
		this.jboard_id = jboard_id;
	}
	public String getOfilename() {
		return ofilename;
	}
	public void setOfilename(String ofilename) {
		this.ofilename = ofilename;
	}
	public String getNfilename() {
		return nfilename;
	}
	public void setNfilename(String nfilename) {
		this.nfilename = nfilename;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "ImgDto [id=" + id + ", jboard_id=" + jboard_id + ", ofilename=" + ofilename + ", nfilename=" + nfilename
				+ ", ext=" + ext + ", filesize=" + filesize + ", userid=" + userid + ", imnum=" + imnum + "]";
	}
	
}
