package com.douzone.mysite.vo;

public class GalleryVo {

	private long no;
	private String comment;
	private String url;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", comment=" + comment + ", url=" + url + "]";
	}
	
}
