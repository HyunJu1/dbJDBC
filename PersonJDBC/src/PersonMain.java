import java.util.ArrayList;
import java.util.Scanner;

public class PersonMain {
	PersonDao dao = new PersonDao();
	Scanner scan = new Scanner(System.in);

	// TODO Auto-generated method stub
	public PersonMain(){
		System.out.println("PersonMain생성자");
		init();

	}
	public void init(){
		while(true){
			System.out.println("\n================메뉴 선택하기=============");
			System.out.println("1.전체조회");
			System.out.println("2.성별로 조회");
			System.out.println("3.정보수정");
			System.out.println("4.정보삭제");
			System.out.println("5.정보추가");
			System.out.println("6.프로그램 종료");
			System.out.println(">> 메뉴 번호 입력:");
			int menu = scan.nextInt();

			switch(menu){
			case 1: ArrayList<PersonBean>lists = dao.GetAllPerson();
			ShowPerson(lists);
			break;
			case 2: //성별로 조회
				GetPersonByGender();
				break;
			case 3: //정보수정
				 UpdateData();
				break;
			case 4: //정보 삭제
				DeleteData();
				break;
			case 5: //정보추가
				 InsertData();
				 break;
			case 6: //종료
				System.exit(0);
				break;
			default:
				break;
			}
		}//while의 끝
	}//int()의 끝


public static void main(String[] args) {
		PersonMain d=new PersonMain();


	}
	private void ShowPerson(ArrayList<PersonBean>lists){
		String imsi =
				"번호\t"+"이름\t"+"나이\t"+"성별\t"+"생년월일\t";
		System.out.println(imsi);  
		for(PersonBean person : lists){  //db table내용 출력!
			//for(int i=0; i<lists.size(); i++){ }
            
			String result = 
					person.getNum()+"\t"+
							person.getName()+"\t"+
							person.getAge()+"\t"+
							person.getGender()+"\t"+
							person.getBirth()+"\t";
			System.out.println(result);
		}
	}
  private void GetPersonByGender(){
	  //2. 특정 성별만 조회
	  String gender = null;
	  System.out.print("찾으려는 성별 입력: ");
	  gender = scan.next();
	  ArrayList<PersonBean>find_gender= dao.getPersonByGender(gender);
	  if(find_gender.size()==0){
		  System.out.println("해당성별은 존재하지 않음");
	  }else{
		  ShowPerson(find_gender);
	  }
  }
	private void InsertData() {
		// TODO Auto-generated method stub
		int age=0;
		String name=null, gender= null, birth=null;
		
		int result=-1;
		PersonBean person = new PersonBean();
		System.out.println("q번호는 시퀀스로 입력됩니다(생략)");
		System.out.print("이름 입력:");
	     name= scan.next();
	
	     System.out.println("나이 입력:");
	     age= scan.nextInt();
	
	     System.out.println("성별 입력:");
	     gender=scan.next();
	
	     System.out.println("생년월일 입력 (yyyy/m/dd 형식으로)");
	     birth= scan.next();
	
	person.setName(name);
    person.setAge(age);
    person.setGender(gender);
    person.setBirth(birth);

	
	result = dao.InsertData(person);
	if(result ==0){
		System.out.println("데이터 삽입 실패");
		}
	else{
		System.out.println("데이터 삽입 성공");
	}
	}
	
	private void UpdateData() {
		// TODO Auto-generated method stub
		int age=0 , num=0;
		String name=null, gender= null, birth=null;
		
		int result=-1;
		PersonBean person = new PersonBean();
		System.out.println("수정할 번호를 입력하세요:");
		num=scan.nextInt();
		System.out.print("이름 입력:");
	name= scan.next();
	
	System.out.println("나이 입력:");
	age= scan.nextInt();
	
	System.out.println("성별 입력:");
	gender=scan.next();
	
	System.out.println("생년월일 입력 (yyyy/m/dd 형식으로)");
	birth= scan.next();
	
	
	person.setNum(num);
	person.setName(name);
person.setAge(age);
person.setGender(gender);
person.setBirth(birth);

	
	result = dao.UpdateData(person);
	if(result ==0){
		System.out.println("수정실패");
		}
	else{
		System.out.println("수정성공");
	}
		
	}
	private void DeleteData() {
		int num=0;
		int result=-1;
		
		System.out.println("삭제할 번호를 입력하세요:");
		num=scan.nextInt();
		result = dao.DeleteData(num);
	   if(result ==0){
			System.out.println("삭제실패");
			}
		else{
			System.out.println("삭제성공");
		}
	}

	

}
