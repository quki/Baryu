package com.quki.gongmo.bary.data;

public class GyuoData {
	private String title, phone, location, adressOne, adressTwo;
	private double wiDo, kyungDo;

	public GyuoData() {
	}

	public GyuoData(String title, String phone, String location,
			String adressOne, String adressTwo, double wiDo, double kyungDo) {
		this.title = title;
		this.phone = phone;
		this.location = location;
		this.adressOne = adressOne;
		this.adressTwo = adressTwo;
		this.wiDo = wiDo;
		this.kyungDo = kyungDo;
	}

	// 훈련기관명
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// 전화번호
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// 관할지사
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// 지번주소
	public String getAdressOne() {
		return adressOne;
	}

	public void setAdressOne(String adressOne) {
		this.adressOne = adressOne;
	}

	// 도로명주소
	public String getAdressTwo() {
		return adressTwo;
	}

	public void setAdressTwo(String adressTwo) {
		this.adressTwo = adressTwo;
	}

	// 위도
	public double getWido() {
		return wiDo;
	}

	public void setWido(double wiDo) {
		this.wiDo = wiDo;
	}

	// 경도
	public double getKyungdo() {
		return kyungDo;
	}

	public void setKyungdo(double kyungDo) {
		this.kyungDo = kyungDo;
	}
}
