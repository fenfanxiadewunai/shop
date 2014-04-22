package com.huang.domain.privilege;

import java.util.HashSet;
import java.util.Set;

public class Department {
	private String departmentid;
	private String name;
	private Set<Employee> employees = new HashSet<Employee>();
	
	public Department(){}
	
	public Department(String departmentid){
		this.departmentid = departmentid;
	}
	
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((departmentid == null) ? 0 : departmentid.hashCode());
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
		Department other = (Department) obj;
		if (departmentid == null) {
			if (other.departmentid != null)
				return false;
		} else if (!departmentid.equals(other.departmentid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Department [departmentid=" + departmentid + ", name=" + name
				+ ", employees=" + employees + "]";
	}
	
	
}
