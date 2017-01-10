import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB_connect {
	public static void main(String[] args){
		try{
			//1. ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����!");

			//2. DB�� ����
			String ur1 ="jdbc:oracle:thin:@localhost:1521:xe";

			/*
    jdbc : protocol �̸�,
    oracle : db�̸�,
    thin : 100% �ڹٷ� �ۼ��� ����̹�
    @localhost : db server�� ip
    1521 : oracle port��ȣ (db�����ϱ� b���� ��Ʈ)
    xe : SID(System Identifier, ��ġ�Ǿ� �ִ� ����Ŭ �����ͺ��̽��� �̸��� 
    �ü���� ������ �� �ֵ��� �����ϴ� �̸�)

			 */

			String user = "jspid", pwd = "jsppw";
			System.out.println("DB���� ������ �õ��մϴ�.");

			Connection con = DriverManager.getConnection(ur1, user, pwd);
			System.out.println("DB���� ������ �����Ͽ����ϴ�.");

			System.out.println("DB���� ���� ���⸦ �õ��մϴ�.");
			con.close(); //DB�� ����� �ڿ� �ݳ�
			System.out.println("DB���� ���� ���⸦ �����Ͽ����ϴ�.");

		}   //����ó��
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e){
			e.printStackTrace();
		}

	}
} 