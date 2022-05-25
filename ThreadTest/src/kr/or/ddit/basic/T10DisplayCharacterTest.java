package kr.or.ddit.basic;

import java.util.*;

public class T10DisplayCharacterTest {

	static String strRank ="";
	
	public static void main(String[] args) {
		Character[] character = new Character[] {
				new Character("홍길동"),
				new Character("일지매"),
				new Character("변학도")
		};
		
		for(int i = 0; i <character.length; i++) {
			character[i].start();
		}
		for(Character ch : character) {
			try {
				ch.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("경기 끝...");
		System.out.println("-----------------");
		System.out.println();
		System.out.println(" 경기 결과");
		System.out.println("순위 : " + strRank);
	}
}

class Character extends Thread {
	Random random = new Random();

	private String name;
	
	public Character(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);
			try {
				Thread.sleep(random.nextInt(301)+200);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " 출력 끝...");
		T10DisplayCharacterTest.strRank += name + " ";
	}
}