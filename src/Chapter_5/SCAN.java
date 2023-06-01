package Chapter_5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class SCAN {
	public static void main(String args[]) throws InterruptedException {
		ThreadDisk4 td = new ThreadDisk4();
		ThreadScan4 ts = new ThreadScan4(td);
		ThreadInsert4 ti = new ThreadInsert4(td);
		ts.start();
		ti.start();
		ts.join();
		ti.join();
		System.out.println(td.distanceMove);
	}
}


class ThreadDisk4 {
	static Vector<Integer> disk = new Vector<Integer>();
	static int head = 50;
	int direct;
	int distanceMove = 0;
	int scanTrial;
	int count = 0;
	final int maxdistance = 200;
	ThreadDisk4(){
		disk.add(105);
		disk.add(180);
		disk.add(40);
		disk.add(120);
		disk.add(10);
		disk.add(125);
		disk.add(65);
		disk.add(70);
		scanTrial = disk.size();
		System.out.println("현재 상태");
		for(int i: disk) {
			System.out.print(i+"  ");
		}
		direct = searchDirection();
		
	}
	public synchronized void scanStart() throws InterruptedException {
		System.out.println("\n스캔 알고리즘 시작 \n현재 큐 상태");
		for(int i : disk) {
			System.out.print(i + "  ");
		}
		System.out.println();
		System.out.println("\n현재 헤드 위치: " + head);
		disk.add(head);
		Collections.sort(disk);
		//maxdistance까지 도달한 경우
		if(head != maxdistance && direct == 1) {
			if(disk.get(disk.size()-1) == head) {
				direct = 0;
				distanceMove += (maxdistance - head);
				head = 200;
				System.out.println("헤드 "+ maxdistance + "도달");
				direct = 0;
				disk.remove(disk.size()-1);
				System.out.println("헤드 이동거리: " + distanceMove);
				return;
			}
		}
		else if( head!=0 && direct == 0) {
		//mindistance까지 도달
			 	if(disk.get(0) == head) {
			 		direct = 1;
			 		distanceMove += head;
			 		head = 0;
			 		disk.remove(0);
			 		System.out.println("헤드 0 도달");
			 		direct = 1;
			 		System.out.println("헤드 이동거리: " + distanceMove);
			 		return;
			}
		}
		//왼쪽인 경우
		if(direct == 0) {
				int movehead = 0;
				if(head ==0) {
					distanceMove += disk.get(1);
					head = disk.get(1);
					disk.remove(1);
					disk.remove(0);
					System.out.println("헤드 이동거리: " + distanceMove);
					return;
				}
				for(int i = disk.size()-1 ; i >= 0; i--) {
					movehead = disk.get(i) - head;
					if(movehead == 0) {
						movehead = head- disk.get(i-1);
						head = disk.get(i-1);
						disk.remove(i-1);
						disk.remove(i-1);
						break;
						}
					}
				distanceMove += movehead;
			
			}
		//오른쪽인 경우
		else{
			if(head == maxdistance) {
				distanceMove = maxdistance - disk.get(disk.size()-2);
				head = disk.get(disk.size()-2);
				disk.remove(disk.size()-2);
				disk.remove(disk.size()-1);
				return;
			}
			int movehead = 0;
			for(int i = 0; i<disk.size()-1; i++) {
				movehead = disk.get(i)- head;
				if(movehead == 0) {
					movehead = disk.get(i+1)-head;
					head = disk.get(i+1);
					disk.remove(i+1);
					disk.remove(i);
					break;
				}
			}
			distanceMove += movehead;
		}
		System.out.println();
		System.out.println("이동 후 헤드 위치: "+head);
		System.out.println("헤드 이동거리: " + distanceMove + "\n");
		if(disk.size() == 0 && count !=3) {
			System.out.println("큐가 없습니다.");
			scanTrial++;
			wait();
		}
		
	}
	public synchronized void insert() {
		int rand =(int) (Math.random()*maxdistance) + 1;
		disk.add(rand);
		System.out.println("======새로운 큐 " + rand  +" 들어옴======");
		scanTrial++;
		count++;
		notifyAll();
	}
	public void printDistance() {
		System.out.println("이동거리: "+distanceMove);
	}
	public int returnTrial() {
		return scanTrial;
	}
	public int searchDirection() {
        int direction = 0;
        int min = -1;
        int minindex = 0;
        int tmp;
        for(int i = 0; i < disk.size(); i++) {
           tmp = Math.abs(disk.get(i) - head);
            if(min == -1) {
               min = tmp;
               minindex = i;
            }
            else {
               if(min > tmp) {
                  min = tmp;
                  minindex = i;
               }
            }
        }
        if(disk.get(minindex) < head)
           direction = 0;
        else
           direction = 1;
        return direction;
     }
}

class ThreadInsert4 extends Thread{
	ThreadDisk4 td;
	ThreadInsert4(ThreadDisk4 td){
		this.td = td;
	}
	public void run() {
		for(int i = 0; i < 3;i++) {
		td.insert();
		try {
			int time = (int)(Math.random() * 1000);
			sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}

class ThreadScan4 extends Thread{
	ThreadDisk4 td;
	ThreadScan4(ThreadDisk4 td){
		this.td = td;
	}
	public void run() {
		for(int i = 0; i < td.returnTrial()+1;i++) {
			try {
				td.scanStart();
			
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}