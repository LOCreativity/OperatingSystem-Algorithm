package Chapter_3;

import java.util.Arrays;

public class Worst_fit {
	
	public Worst_fit(int blocks[], int blockindex, int processes[], int processindex) {
		int []allocate = new int[processindex];
		int []temp = new int[blockindex];		  //내림차순으로 블록 저장공간을 배열하기 위해 생성
		String []tempSt = new String[blockindex]; //indexOf를 사용하기 위해 생성
		
		for(int i = 0; i < processindex; i++)
			allocate[i] = -1;
		
		for(int i = 0; i < blockindex; i++) {
			temp[i] = blocks[i];	
			tempSt[i] = Integer.toString(blocks[i]); 
		}
		for(int i = 0; i < processindex; i++) {
			int search = - 1;					  //할당된 곳의 인덱스를 찾는 변수
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
		
		System.out.println("\n프로세스 번호\t프로세스 크기\t할당된 블록 번호");
		for(int i = 0; i < processindex; i++) {
			System.out.print((i + 1) + "\t\t" + processes[i] + "\t\t");
			if(allocate[i] != -1)
				System.out.println(allocate[i] + 1);
			else
				System.out.println("할당 안됨");
		}
	}
	
	public static void main(String []args) {
		//각 블록과 프로세스 크기
		int []blocks = {100, 50, 30, 125, 35};
		int []processes = {20, 60, 70, 40};
					
		//인덱스
		int blockindex = blocks.length;
		int processindex = processes.length;
		new Worst_fit(blocks, blockindex, processes, processindex);
	}
}
