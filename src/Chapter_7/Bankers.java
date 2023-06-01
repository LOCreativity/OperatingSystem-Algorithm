package Chapter_7;

/*
 * 
Allocation : �Ҵ緮
Need : �ʿ䷮
Max : �䱸��
Available : �ܿ���

-1�ܰ�
Workers = Available(�ӽú���)
Finish[i] = false(�ʱ�ȭ)

-2�ܰ�(�ش��ϴ� ���� �����ϸ� 3�ܰ� ������ 4�ܰ�)
Finish[i] = false
Need[i] <= Work

-3�ܰ�(���� �� 2�ܰ��)
Work[i] = Work[i] + Allocation[i]
Finish[i] = true;

-4�ܰ�
��� finish�� ���� true�̸� ��������
*/
import java.util.*;

public class Bankers {
    private int allocation[][], need[][], max[][], available[];
    private int []work = new int[3];
    private boolean []finish;
    private static Scanner scanner = new Scanner(System.in);
    private int []pNum;
    public Bankers(int p, int s) {
    	allocation = new int[p][s];
    	need = new int[p][s];
    	max = new int[p][s];
    	available = new int[s];
    	pNum = new int[p];
    	finish = new boolean[p];
    	//finish �ʱ�ȭ
    	for(int i = 0; i < p; i++)
    		finish[i] = false;
    	//�� ���μ��� �� ����
    	setProcess(p, s);
    	//available ����
    	System.out.println("available �� ����(����� �����Ͽ� �Է� �� ����)");
    	for(int i = 0; i < s; i++) {
    		available[i] = scanner.nextInt();
    		work[i] = available[i];
    	}
    	for(int i = 0; i < p; i++) {
    		int index = searchIndex(work, need);
    		for(int j = 0; j < s; j++)
    			work[j] = work[j] + allocation[index][j];
    		finish[index] = true;
    		pNum[i] = index;
    	}
    	for(int i= 0; i < p; i++) {
    		System.out.println("Finish" + i + " = "+ finish[i]);
    	}
    	
    	if(checkFinish(finish)) {
    		System.out.println("���� ����");
    		System.out.print("���� ���� : ");
    		for(int i = 0; i< p; i++) {
    			System.out.print(pNum[i] + 1 + " ");
    		}
    	}
    	else
    		System.out.println("���� ���� �߻�");
    	
    }
    public void setProcess(int p, int s) {
    	for(int i = 1; i <= p; i++) {
    		System.out.println("p" + i + " allocation ����(����� �����Ͽ� �Է� �� ����)");
    		for(int j = 0; j < s; j++)
    			allocation[i-1][j] = scanner.nextInt();
    		System.out.println("p" + i + " max ����(����� �����Ͽ� �Է� �� ����)");
    		for(int j = 0; j < s; j++)
    			max[i-1][j] = scanner.nextInt();
    		for(int j = 0; j < s; j++) {
    			need[i-1][j] = max[i-1][j] - allocation[i-1][j];
    			if(need[i-1][j] < 0) {
    				System.out.println("need ���� ������ ���ԵǾ� �ֽ��ϴ�. allocation�� max �ٽ� �Է�");
    				i--;
    				break;
    			}
    		}
    	}
    }
    public int searchIndex(int work[], int need[][]) {
    	int index = -1;
    	int j;
    	for(int i = 0; i < need.length; i++) {
    		for(j = 0; j < work.length; j++) {
    			//work�� �ʿ䷮���� ���� ���
    			if(need[i][j] > work[j])
    				break;
    		}
    		//work�� �ʿ䷮���� ũ�ų� ���� ���
    		if((work.length == j) && (finish[i] == false)) {
    			index = i;
    			break;
    		}
    	}
    	return index;
    }
    public boolean checkFinish(boolean finish[]) {
    	for(int i = 0; i < finish.length; i++) {
    		if(!finish[i])
    			return false;
    	}
    	return true;
    }
    public static void main(String args[]) {
    	System.out.print("���μ��� ���� �Է� : ");
    	int p = scanner.nextInt();
    	System.out.print("�ڿ� ���� �Է� : ");
    	int s = scanner.nextInt();
    	new Bankers(p, s);
    	scanner.close();
    }
}