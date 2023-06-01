package Chapter_4;

import java.util.Scanner;

//LFU : ���� ���� ���ǰų� �������� �ƴ� �������� ��ü
//FIFO ����� (top : ���� ������, bottom : ���� �ֱ�)

public class LFU {
	private Scanner scanner = new Scanner(System.in);
	private int []timestamp = new int[9];
	public LFU() {
		System.out.print("������ ������ ���� ���� : ");
		int s = scanner.nextInt(); 		//ĳ�� ���� �� �ִ� ����
		int[] page = new int[s];
		int count = 0;				  //������ ���� ī��Ʈ
		int pagecount;				  //������ ȣ�� ī��Ʈ
		int k = 1; 					  //ȣ�� Ƚ��
		int i;
		while(true) {
			System.out.print("\n�������� ȣ���ϼ���(-1 �Է½� ����) : ");
			int x= scanner.nextInt();
			if(x == -1)
				break; 
			
			//���� ���� �ִ��� ã�� �ݺ���
			for(i = 0; i < s; i++) {
				//�ʱ� ����(0, 0, 0, .... , 0)�� ���
				if(page[i] == 0) {
					page[i] = x;
					timestamp[x-1]++; //sd
					count++;
					break;
				}
				//���� ���� ���(�ش� Ÿ�ӽ����� 1 ���ϰ� ť�� �� ��(bottom)����)
				else if(page[i] == x) {
					timestamp[x-1]++;
					int index = getnullindex(page) - 1;
					while(i != (s-1)) {
						page[i] = page[i + 1];
						i++;
					}
					if(index != s) {
						page[index] = x;
						//page[index][1] = pagecount;
					}
					else {
						page[s - 1] = x;
						//page[s - 1][1] = pagecount;
					}
					break;
				}
			}
			//���� �������� ���� ���(Ÿ�ӽ������� ���� ���� �ε��� �ڸ� �ٲ�)
			if(i == s) {
				int index = getMinindex(page,timestamp);
				
				while(index != s-1) {
					page[index] = page[index + 1];
					index++;
				}
				page[s - 1] = x;
				timestamp[page[s-1] - 1]++;
				count++;
			}
			System.out.println("\n" + k + "��° ȣ��");
			k++;
			printState(page, timestamp);
		}
		System.out.println("\n�� ������ ������ �� : " + count + "ȸ");
		
	}
	public int getMinindex(int page[], int timestamp[]) {
		int min = timestamp[page[0] - 1];
		int minIndex = 0;
		for(int i = 0; i < page.length; i++) {
			if(min > timestamp[page[i]-1]) {
				min = timestamp[page[i]-1];
				minIndex = i;
			}
		}
		return minIndex;
	}
	//�������� 0�� �����ϴ��� �˻��Ͽ� ������ ������ ȣ��� 0���� �ٲ��� �ʵ���
	public int getnullindex(int page[]) {
		int i;
		for(i = 0; i < page.length; i++) {
			if(page[i] == 0)
				return i;
		}
		return i;
	}
	public void addTimestamp(int x) {
		timestamp[x-1]++; 	//1~9
	}
	public void printState(int page[], int timestamp[]) {
  		for(int i = 0; i <page.length; i++)
  			System.out.println("    " + page[i]);
  		System.out.print("Ÿ�ӽ����� �� : ");
		for(int i = 0; i < page.length; i++) {
			
			int x = page[i] - 1;
			if(x < 0) {
				System.out.print(i + 1 + ": " + 0 + ", ");
			}
			else
				System.out.print(i + 1 + ": " + timestamp[x] + ", ");
		}
		System.out.println();
  	}
	public static void main(String []args) {
		new LFU();
	}
}