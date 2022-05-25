package kr.or.ddit.basic;

/*
 *  wait() => 동기화 영역에서 락을 풀고 Wait-Set 영역(공유객체별 존재)으로
 *  		  이동 시킨다.
 *  notify() 또는 notifyAll() => Wait-Set 영역에 있는 스레드를 꺠워서 
 *                              실행될 수 있도록 한다.
 *                              (notify() -> 하나 / notifyAll() -> 전부)
 *  => wait() 과 notify(), notifyAll() 은 동기화영역에서만 실행할 수 있고, Object 클래스에서 제공하는 메서드이다.
 *  */

public class T18WaitNotifyTest {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
        
        ThreadA thA = new ThreadA(workObj);
        ThreadB thB = new ThreadB(workObj);
        
        thA.start();
        thB.start();
	}
}

//공통으로 사용할 클래스
class WorkObject{
	 public synchronized void methodA(){
		 System.out.println("methodA()메서드 앞쪽 실행 중...");
		 
		 notify();
		 
		 try {
			wait();//먼저 쓰면 깨워줄 애가 없어서 notify 먼저 실행
		} catch (InterruptedException e) {
			
		}
		 System.out.println("methodA()메서드 뒤쪽 실행 중...");
	 }
	 
	 
	 public synchronized void methodB(){
		 System.out.println("methodB()메서드 앞쪽 작업 실행 중...");
		 
		 notify();
		 
		 try {
			wait();
		} catch (InterruptedException e) {
			
		}
		 System.out.println("methodB()메서드 뒤쪽 작업 실행 중...");
	 }
}

class ThreadA extends Thread{
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	} 
    	 
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.methodA();
		}
		//누가먼저 실행될지 모르니까 A,B둘다 해주기
		synchronized (workObj) {
			workObj.notify();
		}
	}
	 
	 
 }
 
 
 //WorkObject의 methodB()메서드를 호출하는 쓰레드
 class ThreadB extends Thread{
	private WorkObject workObj;

	//생성자
	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	} 
    	 
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.methodB();
		}
		//누가먼저 실행될지 모르니까 A,B둘다 해주기
		synchronized (workObj) {
			workObj.notify();
		}
	}
	 
	 
 }