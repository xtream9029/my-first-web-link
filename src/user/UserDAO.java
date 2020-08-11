package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {//생성자
		try {
			//내가 돈주고 할당받은 cafe24 웹서버 호스팅 정보 입력 (cafe24에 내 웹코드가 돌아가고 db역시 그 cafe24에서 할당해준 서버에 구축돼있으므로)--> 나는 원격으로 db에 접속해서 수정할수있음
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
			rs=pstmt.executeQuery();//sql 명령문 실행
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				else return 0;//비밀번호 불일치
			}
			return -1;//아이디가 없음
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;//데이터 베이스 오류
	}

	public int join(User user) {//회원가입할때 정보들을 db에 저장
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
