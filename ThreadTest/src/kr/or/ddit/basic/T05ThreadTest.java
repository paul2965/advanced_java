package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T05ThreadTest {
	/*
	 * ���� �����忡���� ����� �Է� ó���ϱ�
	 * 
	 * */
	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("�ƹ��ų� �Է��ϼ���");
		System.out.println("�Է��� ���� " + str + "�Դϴ�.");
		
		for(int i = 5; i >= 1 ; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
