package kr.or.ddit.basic;

public class T07ThreadPriorityTest {
	public static void main(String[] args) {
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		
		Thread[] ths = new Thread[] {
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest2()
		};
		
		// 우선 순위 부여
		for(int i = 0; i<ths.length;i++) {
			if(i == 5) {
				ths[i].setPriority(10);
			} else {
				ths[i].setPriority(1);
			}
		}
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.print(ch);
			
			for(long i = 1; i<100000000L; i++) {
				//시간 때우기용
			}
		}
	}
}

class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch = 'a'; ch <= 'z'; ch++) {
			System.out.print(ch);
		}
	}

}