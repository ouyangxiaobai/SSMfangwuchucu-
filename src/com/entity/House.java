package com.entity;

import com.util.VeDate;

public class House {
	private String houseid = "H" + VeDate.getStringId();
	private String usersid;
	private String housename;
	private String image;
	private String price;
	private String cateid;
	private String mianji;
	private String louceng;
	private String chaoxiang;
	private String addtime;
	private String hits;
	private String status;
	private String contents;
	private String username;
	private String catename;
	private String contact;

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getHouseid() {
		return houseid;
	}

	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getHousename() {
		return this.housename;
	}

	public void setHousename(String housename) {
		this.housename = housename;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCateid() {
		return this.cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getMianji() {
		return this.mianji;
	}

	public void setMianji(String mianji) {
		this.mianji = mianji;
	}

	public String getLouceng() {
		return this.louceng;
	}

	public void setLouceng(String louceng) {
		this.louceng = louceng;
	}

	public String getChaoxiang() {
		return this.chaoxiang;
	}

	public void setChaoxiang(String chaoxiang) {
		this.chaoxiang = chaoxiang;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getHits() {
		return this.hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}
}
