package test2_250613.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import test2_250613.dto.MemberDTO;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	int result = 0;

	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.168:1521:xe", "TEST2", "TEST2");
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ojdbc6.jar�� Ȯ�����ּ���.");
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("URL �Ǵ� ID �Ǵ� PW�� Ȯ�����ּ���.");
			e.printStackTrace();
			System.exit(0);
		}
	}

	// -1. login method
	public MemberDTO login(MemberDTO session) throws SQLException {
		try {
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PW = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, session.getId());
			pstmt.setString(2, session.getPw());
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				session.setMno(resultSet.getInt("MNO"));
				session.setNick(resultSet.getString("NICK"));
				session.seteAdress(resultSet.getString("EADRESS"));
				session.setActive(resultSet.getString("ACTIVE"));
				session.setCtg(resultSet.getString("CTG"));
				session.setRegiDate(resultSet.getDate("REGIDATE"));
				return session;
			}

		} catch (SQLException e) {
			System.out.println("check login method of MemberDAO");
			e.printStackTrace();
		} finally {
			pstmt.close();
			resultSet.close();
		}
		return null;
	}

	
	// 1. insert method
	public void insert(MemberDTO memDto) throws SQLException {
		try {
			String sql = "INSERT INTO MEMBER (MNO, ID, PW, NICK, EADRESS, ACTIVE, CTG) VALUES (SEQ.NEXTVAL, ?, ?, ?, ?, 'ACTIVE', 'USER')";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memDto.getId());
			pstmt.setString(2, memDto.getPw());
			pstmt.setString(3, memDto.getNick());
			pstmt.setString(4, memDto.geteAdress());
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
			} else {
				System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
			}
		} catch (SQLException e) {
			System.out.println("check insert method of MemberDAO");
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	
	
	//1-1. emailIdCheck method
	public boolean emailIdCheck(MemberDTO memDto) throws SQLException {
		try {
			//����: �̸����� EMAIL ���̺� �����ϰ�!! ���̵� MEMBER ���̺� �������� ���� ��!!
			//�������� ����Ͽ� ���� ���翩�� �Ǵ���
			String sql = "SELECT * FROM MEMBER WHERE EXISTS (SELECT EADRESS FROM EMAIL E WHERE E.EADRESS = ?)"
					+ "AND NOT EXISTS (SELECT M.ID FROM MEMBER M WHERE M.ID = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memDto.geteAdress());
			pstmt.setString(2, memDto.getId());
			resultSet = pstmt.executeQuery();
			if (!resultSet.next()) {
				System.out.println("�̸����� ���¸� Ȯ�����ּ���.");
				System.out.println("�Ǵ� ���̵��� �ߺ� ���ɼ��� �ֽ��ϴ�.");
				//���Ȼ� �̸��� ���翩��, ���̵� ���翩�� �˷����� ���� 
				return false;
			}
		} catch (SQLException e) {
			System.out.println("check emailIdCheck method of MemberDAO");
			e.printStackTrace();
		}
		return true;
	}
	

	// 2. readAll method
	public void readAll() throws SQLException {
		try {
			String sql = "SELECT MNO, ID, NICK, EADRESS, ACTIVE, REGIDATE FROM MEMBER ORDER BY REGIDATE DESC";
			pstmt = conn.prepareStatement(sql);
			
			resultSet = pstmt.executeQuery();
			System.out.println("ȸ����ȣ\t ���̵�\t �г���\t �̸���\t ��������");
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("MNO") + "\t");
				System.out.print(resultSet.getString("ID") + "\t");
				System.out.print(resultSet.getString("NICK") + "\t");
				System.out.print(resultSet.getString("EADRESS") + "\t");
				System.out.println(resultSet.getString("ACTIVE") + "\t");
				System.out.println(resultSet.getDate("REGIDATE"));
			}
		} catch (SQLException e) {
			System.out.println("check readAll method of MemberDAO");
			e.printStackTrace();
		} finally {
			pstmt.close();
			resultSet.close();
		}
	}

	// 3. readOne method
	public void readOne(MemberDTO memDto) throws SQLException {
		try {
			String sql = "SELECT MNO, ID, NICK, EADRESS, ACTIVE, REGIDATE FROM MEMBER WHERE ? IN(NICK, ID) AND PW = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memDto.getId());
			pstmt.setString(2,memDto.getPw());
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				System.out.println("ȸ����ȣ\t ���̵�\t �г���\t �̸���\t ��������\t ȸ�������");
				System.out.print(resultSet.getInt("MNO") + "\t");
				System.out.print(resultSet.getString("ID") + "\t");
				System.out.print(resultSet.getString("NICK") + "\t");
				System.out.print(resultSet.getString("EADRESS") + "\t");
				System.out.print(resultSet.getString("ACTIVE") + "\t");
				System.out.println(resultSet.getDate("REGIDATE"));
			} else {
				System.out.println("ã���ô� ȸ�������� �����ϴ�.");
			}
		} catch (SQLException e) {
			System.out.println("check readOne method of MemberDAO");
			e.printStackTrace();
		} finally {
			pstmt.close();
			resultSet.close();
		}
	}

	// 4. update method
	public void update(MemberDTO memDto) throws SQLException {
		try {
			String sql = "UPDATE MEMBER SET ID= ?, PW = ?, NICK=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memDto.getId());
			pstmt.setString(2, memDto.getPw());
			pstmt.setString(3, memDto.getNick());
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("ȸ������ ������ �Ϸ��Ͽ����ϴ�.");
			} else {
				System.out.println("ȸ������ ������ �����Ͽ����ϴ�.");
			}
		} catch (SQLException e) {
			System.out.println("check update method of MemberDAO");
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}

	// 5. accountState method
	public void accountState(MemberDTO memDto, String subSelect) throws SQLException {
		try {
			String sql = "UPDATE MEMBER SET ACTIVE  = ? ";
			pstmt = conn.prepareStatement(sql);
			switch (subSelect) {
			case "1":
				pstmt.setString(1, "ACTIVE");
				break;
			case "2":
				pstmt.setString(1, "REST");
				break;
			case "3":
				pstmt.setString(1, "NONE");
				break;
			default:
				pstmt.setString(1, memDto.getActive());
				System.out.println("���� ���� ������ �����Ͽ����ϴ�.");
			}
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("���� ���°� ����Ǿ����ϴ�.");
			} else {
				System.out.println("���� ����  ������ �����Ͽ����ϴ�.");
			}
		} catch (SQLException e) {
			System.out.println("check accountState method of MemberDAO");
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}

}
