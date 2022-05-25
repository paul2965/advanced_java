package kr.or.ddit.basic;


/*
 * 싱글 쓰레드와 멀티 쓰레드의 시간 차이 예제
 * 
 * 문제) 1부터 20억까지 합계를 구하는데 걸린 시간 체크
 * 전체 합계를 구하는 작업을 싱글과 멀티로 구했을 때, 시간 차이가 얼마큼 나는가?
 * */
public class T04ThreadTest {
	public static void main(String[] args) {
		SumThread sm = new SumThread(1L,2000000000L);
		long startTime = System.currentTimeMillis();
		
		sm.start();
		
		try {
			sm.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("처리시간 : " + (endTime - startTime) + "ms");
		
		System.out.println();
	}
}

class SumThread extends Thread {
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0;
		for(long i = min; i < max; i++) {
			sum += i;
		}
		System.out.printf("min(%d) ~ max(%d) 합계 : sum(%d)",min,max,sum);
	}
}