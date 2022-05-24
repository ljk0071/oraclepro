package com.javaex.phone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String num, list, name, hp, op, str, url;
		url = "./PhoneDB.txt";
		int sel,count;
		count = 0;
		boolean stat = true;
		List<String> dl = new ArrayList<String>();
		BufferedReader input = Input(url);
		while (true) {
			list = input.readLine();
			if (list == null) {
				break;
			}
			dl.add(list);
		}
		List<PersonVo> personList = new ArrayList<PersonVo>();
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.Drop();
		phoneDao.DropSeq();
		phoneDao.Create();
		phoneDao.CreateSeq();
		for(int i=0;i<dl.size();i++) {
			String[] newDetail = dl.get(i).split(",");
			personList.add(new PersonVo(newDetail[0],newDetail[1],newDetail[2]));
			phoneDao.Insert(personList.get(i));
		}
		BufferedWriter output = Output(url);
		System.out.println(
				"************************************\n*         전화번호 관리 프로그램         *\n************************************");
		while (stat) {
			System.out.println("1.리스트 2.등록 3.수정 4.삭제 5.검색 6.종료\n------------------------------------");
			System.out.print(">메뉴번호: ");
			num = sc.next();
			switch (num) {
			case "1":
				System.out.print("<1.리스트>\n");
				for (int i = 0; i < dl.size(); i++) {
					String[] details = dl.get(i).split(",");
					System.out.println((i + 1) + ".    " + 
					details[0] + "     " + details[1] + "  " + details[2]);
				}
				System.out.println();
				break;
			case "2":
				System.out.print("<2.등록>\n");
				System.out.print(">이름: ");
				name = sc.next();
				System.out.print(">휴대전화: ");
				hp = sc.next();
				System.out.print(">회사전화: ");
				op = sc.next();
				dl.add(name + "," + hp + "," + op);
				for(int i=0;i<dl.size();i++) {
					if (i==dl.size()-1) {
						String[] newDetail = dl.get(i).split(",");
						personList.add(new PersonVo(newDetail[0],newDetail[1],newDetail[2]));
						phoneDao.Insert(personList.get(i));
					}
				}
				System.out.println("[등록되었습니다.]\n");
				break;
			case "3":
				System.out.print("<3.수정>\n");
				System.out.print(">번호: ");
				sel = sc.nextInt() - 1;
				if (sel < dl.size()) {
					System.out.print(">이름: ");
					name = sc.next();
					System.out.print(">휴대전화: ");
					hp = sc.next();
					System.out.print(">회사전화: ");
					op = sc.next();
					dl.remove(sel);
					dl.add(sel, name + "," + hp + "," + op);
					for(int i=0;i<dl.size();i++) {
						if (i==dl.size()-1) {
							String[] newDetail = dl.get(i).split(",");
							personList.add(new PersonVo(newDetail[0],newDetail[1],newDetail[2]));
							phoneDao.Insert(personList.get(i));
						}
					}
					count++;
					System.out.println("["+count+"건 수정되었습니다.]\n");
				} else {
					System.out.println("리스트 범위를 초과하였습니다\n");
				}
				break;
			case "4":
				System.out.print("<4.삭제>\n>번호 : ");
				sel = sc.nextInt() - 1;
				if (sel < dl.size()) {
					dl.remove(sel);
					phoneDao.DeleteAll();
					for(int i=0;i<dl.size();i++) {
						String[] newDetail = dl.get(i).split(",");
						personList.add(new PersonVo(newDetail[0],newDetail[1],newDetail[2]));
						phoneDao.Insert(personList.get(i));
					}
					System.out.println("[삭제되었습니다.]\n");
				} else {
					System.out.println("리스트 범위를 초과하였습니다\n");
				}
				break;
			case "5":
				int chk = 0;
				System.out.print("<5.검색>\n>이름 : ");
				str = sc.next();
				for (int i = 0; i < dl.size(); i++) {
					if (dl.get(i).contains(str)) {
						String[] details = dl.get(i).split(",");
						System.out.println((i + 1) + ".    " + details[0] + "     " + details[1] + "  " + details[2]);
					} else {
						chk += 1;
					}
				}
				if (chk == dl.size()) {
					System.out.println("[찾으시는 결과가 없습니다.]");
				}
				System.out.println();
				break;
			case "6":
				for (int i = 0; i < dl.size(); i++) {
					output.write(dl.get(i));
					output.newLine();
				}
				phoneDao.DeleteAll();
				phoneDao.DropSeq();
				phoneDao.CreateSeq();
				for(int i=0;i<dl.size();i++) {
					String[] newDetail = dl.get(i).split(",");
					personList.add(new PersonVo(newDetail[0],newDetail[1],newDetail[2]));
					phoneDao.Insert(personList.get(i));
				}
				System.out.println(
						"************************************\n*             감사합니다              *\n************************************");
				stat = false;
				break;
			default:
				System.out.println("[다시 입력해 주세요.]\n");
				break;
			}
		}
		input.close();
		output.close();
		sc.close();
	}
	public static BufferedWriter Output(String url) throws IOException {
		OutputStream os = new FileOutputStream(url);
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		return bw;
	}
	public static BufferedReader Input(String url) throws IOException {
		InputStream is = new FileInputStream("./PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		return br;
	}
}
