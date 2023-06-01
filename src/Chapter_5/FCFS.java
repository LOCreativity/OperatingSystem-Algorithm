package Chapter_5;

import java.util.Scanner;

//요청 큐에 먼저 도착한 요청을 우선적으로 서비스

public class FCFS {
	private static Scanner scanner = new Scanner(System.in);
	private int sum = 0;
	private int start;
	int [] arr = new int[0];
	int count = 0;
	public FCFS() {
		
		System.out.print("현재 헤드 위치 : ");
		start = scanner.nextInt();
		
		System.out.println("디스크 큐 설정(-1 입력시 종료)");
		input();
		
		System.out.println("추가 들어온 디스크 큐가 있으면 입력(-1 입력시 종료)");
		input();
		
		System.out.println("총 헤드 이동거리 : " + sum);
		
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
