package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {//������
		try {
			//���� ���ְ� �Ҵ���� cafe24 ������ ȣ���� ���� �Է� (cafe24�� �� ���ڵ尡 ���ư��� db���� �� cafe24���� �Ҵ����� ������ ����������Ƿ�)--> ���� �������� db�� �����ؼ� �����Ҽ�����
			String dbURL="jdbc:mysql://localhost/qocn9029";
			String dbID="qocn9029";
			String dbPassword="dl@9622919595";
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int login(String userID,String userPassword){
		String SQL="SELECT userPassword FROM USER WHERE userID=?";
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs=pstmt.executeQuery();//sql ��ɹ� ����
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //�α��� ����
				}
				else return 0;//��й�ȣ ����ġ
			}
			return -1;//���̵� ����
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;//������ ���̽� ����
	}

	public int join(User user) {//ȸ�������Ҷ� �������� db�� ����
		String SQL="INSERT INTO USER VALUES(?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setNString(1, user.getUserID());
			pstmt.setNString(2, user.getUserPassword());
			pstmt.setNString(3, user.getUserName());
			pstmt.setNString(4, user.getUserGender());
			pstmt.setNString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
