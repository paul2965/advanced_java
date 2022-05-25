package kr.or.ddit.basic;

import java.util.Random;
import javax.swing.JOptionPane;

public class T06ThreadTest {
	//입력 여부 확인을 위한 변수 선언
	static boolean inputCheck = false;
	
	public static void main(String[] args) {
		//Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		//th1.start();
		th2.start();
		
		String[] computer = {"가위", "바위", "보"};
		
		Random random = new Random();
		int idx = random.nextInt(3);
		
		String com = computer[idx];
		
		String str = "";
		do {
			str = JOptionPane.showInputDialog("가위바위보를 입력하시오");
			inputCheck = true;
		} while (!str.equals("가위") && !str.equals("바위") && !str.equals("보"));
		
		String result = "";
		
		if(com.equals(str)) {
			result = "비겼습니다.";
		} else if ((com.equals("가위")&&str.equals("보")) ||
					(com.equals("바위")&&str.equals("가위")) ||
					(com.equals("보")&&str.equals("바위"))) {
			result = "컴퓨터 승";
		} else {
			result = "인간 승";
		}
		
		System.out.println("===결과===");
		System.out.println("컴퓨터:"+com);
		System.out.println("게이머:"+str);
		System.out.println("결과:"+result);
	}
}

////입력데이터 받기
//class DataInput extends Thread {
//	@Override
//	public void run() {
//		String str = JOptionPane.showInputDialog("입력(제한시간 5초) : ");
//		
//		//입력이 완료되면 inputCheck를 true로 반환한다.
//		System.out.println("입력한 값은 " + str + "입니다.");
//		T06ThreadTest.inputCheck = true;
//	}
//}

//입력 시간 제한
class CountDown extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1 ; i--) {
			//입력이 완료 되었는지 여부를 검사하고 입력이 완료되면 run()종료
			if(T06ThreadTest.inputCheck == true) {
				return; //종료
			} else {
				System.out.println(i);
				if(i == 1) {
					System.out.println("시간이 종료됩니다.");
					System.exit(0);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
}