package test2_250613.dto;

import java.sql.Date;
import java.util.Scanner;

public class MemberDTO {
	private int mno;
	private String id;
	private String pw;
	private String nick;
	private String eAdress;
	private String active;
	private String ctg;
	private Date regiDate;
	
	public MemberDTO() {

	}

	//�ʱⰪ�� �����Ӱ� ���� �ֵ� �����̳� �����ڵ�� getter setter��
	public MemberDTO(String id, String nick, String eAdress) {
		this.id = id;
		this.nick = nick;
		this.eAdress = eAdress;
		active = "ACTIVE";
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	 
	public void insertPw(String pw, Scanner scStr) {
		//dto�� ���� �ڵ带 ������ �ʵ� ���۽� ������ �ɸ� 
		//���� �ڵ�� ��ۿ� ����� ����(setter)
		System.out.println("�����Ͻ� ��й�ȣ�� �ٽ� �Է����ּ���.");
		System.out.print(">>>");
		String check = scStr.next();
		if (check.equals(pw)) {
			this.pw = pw;
		}
		System.out.println("�����Ͻ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String geteAdress() {
		return eAdress;
	}

	public void seteAdress(String eAdress) {
		this.eAdress = eAdress;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	
	public String getCtg() {
		return ctg;
	}

	public void setCtg(String ctg) {
		this.ctg = ctg;
	}

	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}




	
	
	
}
