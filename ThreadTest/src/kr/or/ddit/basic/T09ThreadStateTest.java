package kr.or.ddit.basic;

public class T09ThreadStateTest {
/*
	< 스레드의 상태 >
	
	- new : 스레드 생성돠고 아직 state()가 호출되지 않는 상태
	- runnable : 실행 중 또는 실행 가능한 상태
	- blocked : 동기화블록에 의해서 일시 정지된 상태(Lock이 풀릴때까지 기다리는 상태)
	- waiting, timed_Waiting : 스레드 작업이 종료되지 않았지만 실행 가능하지 않는 일시정지 상태
	                          timed_Waiting은 일시정지 시간이 지정된 경우임
	- terminated : 스레드의 작업이 종료된 상태
*/
	public static void main(String[] args) {
		Thread th = new StatePrintThread(new TargetThread());
		th.start();		
	}
}

// 스레드의 상태를 출력하는 클래스( 이클래스도 스레드로 작성)
class StatePrintThread extends Thread {
	private Thread targetThread;

	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		// Thread의 상태 구하기
		Thread.State state = targetThread.getState();
		
		System.out.println("타켓 스레드의 상태 : state");
		
		if(state == Thread.State.NEW) {
			targetThread.start();
		}
		
		if(state == Thread.State.TERMINATED) {
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i = 0; i < 20000000L; i++) {
			//시간지연
		}
		
		try {
			Thread.sleep(1500);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}