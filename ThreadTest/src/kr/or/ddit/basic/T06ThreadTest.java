package kr.or.ddit.basic;

import java.util.Random;
import javax.swing.JOptionPane;

public class T06ThreadTest {
	//�Է� ���� Ȯ���� ���� ���� ����
	static boolean inputCheck = false;
	
	public static void main(String[] args) {
		//Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		//th1.start();
		th2.start();
		
		String[] computer = {"����", "����", "��"};
		
		Random random = new Random();
		int idx = random.nextInt(3);
		
		String com = computer[idx];
		
		String str = "";
		do {
			str = JOptionPane.showInputDialog("������������ �Է��Ͻÿ�");
			inputCheck = true;
		} while (!str.equals("����") && !str.equals("����") && !str.equals("��"));
		
		String result = "";
		
		if(com.equals(str)) {
			result = "�����ϴ�.";
		} else if ((com.equals("����")&&str.equals("��")) ||
					(com.equals("����")&&str.equals("����")) ||
					(com.equals("��")&&str.equals("����"))) {
			result = "��ǻ�� ��";
		} else {
			result = "�ΰ� ��";
		}
		
		System.out.println("===���===");
		System.out.println("��ǻ��:"+com);
		System.out.println("���̸�:"+str);
		System.out.println("���:"+result);
	}
}

////�Էµ����� �ޱ�
//class DataInput extends Thread {
//	@Override
//	public void run() {
//		String str = JOptionPane.showInputDialog("�Է�(���ѽð� 5��) : ");
//		
//		//�Է��� �Ϸ�Ǹ� inputCheck�� true�� ��ȯ�Ѵ�.
//		System.out.println("�Է��� ���� " + str + "�Դϴ�.");
//		T06ThreadTest.inputCheck = true;
//	}
//}

//�Է� �ð� ����
class CountDown extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1 ; i--) {
			//�Է��� �Ϸ� �Ǿ����� ���θ� �˻��ϰ� �Է��� �Ϸ�Ǹ� run()����
			if(T06ThreadTest.inputCheck == true) {
				return; //����
			} else {
				System.out.println(i);
				if(i == 1) {
					System.out.println("�ð��� ����˴ϴ�.");
					System.exit(0);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
}