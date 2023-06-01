package Chapter_3;

import java.util.Arrays;

public class Worst_fit {
	
	public Worst_fit(int blocks[], int blockindex, int processes[], int processindex) {
		int []allocate = new int[processindex];
		int []temp = new int[blockindex];		  //������������ ���� ��������� �迭�ϱ� ���� ����
		String []tempSt = new String[blockindex]; //indexOf�� ����ϱ� ���� ����
		
		for(int i = 0; i < processindex; i++)
			allocate[i] = -1;
		
		for(int i = 0; i < blockindex; i++) {
			temp[i] = blocks[i];	
			tempSt[i] = Integer.toString(blocks[i]); 
		}
		for(int i = 0; i < processindex; i++) {
			int search = - 1;					  //�Ҵ�� ���� �ε����� ã�� ����
			Arrays.sort(temp);
			for(int j = blockindex - 1; j >= 0; j--) {
				if(temp[j] >= processes[i]) {
					search = Arrays.asList(tempSt).indexOf(Integer.toString(temp[j]));
					temp[j] -= processes[i];
					tempSt[search] = Integer.toString(temp[j]);
					blocks[search] = temp[j];
					allocate[i] = search;
					break;
				}	
			}
		}
		
		System.out.println("\n���μ��� ��ȣ\t���μ��� ũ��\t�Ҵ�� ���� ��ȣ");
		for(int i = 0; i < processindex; i++) {
			System.out.print((i + 1) + "\t\t" + processes[i] + "\t\t");
			if(allocate[i] != -1)
				System.out.println(allocate[i] + 1);
			else
				System.out.println("�Ҵ� �ȵ�");
		}
	}
	
	public static void main(String []args) {
		//�� ���ϰ� ���μ��� ũ��
		int []blocks = {100, 50, 30, 125, 35};
		int []processes = {20, 60, 70, 40};
					
		//�ε���
		int blockindex = blocks.length;
		int processindex = processes.length;
		new Worst_fit(blocks, blockindex, processes, processindex);
	}
}