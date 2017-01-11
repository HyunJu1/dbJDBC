

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
		System.out.println("PersonDao 생성자");
		try{
			Class.forName(driver);
			//ojdbc14.jar반드시 넣어줘야한다. C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib에 jar 파일이 있다
		}catch(ClassNotFoundException e){
			System.out.println("클래스가 발견되지 않았습니다.(jar 파일 누락");
			e.printStackTrace();
		}
	}
	private Connection getConnection(){
		try{
			conn= 
					DriverManager.getConnection(url,username,password);
		}catch(SQLException e){
			System.out.println("커넥션 생성 오류");
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
			pstmt= conn.prepareStatement(sql); //3단계: sql 분석
			rs= pstmt.executeQuery(); //4단계 : 실행
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
	public ArrayList<PersonBean> getPersonByGender(String gender) {
		System.out.println(gender);
		// TODO Auto-generated method stub
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql= 
				"select * from person where gender=? order by num asc";
		// ?: 위치홀더
		ArrayList<PersonBean>lists = new ArrayList<PersonBean>();
		try{
			conn= getConnection();
     		  //3.sql 분석
			PersonBean person= new PersonBean();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gender);
			rs= pstmt.executeQuery();

			while(rs.next()){

			//pstmt.setString(1,person.getGender());
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
				if(rs!=null){
					rs.close();
				}
				if(pstmt !=null){
					pstmt.close();
				}
				closeConnection();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		System.out.println("검색된 성별은 : " +lists.size()+"명 입니다");
		return lists;
	}
	public int InsertData(PersonBean person) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt =null;
		String sql ="insert into person values(perseq.nextval,?,?,?,to_date(?,'yyyy/mm/dd'))";
		int cnt =-1;
		try{
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setInt(2, person.getAge());
			pstmt.setString(3, person.getGender());
			pstmt.setString(4, person.getBirth());
		
		cnt=pstmt.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				conn.rollback();
			}
			catch(Exception e2){e2.printStackTrace();}
		}
		finally{
			try{
				if(pstmt != null){
					pstmt.close();}
				closeConnection();
			}
			catch(Exception e2){
				e2.printStackTrace();
				}
			}
		return cnt;
			
		}
	public int UpdateData(PersonBean person) {
		System.out.println(person.getName());
		
		
		PreparedStatement pstmt =null;
		String sql ="update person set name=?,age=?,gender=?,"+
		"birth=to_date(?,'yyyy/mm/dd')"
				+"where num=?";
		int cnt =-1;
		try{
			
			conn= getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getName());
			pstmt.setInt(2, person.getAge());
			pstmt.setString(3, person.getGender());
			pstmt.setString(4, person.getBirth());
			pstmt.setInt(5, person.getNum());
		
		cnt=pstmt.executeUpdate();
		conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				conn.rollback();
			}
			catch(Exception e2){e2.printStackTrace();}
		}
		finally{
			try{
				if(pstmt != null){
					pstmt.close();}
				closeConnection();
			}
			catch(Exception e2){
				e2.printStackTrace();
				}
			}
		return cnt;
	}
	public int DeleteData(int num) {
		
		
		PreparedStatement pstmt =null;
		String sql ="delete from person where num=?";
		int cnt =-1;
		try{
			
			conn= getConnection();
			//conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
		
		cnt=pstmt.executeUpdate();
		//conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				conn.rollback();
			}
			catch(Exception e2){e2.printStackTrace();}
		}
		finally{
			try{
				if(pstmt != null){
					pstmt.close();}
				closeConnection();
			}
			catch(Exception e2){
				e2.printStackTrace();
				}
			}
		return cnt;
	
	}
			}





