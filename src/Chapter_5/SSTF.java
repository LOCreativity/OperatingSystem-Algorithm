package Chapter_5;

import java.util.Scanner;

//SSTF : ���� ����� ��ġ�� ���� ����� ��û�� ���� ó���ϴ� ���

public class SSTF {
	private static Scanner scanner = new Scanner(System.in);
	private int sum = 0;	//�� �̵��Ÿ� ���
	private int start;		//��ũ ������
	private int [] arr = new int[0]; //��ũ ť
	private int count = 0;	//sum ������ �����ϱ� ���� ��ũ ť ����
	private int []remove = new int[0]; //�� �� ���� ��ũ ���
	private int x; //��ũ ��ġ
	private int k; //��ũ ���� Ȯ��
	private boolean a = false; //remove ���� �ϱ� ���� �ο� ����()
	public SSTF() {
		
		//�ʱ� ��� ��ġ ����
		System.out.print("���� ��� ��ġ : ");
		start = scanner.nextInt();
		
		System.out.println("��ũ ť ����(-1 �Է½� ����)");
		input();
		count = arr.length;
		
		//�����ٸ� ���� �� ��ũ ����
		System.out.println("��ũ �����ٸ� ���� �� �߰��� ���� ��ũ�� �ֳ���?(������ -1 �Է�, ������ �ƹ��ų�)");
		k = scanner.nextInt();
		if(k != -1) {
			System.out.println("��ũ �߰�(-1 �Է½� ����)");
			input();
			count = arr.length;
		}
		a = true;
		//��ũ ť ����
		System.out.println("��ũ �����ٸ� ����");
		addSum(0);
		
		//���� �� �߰��� ���� ��ũ ����
		System.out.println("��� ������ ��ġ�� �߰��� ���� ��ũ ť�� �ֳ���?(������ -1 �Է�, ������ �ƹ��ų�)");
		k = scanner.nextInt();
		if(k != -1) {
			System.out.println("��ũ �߰�(-1 �Է½� ����)");
			input();
			addSum(count);
		}
		
		//�� �̵��Ÿ�
		System.out.println("�� ��� �̵��Ÿ� : " + sum);
		
		scanner.close();
	}
	//�迭 ��� �߰��ϴ� �޼ҵ�
	public int []addArray(int origin[], int x){
		
		int []newArr = new int[origin.length + 1];
		
		for(int i = 0; i < origin.length; i++) {
			newArr[i] = origin[i];
		}
		newArr[origin.length] = x; 
		return newArr;
	}
	//��ũ ť �Է�
	public void input() {
		while(true) {
			x = scanner.nextInt();
			if(x == -1) 
				break;
			
			arr = addArray(arr, x);
		}
		//remove ���̰� 0�� ��(ó�� ������ ��)
		if(!a) {
			remove = new int[arr.length];
			for(int i = 0; i < arr.length; i++)
				remove[i] = -1;
		}
		//remove ���̰� 0�� �ƴ� ��(�߰��� ���� ��ũ�� ���� ��)
		else { 
			remove = updateRemove(remove);
		}
	}
	//��ũ �����ٸ� �Ÿ� ���ϴ� �޼ҵ�
	//count = 0(ó�� ������ ��), count != 0(�߰��� ���� ��ũ�� ���� ��)
	public void addSum(int count) {
		for(int i = count; i < arr.length; i++) {
			int index = getMin();
			 
 			if(start >= arr[index]) 
				sum += start - arr[index];		//�Ÿ� ������ ������ �ʰ�
			else
				sum += arr[index] - start;
			start = arr[index];
			System.out.println("sum = " + sum);
		}
	}
	//���� �������� �ּ� �Ÿ��� ����
	public int getMin() {
		int minindex = 0;
		int tmp = -1;
		int min = -1;
		for(int i = 0; i < arr.length; i++) {
			//�� �� �̵��ߴ� ������ Ȯ��
			if(checkprior(remove[i])) {
				if(start >= arr[i]) {
					tmp = start - arr[i];
				}
				else if(start < arr[i]) 
					tmp = arr[i] - start;
			}
			if(min == -1 && tmp != -1) {
				min = tmp;
				minindex = i;
			}
			else{
				if(min >= tmp && checkprior(remove[i])) {
					min = tmp;
					minindex = i;
				}
			}
		}
		System.out.println("min = " + min);
		remove[minindex] = arr[minindex];
		return minindex;
	}
	//�߰��� ���� ��ũ�� ���� �� remove ������Ʈ
	public int[] updateRemove(int oldremove[]) {
		int []newremove = new int[arr.length];
		//������ remove�����͸� �ű�
		for(int i = 0; i < oldremove.length; i++)
			newremove[i] = arr[i];
		//�߰��� ��ũ�� ������ŭ -1 �߰�
		for(int i = oldremove.length; i < arr.length; i++)
			newremove[i] = -1;
		return newremove;
	}
	//������ ó���ߴ� ��ũ���� Ȯ��
	public boolean checkprior(int remove) {
		for(int i = 0; i < arr.length; i++) {
			if((remove == arr[i]))
				return false;
		}
		return true;
	}
	public static void main(String []args) {
		new SSTF();
	}
}