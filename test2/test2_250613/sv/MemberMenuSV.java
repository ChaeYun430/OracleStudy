package test2_250613.sv;

import java.sql.SQLException;
import java.util.Scanner;

import test2_250613.dao.MemberDAO;
import test2_250613.dto.MemberDTO;

public class MemberMenuSV {
	static MemberDAO memberDAO = new MemberDAO();
	static Scanner scStr = new Scanner(System.in);

	// 1. userMenu method
	static public void userMenu(MemberDTO session) throws SQLException {
		boolean run = true;
		while (run) {
			String[] UserMenu = { "1. �� ȸ�� ���� ��ȸ", "2. ȸ�� ���� ����", "3. ���� ���� ��ȯ", "4. �α׾ƿ�" };
			for (int i = 0; i < UserMenu.length; i++) {
				System.out.println(UserMenu[i]);
			}
			System.out.print(">>>");
			String select = scStr.next();
			switch (select) {
			case "1":
				if (!session.getActive().equals("ACTIVE")) {
					System.out.println("���� ���¸� �������ּ���");
					break;
				}
				memberDAO.readOne(session);
				break;
			case "2":
				if (!session.getActive().equals("ACTIVE")) {
					System.out.println("���� ���¸� �������ּ���");
					break;
				}
				MemberSV.update(session);
				break;
			case "3":
				accountStateMenu(session);
				break;
			case "4":
				System.out.println("�α׾ƿ��Ǿ����ϴ�.");
				run = false;
				break;
			default:
				System.out.println("�ٽ� �������ּ���.");
				break;
			}
		}
	}

	// 2. adminMenu method
	static public void adminMenu(MemberDTO session) throws SQLException {
		boolean run = true;
		while (run) {
			String[] adminMenu = { "1. ��ü ȸ�� ���� ��ȸ", "2. ȸ�� ���� ��ȸ", "3. �α׾ƿ�" };
			for (int i = 0; i < adminMenu.length; i++) {
				System.out.println(adminMenu[i]);
			}
			System.out.print(">>>");
			String select = scStr.next();
			switch (select) {
			case "1":
				memberDAO.readAll();
				break;
			case "2":
				System.out.println();
				memberDAO.readOne(session);
				break;
			case "3":
				System.out.println("�α׾ƿ��Ǿ����ϴ�.");
				run = false;
				break;
			default:
				System.out.println("�ٽ� �������ּ���.");
				break;
			}
		}
	}

	// 3. accountStateMenu method
	private static void accountStateMenu(MemberDTO session) throws SQLException {
		String[] UserMenu = { "1. ���� Ȱ��ȭ", "2. ���� ��Ȱ��ȭ", "3. ���� Ż��", "4. �������� ����" };
		for (int i = 0; i < UserMenu.length; i++) {
			System.out.println(UserMenu[i]);
		}
		System.out.print(">>>");
		memberDAO.accountState(session, scStr.next());

	}
}