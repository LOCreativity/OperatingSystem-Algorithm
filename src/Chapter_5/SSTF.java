package Chapter_5;

import java.util.Scanner;

//SSTF : 현재 헤ㅡ의 위치에 가장 가까운 요청을 먼저 처리하는 방법

public class SSTF {
	private static Scanner scanner = new Scanner(System.in);
	private int sum = 0;	//총 이동거리 기록
	private int start;		//디스크 시작점
	private int [] arr = new int[0]; //디스크 큐
	private int count = 0;	//sum 시작점 설정하기 위한 디스크 큐 개수
	private int []remove = new int[0]; //한 번 지난 디스크 기록
	private int x; //디스크 위치
	private int k; //디스크 여부 확인
	private boolean a = false; //remove 갱신 하기 위한 부울 변수()
	public SSTF() {
		
		//초기 헤드 위치 설정
		System.out.print("현재 헤드 위치 : ");
		start = scanner.nextInt();
		
		System.out.println("디스크 큐 설정(-1 입력시 종료)");
		input();
		count = arr.length;
		
		//스케줄링 시작 전 디스크 여부
		System.out.println("디스크 스케줄링 시작 전 추가로 들어온 디스크가 있나요?(없으면 -1 입력, 있으면 아무거나)");
		k = scanner.nextInt();
		if(k != -1) {
			System.out.println("디스크 추가(-1 입력시 종료)");
			input();
			count = arr.length;
		}
		a = true;
		//디스크 큐 설정
		System.out.println("디스크 스케줄링 시작");
		addSum(0);
		
		//실행 뒤 추가로 들어온 디스크 여부
		System.out.println("모든 과정을 마치고 추가로 들어온 디스크 큐가 있나요?(없으면 -1 입력, 있으면 아무거나)");
		k = scanner.nextInt();
		if(k != -1) {
			System.out.println("디스크 추가(-1 입력시 종료)");
			input();
			addSum(count);
		}
		
		//총 이동거리
		System.out.println("총 헤드 이동거리 : " + sum);
		
		scanner.close();
	}
	//배열 요소 추가하는 메소드
	public int []addArray(int origin[], int x){
		
		int []newArr = new int[origin.length + 1];
		
		for(int i = 0; i < origin.length; i++) {
			newArr[i] = origin[i];
		}
		newArr[origin.length] = x; 
		return newArr;
	}
	//디스크 큐 입력
	public void input() {
		while(true) {
			x = scanner.nextInt();
			if(x == -1) 
				break;
			
			arr = addArray(arr, x);
		}
		//remove 길이가 0일 때(처음 시작할 때)
		if(!a) {
			remove = new int[arr.length];
			for(int i = 0; i < arr.length; i++)
				remove[i] = -1;
		}
		//remove 길이가 0이 아닐 때(추가로 들어온 디스크가 있을 때)
		else { 
			remove = updateRemove(remove);
		}
	}
	//디스크 스케줄링 거리 합하는 메소드
	//count = 0(처음 시작할 때), count != 0(추가로 들어온 디스크가 있을 때)
	public void addSum(int count) {
		for(int i = count; i < arr.length; i++) {
			int index = getMin();
			 
 			if(start >= arr[index]) 
				sum += start - arr[index];		//거리 음수가 나오지 않게
			else
				sum += arr[index] - start;
			start = arr[index];
			System.out.println("sum = " + sum);
		}
	}
	//현재 지점에서 최소 거리를 구함
	public int getMin() {
		int minindex = 0;
		int tmp = -1;
		int min = -1;
		for(int i = 0; i < arr.length; i++) {
			//한 번 이동했던 곳인지 확인
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
	//추가로 들어온 디스크가 있을 때 remove 업데이트
	public int[] updateRemove(int oldremove[]) {
		int []newremove = new int[arr.length];
		//이전의 remove데이터를 옮김
		for(int i = 0; i < oldremove.length; i++)
			newremove[i] = arr[i];
		//추가된 디스크의 개수만큼 -1 추가
		for(int i = oldremove.length; i < arr.length; i++)
			newremove[i] = -1;
		return newremove;
	}
	//이전에 처리했던 디스크인지 확인
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
