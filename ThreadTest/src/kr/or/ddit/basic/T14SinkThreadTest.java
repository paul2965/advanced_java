package kr.or.ddit.basic;

public class T14SinkThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		Thread th1 = new WorkThread("1번", sObj);
		Thread th2 = new WorkThread("2번", sObj);
		
		th1.start();
		th2.start();
	}
}

//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 방법1. 메서드자체에 동기화 설정하기
	public /*synchronized*/ void add() {
		for(int i = 0; i <1000000000; i++) {
			//동기화처리 전까지 시간벌기용
		}
		
		//동기화 방법2. 동기화 블럭을 설정하기 => 1,2번 번갈아 가면서 
		//synchronized (this) {			
			int n = sum;
			n += 10;
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + 
					", 합계 : " + sum);
		//}
	}
}

//작업을 수행하는 메서드
class WorkThread extends Thread {
	private ShareObject sObj;
	
	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	} 
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			synchronized (sObj) {				
				sObj.add();
			}
		}
	}
}
