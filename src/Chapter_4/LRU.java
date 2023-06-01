package Chapter_4;

import java.util.Scanner;

public class LRU{
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("페이지 프레임 개수 설정 : ");
		int s = scanner.nextInt(); 		//캐시 넣을 수 있는 개수
		LRU ob = new LRU();
		System.out.println("\n1번 : 타임-스탬프용 카운터 메소드");
		System.out.println("2번 : 스택을 이용한 메소드\n");
		System.out.print("하나를 택하세요 : ");
		while(true) {
			int select = scanner.nextInt();
			if(select == 1) {
				ob.countMethod(s);
				break;
			}
			else if(select == 2) {
				ob.stackMethod(s);
				break;
			}
			else {
				System.out.print("잘못입력하셨습니다. 다시 입력하세요 : ");
			}
		}
		scanner.close();
	}
	public void countMethod(int s) {
		int[] page = new int[s];
		int[] timestamp = new int[s]; //타임스탬프 카운터
		int count = 0;				  //페이지 부재 카운트
		int k = 1; 					  //호출 횟수
		int i;
		while(true) {
			System.out.print("\n페이지를 호출하세요(-1 입력시 종료) : ");
			int x= scanner.nextInt();
			if(x == -1)
				break; 
			plusCount(page, timestamp);
			//같은 값이 있는지 찾는 반복문
			for(i = 0; i < s; i++) {
				//초기 상태(0, 0, 0, .... , 0)일 경우
				if(page[i] == 0) {
					page[i] = x;
					count++;
					break;
				}
				//값이 있을 경우(해당 타임스탬프만 0으로 바꿈)
				else if(page[i] == x) {
					timestamp[i] = 0;
					break;
				}
			}
			//값이 존재하지 않을 경우(타임스탬프가 가장 큰 인덱스 자리 바꿈)
			if(i == s) {	
				int index = getMaxindex(timestamp);
				page[index] = x;
				timestamp[index] = 0;
				count++;
			}
			System.out.println("\n" + k + "번째 호출");
			k++;
			printState(page);
			System.out.print("타임스탬프 값 : ");
			for(int n = 0; n < s; n++) {
				System.out.print(n+1 + " : " + timestamp[n] + ", ");
			}
			System.out.println();
		}
		System.out.println("\n총 페이지 부재의 수 : " + count + "회");
	}
	public int getMaxindex(int timestamp[]) {
		int max = timestamp[0];
		int maxIndex = 0;
		for(int i = 0; i < timestamp.length; i++) {
			if(max < timestamp[i]) {
				max = timestamp[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	public void plusCount(int page[], int timestamp[]) {
		for(int i = 0; i < timestamp.length; i++) {
			if(page[i] == 0) {}
			else
				timestamp[i]++;
		}
	}
	
	public void stackMethod(int s) {
		int[] page = new int[s];
		int count = 0;			//페이지 부재 카운트
		int k = 1;				//호출 횟수
		while(true) {
		  int i;
		  System.out.print("\n페이지를 호출하세요(-1 입력시 종료) : ");
		  int x = scanner.nextInt();
		  if(x == -1)
			  break;
		  // 같은 값이 있는지 찾는 반복문
		  for (i = 0; i < s; i++) {
			  // 같은 값이 있다면
			  if (page[i] == x) {
			  // 그 자리부터 앞에서 차례로 뒤로 밀어줌
				  while(i != 0){
					  page[i] = page[i-1];
					  i--;
				  }
				  // 제일 앞에 값 입력
				  page[0] = x;
				  break;
			  }
		  }
		  // 반복문을 끝까지 돌았을 때 제일 앞에 값을 입력해줌
		  if(i == s){
			  while(i != 1){
				  i--;
				  page[i] = page[i-1];
			  }
			  page[0] = x;
			  count++;	//반복문을 끝까지 돌았다는 것은 값이 존재하지 않았을 때(페이지 부재)
		  }
		  System.out.println("\n" + k + "번째 호출");
		  k++;
		  printState(page);
	  }
	  System.out.println("\n총 페이지 부재의 수 : " + count + "회");
  	}
  	//현재 상태를 프린트하는 메소드
  	public void printState(int[] arr) {
  		for(int i = 0; i <arr.length; i++)
  			System.out.println("    " + arr[i]);
  	}
}