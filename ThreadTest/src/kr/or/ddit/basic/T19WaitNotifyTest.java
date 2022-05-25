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
	 * data������ ���� null�̸� data������ ���ڿ��� ä���� ������ ��ٸ���
	 * data������ ���� ������ �ش� ���ڿ��� ��ȯ�Ѵ�.
	 * ���ڿ��� ��ȯ�� �Ŀ��� �ٽ� data������ ���� null�� �����.(������ ����ִ°�)
	 * 
	 */
	
	public synchronized String getData(){
    // �����͸� ��ȯ�ϴ� ���� get
    //(�����巹���� ������������ synchronized: ����ȭ ó��)
    
		if(data == null){
			try {
				wait();//�����Ͱ� �����ִ� �������� �����
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		String temp = data;
		
		System.out.println("�����尡 ���� ������ : " + temp);
		
		data = null;
		notify();//�����ֱ�
		return temp;
		//�����͸� �����ö� �ƹ��͵� ������ �������°��� ���ǹ��ϱ� ������ 
                //������ �������� ������ ä������ ���� ��ٸ�
	}
	
	/* - set
	 * data������ ���� ������ data������ ����� ������ ��ٸ���
	 * data������ ���� null�̸� ���ο� data���� data������ �����Ѵ�.
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
		System.out.println("Thread���� ���� ������ data : " + this.data);
		System.out.println(Thread.currentThread().getName()
				+ "notify() ȣ��");
		notify();//�����ֱ�
	}
}

//�����͸� ���ø� �ϴ� ������
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
			System.out.println("dataBox.setData(" + data + ") ȣ��");
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