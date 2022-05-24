package com.javaex.phone;

public class asd {

	public static void main(String[] args) {
		PersonVo personVo = new PersonVo("이준규", "12", "12");
		PhoneDao phoneDao = new PhoneDao();
		
		phoneDao.DropSeq();
		phoneDao.CreateSeq();
		phoneDao.Insert(personVo);
	}
}
