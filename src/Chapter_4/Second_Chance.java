package Chapter_4;

import java.util.Scanner;

//초기 상태 참조비트 0
// 참조 이루어지면 비트 1로 바뀜
//참조비트가 1이면 2차 기회를 주고 참조비트를 0으로 바꿈
//참조비트가 0이면 그 위치를 바꿈


//처음 페이지를 호출 할 때 참조비트를 1로 설정하는지 0으로 설정하는지
//호출 하는 방식이 다른 호출 방식과 같은지

public class Second_Chance{
   private static Scanner scanner = new Scanner(System.in);
   private int s;         //페이지 프레임 개수
   private int [][]page;
   private int count = 0;   //페이지 부재 카운트
   
   public Second_Chance(int s) {
      this.s = s;
      this.page = new int[s][2];
      int n = 1; //호출 횟수
      int i;
      //int x;
      while(true) {
         System.out.print("페이지 호출(-1 입력시 종료) : ");
         int x = scanner.nextInt();
         if(x == -1)
            break;
         for(i = 0; i < s; i++) {
            //페이지에 해당 값이 존재할경우
            if(search(page, x)) {
               int index = searchIndex(page, x);
               page[index][1] = 1;
               break;
            }
            //페이지 프레임 개수가 다 할당이 안되었을 때(ex 8, 0 ,0)
            else if(page[i][0] == 0) {
               page[i][0] = x;
               count++;
               break;
            }
         }
         //반복문을 다 돌았을 경우(값이 없을 경우)
         if(i == s) {
            int k;
            count++;
            //참조비트 검색
            for(k = 0; k < s; k++) {
               //참조비트 1일 경우 0으로 바꿈(2차 기회)
               if(page[k][1] == 1) {
                  page[k][1] = 0;
               }
               //참조비트 0일 경우
               else {
                  page[k][0] = x;
                  page[k][1] = 1;
                  break;
               }
            }
            //모두 참조비트가 1이었을 경우(한바퀴 돌았을 경우)
            if(k == s) {
               page[0][0] = x;
               page[0][1] = 1;
            }
         }
         System.out.println("\n" + n + "번째 호출");
         n++;
         printState(page);
      }
      System.out.println("\n총 페이지 부재의 수 : " + count + "회");
    }
   //search와 searchIndex 합칠 순 없을까
   //같은 값 있는지 찾는 메소드
   public boolean search(int page[][], int x) {
      for(int i = 0 ; i < page.length; i++) {
         if(page[i][0] == x) {
            return true;
         }
      }
      return false;
   }
   //같은 값 있을 때 그 인덱스 찾는 메소드
   public int searchIndex(int page[][], int x) {
      int index = 0;
      for(int i =0; i  < page.length; i++) {
         if(page[i][0] == x) {
            index = i;
            break;
         }
      }
      return index;
   }
   public void printState(int page[][]) {
      for(int i = 0; i < page.length; i++) {
         System.out.println(i + "번째 : [" + page[i][0] + ", " + page[i][1] + "]");
      }
      System.out.println();
   }
   public static void main(String []args) {
      
      System.out.print("페이지 프래임 개수 입력 : ");
      int s = scanner.nextInt();
      
      new Second_Chance(s);
      scanner.close();
   }
}