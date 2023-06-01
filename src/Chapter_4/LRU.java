package Chapter_4;

import java.util.Scanner;

public class LRU{
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("������ ������ ���� ���� : ");
		int s = scanner.nextInt(); 		//ĳ�� ���� �� �ִ� ����
		LRU ob = new LRU();
		System.out.println("\n1�� : Ÿ��-�������� ī���� �޼ҵ�");
		System.out.println("2�� : ������ �̿��� �޼ҵ�\n");
		System.out.print("�ϳ��� ���ϼ��� : ");
		while(true) {
			int select = scanner.nextInt();
			if(select == 1) {
				ob.countMethod(s);
				break;
			}
			else if(select == 2) {
				ob.stackMethod(s);
				break;
			}
			else {
				System.out.print("�߸��Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ��� : ");
			}
		}
		scanner.close();
	}
	public void countMethod(int s) {
		int[] page = new int[s];
		int[] timestamp = new int[s]; //Ÿ�ӽ����� ī����
		int count = 0;				  //������ ���� ī��Ʈ
		int k = 1; 					  //ȣ�� Ƚ��
		int i;
		while(true) {
			System.out.print("\n�������� ȣ���ϼ���(-1 �Է½� ����) : ");
			int x= scanner.nextInt();
			if(x == -1)
				break; 
			plusCount(page, timestamp);
			//���� ���� �ִ��� ã�� �ݺ���
			for(i = 0; i < s; i++) {
				//�ʱ� ����(0, 0, 0, .... , 0)�� ���
				if(page[i] == 0) {
					page[i] = x;
					count++;
					break;
				}
				//���� ���� ���(�ش� Ÿ�ӽ������� 0���� �ٲ�)
				else if(page[i] == x) {
					timestamp[i] = 0;
					break;
				}
			}
			//���� �������� ���� ���(Ÿ�ӽ������� ���� ū �ε��� �ڸ� �ٲ�)
			if(i == s) {	
				int index = getMaxindex(timestamp);
				page[index] = x;
				timestamp[index] = 0;
				count++;
			}
			System.out.println("\n" + k + "��° ȣ��");
			k++;
			printState(page);
			System.out.print("Ÿ�ӽ����� �� : ");
			for(int n = 0; n < s; n++) {
				System.out.print(n+1 + " : " + timestamp[n] + ", ");
			}
			System.out.println();
		}
		System.out.println("\n�� ������ ������ �� : " + count + "ȸ");
	}
	public int getMaxindex(int timestamp[]) {
		int max = timestamp[0];
		int maxIndex = 0;
		for(int i = 0; i < timestamp.length; i++) {
			if(max < timestamp[i]) {
				max = timestamp[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	public void plusCount(int page[], int timestamp[]) {
		for(int i = 0; i < timestamp.length; i++) {
			if(page[i] == 0) {}
			else
				timestamp[i]++;
		}
	}
	
	public void stackMethod(int s) {
		int[] page = new int[s];
		int count = 0;			//������ ���� ī��Ʈ
		int k = 1;				//ȣ�� Ƚ��
		while(true) {
		  int i;
		  System.out.print("\n�������� ȣ���ϼ���(-1 �Է½� ����) : ");
		  int x = scanner.nextInt();
		  if(x == -1)
			  break;
		  // ���� ���� �ִ��� ã�� �ݺ���
		  for (i = 0; i < s; i++) {
			  // ���� ���� �ִٸ�
			  if (page[i] == x) {
			  // �� �ڸ����� �տ��� ���ʷ� �ڷ� �о���
				  while(i != 0){
					  page[i] = page[i-1];
					  i--;
				  }
				  // ���� �տ� �� �Է�
				  page[0] = x;
				  break;
			  }
		  }
		  // �ݺ����� ������ ������ �� ���� �տ� ���� �Է�����
		  if(i == s){
			  while(i != 1){
				  i--;
				  page[i] = page[i-1];
			  }
			  page[0] = x;
			  count++;	//�ݺ����� ������ ���Ҵٴ� ���� ���� �������� �ʾ��� ��(������ ����)
		  }
		  System.out.println("\n" + k + "��° ȣ��");
		  k++;
		  printState(page);
	  }
	  System.out.println("\n�� ������ ������ �� : " + count + "ȸ");
  	}
  	//���� ���¸� ����Ʈ�ϴ� �޼ҵ�
  	public void printState(int[] arr) {
  		for(int i = 0; i <arr.length; i++)
  			System.out.println("    " + arr[i]);
  	}
}