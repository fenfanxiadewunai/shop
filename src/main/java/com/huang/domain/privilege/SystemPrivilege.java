package com.huang.domain.privilege;

public class SystemPrivilege {
	
	private Integer id;
	private SystemPrivilegePK spp;
	private String name;
	
	public SystemPrivilege(){}
	
	public SystemPrivilege(Integer id){
		this.id = id;
	}
	
	public SystemPrivilege(Integer id, String module, String privilege,
			String name) {
		this.id = id;
		this.spp = new SystemPrivilegePK(module, privilege);
		this.name = name;
	}
	
	public SystemPrivilege(String module, String privilege,String name) {
		this(null,module,privilege,name);
	} 
	
	public SystemPrivilege(String module, String privilege) {
		this(null,module,privilege,null);
	} 

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		SystemPrivilege other = (SystemPrivilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean systemprivilegeequals(SystemPrivilege other){
		if(this == other) return true;
		if(this.spp.getModule().equals(other.spp.getModule())&&this.spp.getPrivilege().equals(other.spp.getPrivilege())) return true;
		return false;
	}

	@Override
	public String toString() {
		return "SystemPrivilege [id=" + id + ", spp=" + spp + ", name=" + name
				+ "]";
	}

	
}
