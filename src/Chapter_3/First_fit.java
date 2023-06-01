package Chapter_3;

public class First_fit {
	//First_fit
	public First_fit(int blocks[], int blockindex, int processes[], int processindex) {
		
		int []allocate = new int[processindex];
		
		//���� �Ҵ��� ���� ���� ����
		for(int i = 0; i < processindex; i++) {
			allocate[i] = -1;
		}
		
		for(int i = 0; i < processindex; i++) {
			for(int j = 0; j < blockindex; j++) {
				//���� ũ�⺸�� ���μ��� ũ�Ⱑ ������
				if(blocks[j] >= processes[i]) {
					allocate[i] = j;			//���μ��� i���� ���� j���� �Ҵ�
					blocks[j] -= processes[i];	//���� ������ ����
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
	
	//����
	public static void main(String []args) {
		
		//�� ���ϰ� ���μ��� ũ��
		int []blocks = {100, 50, 30, 120, 35};	//���� ����
		int []processes = {20, 60, 70, 40};		//���μ��� ������
		
		//�ε���
		int blockindex = blocks.length;
		int processindex = processes.length;
		
		new First_fit(blocks, blockindex, processes, processindex);
		
	}
}