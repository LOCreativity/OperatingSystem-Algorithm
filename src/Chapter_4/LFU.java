package Chapter_4;

import java.util.Scanner;

//LFU : 가장 적게 사용되거나 집중적이 아닌 페이지가 교체
//FIFO 방법임 (top : 가장 마지막, bottom : 가장 최근)

public class LFU {
	private Scanner scanner = new Scanner(System.in);
	private int []timestamp = new int[9];
	public LFU() {
		System.out.print("페이지 프레임 개수 설정 : ");
		int s = scanner.nextInt(); 		//캐시 넣을 수 있는 개수
		int[] page = new int[s];
		int count = 0;				  //페이지 부재 카운트
		int pagecount;				  //페이지 호출 카운트
		int k = 1; 					  //호출 횟수
		int i;
		while(true) {
			System.out.print("\n페이지를 호출하세요(-1 입력시 종료) : ");
			int x= scanner.nextInt();
			if(x == -1)
				break; 
			
			//같은 값이 있는지 찾는 반복문
			for(i = 0; i < s; i++) {
				//초기 상태(0, 0, 0, .... , 0)일 경우
				if(page[i] == 0) {
					page[i] = x;
					timestamp[x-1]++; //sd
					count++;
					break;
				}
				//값이 있을 경우(해당 타임스탬프 1 더하고 큐의 맨 앞(bottom)으로)
				else if(page[i] == x) {
					timestamp[x-1]++;
					int index = getnullindex(page) - 1;
					while(i != (s-1)) {
						page[i] = page[i + 1];
						i++;
					}
					if(index != s) {
						page[index] = x;
						//page[index][1] = pagecount;
					}
					else {
						page[s - 1] = x;
						//page[s - 1][1] = pagecount;
					}
					break;
				}
			}
			//값이 존재하지 않을 경우(타임스탬프가 가장 작은 인덱스 자리 바꿈)
			if(i == s) {
				int index = getMinindex(page,timestamp);
				
				while(index != s-1) {
					page[index] = page[index + 1];
					index++;
				}
				page[s - 1] = x;
				timestamp[page[s-1] - 1]++;
				count++;
			}
			System.out.println("\n" + k + "번째 호출");
			k++;
			printState(page, timestamp);
		}
		System.out.println("\n총 페이지 부재의 수 : " + count + "회");
		
	}
	public int getMinindex(int page[], int timestamp[]) {
		int min = timestamp[page[0] - 1];
		int minIndex = 0;
		for(int i = 0; i < page.length; i++) {
			if(min > timestamp[page[i]-1]) {
				min = timestamp[page[i]-1];
				minIndex = i;
			}
		}
		return minIndex;
	}
	//페이지에 0이 존재하는지 검색하여 있으면 페이지 호출시 0값과 바꾸지 않도록
	public int getnullindex(int page[]) {
		int i;
		for(i = 0; i < page.length; i++) {
			if(page[i] == 0)
				return i;
		}
		return i;
	}
	public void addTimestamp(int x) {
		timestamp[x-1]++; 	//1~9
	}
	public void printState(int page[], int timestamp[]) {
  		for(int i = 0; i <page.length; i++)
  			System.out.println("    " + page[i]);
  		System.out.print("타임스탬프 값 : ");
		for(int i = 0; i < page.length; i++) {
			
			int x = page[i] - 1;
			if(x < 0) {
				System.out.print(i + 1 + ": " + 0 + ", ");
			}
			else
				System.out.print(i + 1 + ": " + timestamp[x] + ", ");
		}
		System.out.println();
  	}
	public static void main(String []args) {
		new LFU();
	}
}
