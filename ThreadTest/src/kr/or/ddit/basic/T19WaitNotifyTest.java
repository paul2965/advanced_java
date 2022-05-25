package kr.or.ddit.basic;

public class T19WaitNotifyTest {
	public synchronized static void main(String[] args) {
		DataBox databox = new DataBox();
		
		ProducerThread thP = new ProducerThread(databox);
		ConsumerThread thC = new ConsumerThread(databox);
		
		thP.start();
		thC.start();
	}
}

class DataBox{
	private String data;
	
	/* - get
	 * data변수의 값이 null이면 data변수에 문자열이 채워질 때까지 기다리고
	 * data변수에 값이 있으면 해당 문자열을 반환한다.
	 * 문자열을 반환한 후에는 다시 data변수의 값을 null로 만든다.(변수를 비워주는것)
	 * 
	 */
	
	public synchronized String getData(){
    // 데이터를 반환하는 것이 get
    //(쓰레드레에서 가져오기위해 synchronized: 동기화 처리)
    
		if(data == null){
			try {
				wait();//데이터가 없던애는 깨워져서 실행됨
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		String temp = data;
		
		System.out.println("쓰레드가 읽은 데이터 : " + temp);
		
		data = null;
		notify();//깨워주기
		return temp;
		//데이터를 가져올때 아무것도 없을때 가져오는것은 무의미하기 때문에 
                //있으면 가져오고 없으면 채워질때 까지 기다림
	}
	
	/* - set
	 * data변수에 값이 있으면 data변수가 비워질 때까지 기다리고
	 * data변수의 값이 null이면 새로운 data값을 data변수에 저장한다.
	 * 
	 */
	public synchronized void setData(String data){
		if(this.data != null){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data = data;
		System.out.println("Thread에서 새로 저장한 data : " + this.data);
		System.out.println(Thread.currentThread().getName()
				+ "notify() 호출");
		notify();//깨워주기
	}
}

//데이터를 세팅만 하는 스레드
class ProducerThread extends Thread{
	private DataBox dataBox;

	public ProducerThread(DataBox databox) {
		super("ProducerThread");
		this.dataBox = databox;
	}
	 
	@Override
	public void run() {
		for (int i = 1; i <= 3; i++) {
			String data = "Data -" + i;
			System.out.println("dataBox.setData(" + data + ") 호출");
			dataBox.setData(data);
		}
	}
}

class ConsumerThread extends Thread{
	private DataBox databox;

	public ConsumerThread(DataBox databox) {
		super("ConsumerThread");
		this.databox = databox;
	}
	 
	 @Override
	public void run() {
		for (int i = 1; i <= 3; i++) {
			String data = databox.getData();
			System.out.println("dataBox.getData() : " + data);
		}
	}
}