package test2_250613.sv;

import java.sql.SQLException;
import java.util.Scanner;

import test2_250613.dao.MemberDAO;
import test2_250613.dto.MemberDTO;

public class MemberSV {
	static MemberDAO memberDAO = new MemberDAO();
	static MemberDTO mem;
	static Scanner scStr = new Scanner(System.in);

	// -1. login method
	static public MemberDTO login(MemberDTO session) throws SQLException {
		session = new MemberDTO();
		System.out.print("���̵�: ");
		session.setId(scStr.next());
		System.out.print("��й�ȣ: ");
		session.setPw(scStr.next());
		session = memberDAO.login(session);
		if (session == null) {
			System.out.println("�α��ο� �����߽��ϴ�.");
			return null;
		}
		System.out.println("�α����� �Ϸ�Ǿ����ϴ�.");
		return session;
	}

	// 0. searchOne method
	public void searchOne() throws SQLException {
		mem = new MemberDTO(); // �б�� ��ü
		System.out.println("ã���ô� ȸ���� ���̵� �Ǵ� �г����� �Է����ּ���.");
		mem.setId(scStr.next());
		System.out.println("��й�ȣ�� �Է����ּ���.");
		mem.setPw(scStr.next());
		memberDAO.readOne(mem);
	}

	// 1. insert method
	static public void insert() throws SQLException {
		System.out.print("�г���:	");
		String nick = scStr.next();
		System.out.print("�̸��� �ּ�:	"); // ���� �����̹Ƿ� �Ժη� �� �ٲ�
		String eAdress = scStr.next();
		System.out.print("���̵�:	");
		String id = scStr.next();
		mem = new MemberDTO(id, nick, eAdress); // ȸ�����Կ� ��ü
		boolean emailId = memberDAO.emailIdCheck(mem);
		if (!emailId) {
			System.out.println("ȸ�����Կ� �����߽��ϴ�.");
			return;
		}
		//�̸���, ���̵� Ȯ�� ��� �� ����
		System.out.print("��й�ȣ: ");
		mem.insertPw(scStr.next(), scStr); //��й�ȣ Ȯ�� �ڵ�
		if (mem.getPw() == null) {
			System.out.println("ȸ�����Կ� �����߽��ϴ�.");
			return;
		}
		memberDAO.insert(mem);
	}

	// 2. update method
	static public void update(MemberDTO session) throws SQLException {
		boolean run = true;
		while (run) {
			System.out.println("�����Ͻ� �׸��� �������ּ���.");
			String[] Menu = { "1. ���̵�", "2. ��й�ȣ", "3. �г���", "4. ȸ������ ���� ����" };
			for (int i = 0; i < Menu.length; i++) {
				System.out.println(Menu[i]);
			}
			System.out.print(">>>");
			String select = scStr.next();
			switch (select) {
			case "1":
				System.out.println("�����Ͻ� ���̵� �Է��ϼ���.");
				System.out.print(">>>");
				session.setId(scStr.next());
				memberDAO.update(session);
				break;
			case "2":
				System.out.println("�����Ͻ� ��й�ȣ�� �Է��ϼ���.");
				System.out.print(">>>");
				session.insertPw(scStr.next(), scStr);
				memberDAO.update(session);
				break;
			case "3":
				System.out.println("�����Ͻ� �г����� �Է��ϼ���.");
				System.out.print(">>>");
				session.setNick(scStr.next());
				memberDAO.update(session);
				break;
			case "4":
				System.out.println("ȸ������ ������ �����մϴ�.");
				run = false;
				break;
			default:
				System.out.println("�ٽ� �������ּ���.");
				break;
			}
		}
	}
}
