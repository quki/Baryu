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

	// �Ʒñ����
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// ��ȭ��ȣ
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// ��������
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// �����ּ�
	public String getAdressOne() {
		return adressOne;
	}

	public void setAdressOne(String adressOne) {
		this.adressOne = adressOne;
	}

	// ���θ��ּ�
	public String getAdressTwo() {
		return adressTwo;
	}

	public void setAdressTwo(String adressTwo) {
		this.adressTwo = adressTwo;
	}

	// ����
	public double getWido() {
		return wiDo;
	}

	public void setWido(double wiDo) {
		this.wiDo = wiDo;
	}

	// �浵
	public double getKyungdo() {
		return kyungDo;
	}

	public void setKyungdo(double kyungDo) {
		this.kyungDo = kyungDo;
	}
}
