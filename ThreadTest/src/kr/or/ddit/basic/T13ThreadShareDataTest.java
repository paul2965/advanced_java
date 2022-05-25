package kr.or.ddit.basic;

public class T13ThreadShareDataTest {
	/*
	 * �����忡�� �����͸� �������� ����ϴ� ���
	 * 1. �������� ����� �����͸� Ŭ������ �����Ѵ�
	 * 2. �������� ����� Ŭ�����l �ν��Ͻ��� �����
	 * 3. �� �ν��Ͻ��� ������ �����忡 �Ѱ��ش�
	 * 4. ������ ������� �� �ν��Ͻ��� �������� ������ ������ �̿��Ͽ� ���� �����͸� ����Ѵ�.
	 * 
	 * ��) �������� ����ϴ� �����尡 �ְ�, ���� �������� ����ϴ� �����尡 �ִ�.
	 *    �������� ����� �� �� ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.(�� �� �������� �����ϴ� ��ü�� �ʿ��ϴ�)
	 *    */
	public static void main(String[] args) {
		ShareData sd = new ShareData();
		
		Thread th1 = new CalcPIThread(sd);
		Thread th2 = new PrintPIThread(sd);
		th1.start();
		th2.start();
	}
}

class ShareData {
	public double result;
	/*
	 * volatile => ����� ������ �����Ϸ��� ����ȭ ��󿡼� ���ܽ�Ų��.
	 * 			   ��, ���� ���� �Ǵ� ��� ������ ����ȴ�.
	 *             ���� �����忡�� �ϳ��� ������ �Ϻ��ϰ� �ѹ��� �۵��ϵ��� �����ϴ� Ű����(������ ����ȭ)
	 *             */
	public volatile boolean isOk = false; // ������ �Խ� �Ϸ� ���� üũ��
}

//������ ��� ������
class CalcPIThread extends Thread {
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		/*
		 * ������ = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - ...) * 4
		 *          1     3     5     7     9    => �и�
		 *          0     1     2     3     4    => 2�� ���� ��*/
		
		double sum = 0.0;
		
		for(int i = 1; i<= 1500000000; i+=2) {
			if((i/2) % 2 == 0) {
				sum += (1.0/i);
			} else {
				sum -= (1.0/i);
			}
		}
		
		sd.result = sum * 4;
		sd.isOk = true;
	}
}

class PrintPIThread extends Thread {
	private ShareData sd;

	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk) {
				break;
			}
		}
		System.out.println();
		System.out.println(sd.result);
		System.out.println(Math.PI);
	}
}
