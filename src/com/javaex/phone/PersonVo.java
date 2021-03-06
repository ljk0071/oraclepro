package com.javaex.phone;

public class PersonVo {
	
	public int personId;
	public String name, hp, company;

	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int personId, String name, String hp, String company) {
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public int getPersonId() {
		return personId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "PersonVo [name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}

}
