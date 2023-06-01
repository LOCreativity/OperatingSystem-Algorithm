package Chapter_7;

/*
 * 
Allocation : 할당량
Need : 필요량
Max : 요구량
Available : 잔여량

-1단계
Workers = Available(임시변수)
Finish[i] = false(초기화)

-2단계(해당하는 값이 존재하면 3단계 없으면 4단계)
Finish[i] = false
Need[i] <= Work

-3단계(수행 후 2단계로)
Work[i] = Work[i] + Allocation[i]
Finish[i] = true;

-4단계
모든 finish의 값이 true이면 안전상태
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
    	//finish 초기화
    	for(int i = 0; i < p; i++)
    		finish[i] = false;
    	//각 프로세스 값 설정
    	setProcess(p, s);
    	//available 설정
    	System.out.println("available 값 설정(띄어쓰기로 구분하여 입력 후 엔터)");
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
    		System.out.println("안전 상태");
    		System.out.print("수행 순서 : ");
    		for(int i = 0; i< p; i++) {
    			System.out.print(pNum[i] + 1 + " ");
    		}
    	}
    	else
    		System.out.println("교착 상태 발생");
    	
    }
    public void setProcess(int p, int s) {
    	for(int i = 1; i <= p; i++) {
    		System.out.println("p" + i + " allocation 설정(띄어쓰기로 구분하여 입력 후 엔터)");
    		for(int j = 0; j < s; j++)
    			allocation[i-1][j] = scanner.nextInt();
    		System.out.println("p" + i + " max 설정(띄어쓰기로 구분하여 입력 후 엔터)");
    		for(int j = 0; j < s; j++)
    			max[i-1][j] = scanner.nextInt();
    		for(int j = 0; j < s; j++) {
    			need[i-1][j] = max[i-1][j] - allocation[i-1][j];
    			if(need[i-1][j] < 0) {
    				System.out.println("need 값에 음수가 포함되어 있습니다. allocation과 max 다시 입력");
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
    			//work가 필요량보다 작을 경우
    			if(need[i][j] > work[j])
    				break;
    		}
    		//work가 필요량보다 크거나 같을 경우
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
    	System.out.print("프로세스 개수 입력 : ");
    	int p = scanner.nextInt();
    	System.out.print("자원 개수 입력 : ");
    	int s = scanner.nextInt();
    	new Bankers(p, s);
    	scanner.close();
    }
}