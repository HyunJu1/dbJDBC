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
				//GetPersonByGender;
				break;
			case 3: //정보수정
				// UpdateData();
				break;
			case 4: //정보 삭제
				//DeleteData();
			case 5: //정보추가
				//InsertData();
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
		for(PersonBean person : lists){
			String result = 
					person.getNum()+"\t"+
							person.getName()+"\t"+
							person.getAge()+"\t"+
							person.getGender()+"\t"+
							person.getBirth()+"\t";

			System.out.println(result);
		}


	}

}
