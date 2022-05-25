package kr.or.ddit.basic;

public class T12TheadStopTest {
	public static void main(String[] args) {
		ThreadStopEx1 th1 = new ThreadStopEx1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//th1.stop(); <-- 자원정비, 실행 종료를 출력하지 않고
		
		//th1.setStop(true); <--플래그 값을 이용하여 종료
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt();
	}
}

class ThreadStopEx1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원정리 중...");
		System.out.println("실행 종료");
	}
}

//interrupt()를 이용하여 스레드클래스 멈추게 하기
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
		//방법1. sleep()이나 join() 등을 사용했을 때, interrupt()를 호출하면 InterruptedException이 발생한다.ㄴ
		/*try {
			while(true) {
				System.out.println("스레드 처리 중...");
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {}
			
		System.out.println("자원 관리 중...");
		System.out.println("스레드 종료")*/
		
		//방법2. interrupt()가 호출 되었는지 검사하기
		
		while(true) {
			System.out.println("스레드 처리 중...");
			//검사방법1 => 스레드의 인스턴스객체용 메서드를 이용하는 방법
			/*if(this.isInterrupted()) {
				System.out.println("인스턴스용 isInterrupted() 호출됨");
				break;
			}*/
			
			//검사방법2 => 스레드의 static메서
			
		}
	}
}