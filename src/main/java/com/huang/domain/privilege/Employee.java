package com.huang.domain.privilege;

import java.text.ParseException;

import com.huang.domain.user.Gender;
import com.huang.vo.EmployeeVO;

public class Employee {
	private String username;
	private String password;
	private String realname;
	private Gender gender;
	private String degree;
	private IDCard idCard;
	private String school;
	private String phone;
	private String email;
	private String imageName;
	private Boolean visible = true;
	private Department department;
	
	public Employee(){}
	
	public Employee(String username, String password, String realname,
			Gender gender, String degree, IDCard idCard, String school,
			String phone, String email, String imageName, Boolean visible,
			Department department) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.gender = gender;
		this.degree = degree;
		this.idCard = idCard;
		this.school = school;
		this.phone = phone;
		this.email = email;
		this.imageName = imageName;
		this.visible = visible;
		this.department = department;
	}
	
	public Employee(EmployeeVO evo) {
		this.username = evo.getUsername();
		this.password = evo.getPassword();
		this.realname = evo.getRealname();
		this.gender = evo.getGender();
		try {
			this.idCard = new IDCard(evo.getCardno(), evo.getAddress(),evo.getBirthday());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.phone = evo.getPhone();
		this.email = evo.getEmail();
		this.degree = evo.getDegree();
		this.school = evo.getSchool();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public IDCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getImagePath(){
		return "/images/employee/" + getUsername()+"/"+this.getImageName();
	}
	
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		Employee other = (Employee) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password
				+ ", realname=" + realname + ", gender=" + gender + ", degree="
				+ degree + ", idCard=" + idCard + ", school=" + school
				+ ", phone=" + phone + ", email=" + email + ", imageName="
				+ imageName + ", visible=" + visible + ", department="
				+ department + "]";
	}
	
	
	
}
