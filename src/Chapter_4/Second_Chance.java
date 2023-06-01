package Chapter_4;

import java.util.Scanner;

//�ʱ� ���� ������Ʈ 0
// ���� �̷������ ��Ʈ 1�� �ٲ�
//������Ʈ�� 1�̸� 2�� ��ȸ�� �ְ� ������Ʈ�� 0���� �ٲ�
//������Ʈ�� 0�̸� �� ��ġ�� �ٲ�


//ó�� �������� ȣ�� �� �� ������Ʈ�� 1�� �����ϴ��� 0���� �����ϴ���
//ȣ�� �ϴ� ����� �ٸ� ȣ�� ��İ� ������

public class Second_Chance{
   private static Scanner scanner = new Scanner(System.in);
   private int s;         //������ ������ ����
   private int [][]page;
   private int count = 0;   //������ ���� ī��Ʈ
   
   public Second_Chance(int s) {
      this.s = s;
      this.page = new int[s][2];
      int n = 1; //ȣ�� Ƚ��
      int i;
      //int x;
      while(true) {
         System.out.print("������ ȣ��(-1 �Է½� ����) : ");
         int x = scanner.nextInt();
         if(x == -1)
            break;
         for(i = 0; i < s; i++) {
            //�������� �ش� ���� �����Ұ��
            if(search(page, x)) {
               int index = searchIndex(page, x);
               page[index][1] = 1;
               break;
            }
            //������ ������ ������ �� �Ҵ��� �ȵǾ��� ��(ex 8, 0 ,0)
            else if(page[i][0] == 0) {
               page[i][0] = x;
               count++;
               break;
            }
         }
         //�ݺ����� �� ������ ���(���� ���� ���)
         if(i == s) {
            int k;
            count++;
            //������Ʈ �˻�
            for(k = 0; k < s; k++) {
               //������Ʈ 1�� ��� 0���� �ٲ�(2�� ��ȸ)
               if(page[k][1] == 1) {
                  page[k][1] = 0;
               }
               //������Ʈ 0�� ���
               else {
                  page[k][0] = x;
                  page[k][1] = 1;
                  break;
               }
            }
            //��� ������Ʈ�� 1�̾��� ���(�ѹ��� ������ ���)
            if(k == s) {
               page[0][0] = x;
               page[0][1] = 1;
            }
         }
         System.out.println("\n" + n + "��° ȣ��");
         n++;
         printState(page);
      }
      System.out.println("\n�� ������ ������ �� : " + count + "ȸ");
    }
   //search�� searchIndex ��ĥ �� ������
   //���� �� �ִ��� ã�� �޼ҵ�
   public boolean search(int page[][], int x) {
      for(int i = 0 ; i < page.length; i++) {
         if(page[i][0] == x) {
            return true;
         }
      }
      return false;
   }
   //���� �� ���� �� �� �ε��� ã�� �޼ҵ�
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
         System.out.println(i + "��° : [" + page[i][0] + ", " + page[i][1] + "]");
      }
      System.out.println();
   }
   public static void main(String []args) {
      
      System.out.print("������ ������ ���� �Է� : ");
      int s = scanner.nextInt();
      
      new Second_Chance(s);
      scanner.close();
   }
}