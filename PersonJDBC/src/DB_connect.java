import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB_connect {
	public static void main(String[] args){
		try{
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!");

			//2. DB와 연결
			String ur1 ="jdbc:oracle:thin:@localhost:1521:xe";

			/*
    jdbc : protocol 이름,
    oracle : db이름,
    thin : 100% 자바로 작성된 드라이버
    @localhost : db server의 ip
    1521 : oracle port번호 (db접속하기 b위한 포트)
    xe : SID(System Identifier, 설치되어 있는 오라클 데이터베이스의 이름을 
    운영체제가 이해할 수 있도록 정의하는 이름)

			 */

			String user = "jspid", pwd = "jsppw";
			System.out.println("DB와의 연결을 시도합니다.");

			Connection con = DriverManager.getConnection(ur1, user, pwd);
			System.out.println("DB와의 연결이 성공하였습니다.");

			System.out.println("DB와의 연결 끊기를 시도합니다.");
			con.close(); //DB와 연결된 자원 반납
			System.out.println("DB와의 연결 끊기를 성공하였습니다.");

		}   //예외처리
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e){
			e.printStackTrace();
		}

	}
} 