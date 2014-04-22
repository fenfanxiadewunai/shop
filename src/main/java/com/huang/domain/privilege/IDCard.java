package com.huang.domain.privilege;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IDCard {
	private Integer id;
	private String cardno;
	private String address;
	private Date birthday;
	private Employee employee;
	
	private static DateFormat format = new SimpleDateFormat("yyyyMMdd");  

	public IDCard(){}
	
	public IDCard(Integer id){
		this.id = id;
	}
	
	public IDCard(String cardno, String address, Date birthday) {
		this.cardno = cardno;
		this.address = address;
		this.birthday = birthday;
	}
	
	public IDCard(String cardno, String address) {
		this.cardno = cardno;
		this.address = address;
	}
	
	public IDCard(String cardno, String address, String birthday) throws ParseException {
		this.cardno = cardno;
		this.address = address;
		this.birthday = format.parse(birthday);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDCard other = (IDCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IDCard [id=" + id + ", cardno=" + cardno + ", address="
				+ address + ", birthday=" + birthday + ", employee=" + employee
				+ "]";
	}
	
	
	
}
