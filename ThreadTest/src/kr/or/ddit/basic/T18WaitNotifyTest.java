package kr.or.ddit.basic;

/*
 *  wait() => ����ȭ �������� ���� Ǯ�� Wait-Set ����(������ü�� ����)����
 *  		  �̵� ��Ų��.
 *  notify() �Ǵ� notifyAll() => Wait-Set ������ �ִ� �����带 �ƿ��� 
 *                              ����� �� �ֵ��� �Ѵ�.
 *                              (notify() -> �ϳ� / notifyAll() -> ����)
 *  => wait() �� notify(), notifyAll() �� ����ȭ���������� ������ �� �ְ�, Object Ŭ�������� �����ϴ� �޼����̴�.
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

//�������� ����� Ŭ����
class WorkObject{
	 public synchronized void methodA(){
		 System.out.println("methodA()�޼��� ���� ���� ��...");
		 
		 notify();
		 
		 try {
			wait();//���� ���� ������ �ְ� ��� notify ���� ����
		} catch (InterruptedException e) {
			
		}
		 System.out.println("methodA()�޼��� ���� ���� ��...");
	 }
	 
	 
	 public synchronized void methodB(){
		 System.out.println("methodB()�޼��� ���� �۾� ���� ��...");
		 
		 notify();
		 
		 try {
			wait();
		} catch (InterruptedException e) {
			
		}
		 System.out.println("methodB()�޼��� ���� �۾� ���� ��...");
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
		//�������� ������� �𸣴ϱ� A,B�Ѵ� ���ֱ�
		synchronized (workObj) {
			workObj.notify();
		}
	}
	 
	 
 }
 
 
 //WorkObject�� methodB()�޼��带 ȣ���ϴ� ������
 class ThreadB extends Thread{
	private WorkObject workObj;

	//������
	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	} 
    	 
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.methodB();
		}
		//�������� ������� �𸣴ϱ� A,B�Ѵ� ���ֱ�
		synchronized (workObj) {
			workObj.notify();
		}
	}
	 
	 
 }