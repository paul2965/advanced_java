package kr.or.ddit.basic;
/*
 * 동기화 처리 예제
 * */
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;


/*
 * Vector, Hashtable 등 예전부터 존재하던 Collection 객체들은
 * 내부에 동기화 처리가 되어 있다.
 * 
 * 그런데, 최근에 새로 구성된 Collection 객체들은 동기화 처리가 되어 있지 않다.
 * 그래서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면
 * 동기화 처리를 한 후에 사용해야 한다.
 * 
 */


public class T17SyncCollectionTest {	
    
	//백터랑 리스트는 내부에 배열이 존재함
	//private static Vector<Integer> vec = new Vector<>();
	
    //동기화 처리가 되지 않은 list
   	//private static ArrayList<Integer> list1 = new ArrayList<>();
   	
   	//동기화 처리를 한 list
   	private static List<Integer> list2 = 
   			//조상쪽 인터페이스 이름
   	Collections.synchronizedList(new ArrayList<Integer>());
	
	public static void main(String[] args) {

		//익명 구현체로 쓰레드 구현
		Runnable r = new Runnable(){
			public void run() {
				for (int i = 0; i < 10000; i++) {
//					vec.add(i);
//					list1.add(i);
					list2.add(i);
					
				}
			}
		};
		//------------------------------------------------------------------------
		
		Thread[] thArr = new Thread[]{
				new Thread(r),	new Thread(r), 	new Thread(r), //각각 쓰레드 한개씩 만개가 들어가야 되서 5만개가 들어가양됨
				new Thread(r), 	new Thread(r), 
				
		};
		
		for(Thread th : thArr){
			th.start();
		}
		
		for(Thread th : thArr){
			try {
				th.join();
			} catch (InterruptedException e) {}
		}
		
//		System.out.println("vec 개수 : "+ vec.size());
//		System.out.println("list1 개수 : "+ list1.size());
		System.out.println("list2 개수 : "+ list2.size());
		
	}

}
