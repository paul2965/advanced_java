package kr.or.ddit.basic;

/*
 * 멀티스레드 프로그램
 * */
public class T02ThreadTest {
	public static void main(String[] args) {
		// 방법1. Thread 클래스를 상속한 class의 인스턴스를 생성한 후
		//       이 인스턴스의 start() 메서드를 호출한다.
		MyThread1 mythread1 = new MyThread1();
		//스레드를 사용하기 위해선 start() 메서드가 필요하다.
		mythread1.start();
		
		// 방법 2. runnable() 인터페이스를 구혆나 클래스의 인트턴스화 생성 후,
		//        이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생서자의 매개변수를 넘겨준다.
		//        이때, 생성할 때 생성자의 매개변수를 넘겨준다.
		//
		
		// 방법 3. 별도의 익명객체를 이용하는 방법
		
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		// 앞으로 해야할 동작 부분을 넣는 부분
		for(int i = 1 ; i < 20 ; i++) {
			System.out.println("*");
			
			try {
				//Thread.sleep(시간); 1000 => 1초 
				//주어진 시간동안 잠시 작업을 멈춘다.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}