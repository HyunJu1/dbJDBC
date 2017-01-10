import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDao {
	private String driver= "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "jspid";
	private String password= "jsppw";
	private Connection conn= null;

	public PersonDao(){
		System.out.println("PersonDao ������");
		try{
			Class.forName(driver);
			//ojdbc14.jar�ݵ�� �־�����Ѵ�. C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib�� jar ������ �ִ�
		}catch(ClassNotFoundException e){
			System.out.println("Ŭ������ �߰ߵ��� �ʾҽ��ϴ�.(jar ���� ����");
			e.printStackTrace();
		}
	}
	private Connection getConnection(){
		try{
			conn= 
					DriverManager.getConnection(url,username,password);
		}catch(SQLException e){
			System.out.println("Ŀ�ؼ� ���� ����");
			e.printStackTrace();
		}
		return conn;
	}
	public ArrayList<PersonBean> GetAllPerson(){
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql= "select * from person order by num";
		ArrayList<PersonBean>lists= new ArrayList<PersonBean>();
		try{
			conn= getConnection();
			pstmt= conn.prepareStatement(sql); //3�ܰ�: sql �м�
			rs= pstmt.executeQuery(); //4�ܰ� : ����
			while(rs.next()){
				PersonBean person = new PersonBean();
				person.setNum(rs.getInt("num"));
				person.setName(rs.getString("name"));
				person.setAge(rs.getInt("age"));
				person.setGender(rs.getString("gender"));
				person.setBirth(String.valueOf(rs.getDate("birth")));

				lists.add(person);
			}}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt !=null){pstmt.close();}
				closeConnection();
			}
			catch (Exception e2){
				e2.printStackTrace();}
		}
		return lists;
	}
	private void closeConnection() {
		try{
			if(conn!=null){conn.close();
			}
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}

}



