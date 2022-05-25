package kr.or.ddit.basic;

import java.util.*;

public class T10DisplayCharacterTest {

	static String strRank ="";
	
	public static void main(String[] args) {
		Character[] character = new Character[] {
				new Character("ȫ�浿"),
				new Character("������"),
				new Character("���е�")
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
		System.out.println("��� ��...");
		System.out.println("-----------------");
		System.out.println();
		System.out.println(" ��� ���");
		System.out.println("���� : " + strRank);
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
			System.out.println(name + "�� ��� ���� : " + ch);
			try {
				Thread.sleep(random.nextInt(301)+200);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " ��� ��...");
		T10DisplayCharacterTest.strRank += name + " ";
	}
}