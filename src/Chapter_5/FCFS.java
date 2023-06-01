package Chapter_5;

import java.util.Scanner;

//��û ť�� ���� ������ ��û�� �켱������ ����

public class FCFS {
	private static Scanner scanner = new Scanner(System.in);
	private int sum = 0;
	private int start;
	int [] arr = new int[0];
	int count = 0;
	public FCFS() {
		
		System.out.print("���� ��� ��ġ : ");
		start = scanner.nextInt();
		
		System.out.println("��ũ ť ����(-1 �Է½� ����)");
		input();
		
		System.out.println("�߰� ���� ��ũ ť�� ������ �Է�(-1 �Է½� ����)");
		input();
		
		System.out.println("�� ��� �̵��Ÿ� : " + sum);
		
		scanner.close();
	}
	public int []addArray(int origin[], int x){
		
		int []newArr = new int[origin.length + 1];
		
		for(int i = 0; i < origin.length; i++) {
			newArr[i] = origin[i];
		}
		
		newArr[origin.length] = x; 
		return newArr;
	}
	public void input() {
		while(true) {
			int x = scanner.nextInt();
			if(x == -1)
				break;
			if(start >= x)
				sum += start - x;
			else
				sum += x - start;
			arr = addArray(arr, x);
			start = arr[count];
			count++;
		}
	}
	public static void main(String []args) {
		new FCFS();
	}
}