package Chapter_3;

import java.util.*;

public class Best_fit {
	//Best_fit
	public Best_fit(int blocks[], int blockindex, int processes[], int processindex) {
		
		System.out.println("처음 블록 상태\n");
		
		printBlock(blocks);
		
		int []allocate = new int[processindex];
		int []temp = new int[blockindex];		  //오름차순으로 블록 저장공간을 배열하기 위해 생성
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
			System.out.println("\n" + (i+1) + "번째 요구량 : " + processes[i] + "\n");
			for(int j = 0; j < blockindex; j++) {
				if(temp[j] >= processes[i]) {
					search = Arrays.asList(tempSt).indexOf(Integer.toString(temp[j]));
					temp[j] -= processes[i];
					tempSt[search] = Integer.toString(temp[j]);
					blocks[search] = temp[j];
					allocate[i] = search;
					System.out.println((i + 1) + "번째 요구량 할당 후 블록의 남은 공백\n");
					printBlock(blocks);
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
		
		System.out.println("\n최종 블록 상태\n");
		printBlock(blocks);
	}
	public void printBlock(int blocks[]) {
		for(int i = 0; i< blocks.length; i++) {
			System.out.print("메모리 공백 길이 " + (i + 1) + '\t');
			System.out.println(blocks[i] + "K");
		}
	}
	public static void main(String []args) {
		
		//각 블록과 프로세스 크기
		int []blocks = {17, 7, 15, 30};
		int []processes = {12, 5, 30};
				
		//인덱스
		int blockindex = blocks.length;
		int processindex = processes.length;

		new Best_fit(blocks, blockindex, processes, processindex);
	}
}
