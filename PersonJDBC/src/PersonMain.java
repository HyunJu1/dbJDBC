import java.util.ArrayList;
import java.util.Scanner;

public class PersonMain {
	PersonDao dao = new PersonDao();
	Scanner scan = new Scanner(System.in);

	// TODO Auto-generated method stub
	public PersonMain(){
		System.out.println("PersonMain������");
		init();

	}
	public void init(){
		while(true){
			System.out.println("\n================�޴� �����ϱ�=============");
			System.out.println("1.��ü��ȸ");
			System.out.println("2.������ ��ȸ");
			System.out.println("3.��������");
			System.out.println("4.��������");
			System.out.println("5.�����߰�");
			System.out.println("6.���α׷� ����");
			System.out.println(">> �޴� ��ȣ �Է�:");
			int menu = scan.nextInt();

			switch(menu){
			case 1: ArrayList<PersonBean>lists = dao.GetAllPerson();
			ShowPerson(lists);
			break;
			case 2: //������ ��ȸ
				GetPersonByGender();
				break;
			case 3: //��������
				// UpdateData();
				break;
			case 4: //���� ����
				//DeleteData();
			case 5: //�����߰�
				//InsertData();
			case 6: //����
				System.exit(0);
				break;
			default:
				break;
			}
		}//while�� ��
	}//int()�� ��
	public static void main(String[] args) {
		PersonMain d=new PersonMain();


	}
	private void ShowPerson(ArrayList<PersonBean>lists){
		String imsi =
				"��ȣ\t"+"�̸�\t"+"����\t"+"����\t"+"�������\t";
		System.out.println(imsi);  
		for(PersonBean person : lists){  //db table���� ���!
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
	  //2. Ư�� ������ ��ȸ
	  String gender = null;
	  System.out.print("ã������ ���� �Է�: ");
	  gender = scan.next();
	  ArrayList<PersonBean>find_gender= dao.getPersonByGender(gender);
	  if(find_gender.size()==0){
		  System.out.println("�ش缺���� �������� ����");
	  }else{
		  ShowPerson(find_gender);
	  }
  }
	

}
