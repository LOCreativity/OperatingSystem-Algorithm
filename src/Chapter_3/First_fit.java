package Chapter_3;

public class First_fit {
	//First_fit
	public First_fit(int blocks[], int blockindex, int processes[], int processindex) {
		
		int []allocate = new int[processindex];
		
		//아직 할당이 되지 않은 상태
		for(int i = 0; i < processindex; i++) {
			allocate[i] = -1;
		}
		
		for(int i = 0; i < processindex; i++) {
			for(int j = 0; j < blockindex; j++) {
				//블록 크기보다 프로세스 크기가 작으면
				if(blocks[j] >= processes[i]) {
					allocate[i] = j;			//프로세스 i번을 블록 j번에 할당
					blocks[j] -= processes[i];	//블록 사이즈 감소
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
	
	//메인
	public static void main(String []args) {
		
		//각 블록과 프로세스 크기
		int []blocks = {100, 50, 30, 120, 35};	//블록 공간
		int []processes = {20, 60, 70, 40};		//프로세스 사이즈
		
		//인덱스
		int blockindex = blocks.length;
		int processindex = processes.length;
		
		new First_fit(blocks, blockindex, processes, processindex);
		
	}
}
