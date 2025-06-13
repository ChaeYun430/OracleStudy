package test2_250613;

import java.sql.SQLException;
import java.util.Scanner;

import test2_250613.dao.MemberDAO;
import test2_250613.dto.MemberDTO;
import test2_250613.sv.MemberMenuSV;
import test2_250613.sv.MemberSV;

public class MemberExam {
	public static Scanner scStr = new Scanner(System.in);
	public static MemberDTO session = null;
	
	public static void main(String[] args) throws SQLException {
		boolean run = true;
		while (run) {
			String[] Menu = {"1. ȸ�� ����", "2. �α���", "3. �ý��� ����"};
			for (int i = 0; i < Menu.length; i++) {
				System.out.println(Menu[i]);
			}
			System.out.print(">>>");
			String select = scStr.next();
			switch (select) {
			case "1":
				MemberSV.insert();
				break;
			case "2":
				session  = MemberSV.login(session); //�α��� ������ session Ȱ��
				if (session == null) {
					break;
				}else if (session.getCtg().equals("ADMIN")) {
					MemberMenuSV.adminMenu(session);
				}else if(session.getCtg().equals("USER")){
					if (session.getActive().equals("NONE")) {
						System.out.println("�������� �ʴ� �����Դϴ�.");
						break;
					}
					MemberMenuSV.userMenu(session);
				}
				break;
			case "3":
			System.out.println("ȸ������ ���α׷��� �����մϴ�.");
			run = false;
			break;
			default:
				System.out.println("�ٽ� �������ּ���.");
				break;
			}
		}
	}
}