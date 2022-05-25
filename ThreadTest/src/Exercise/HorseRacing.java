package Exercise;

import java.util.*;

public class HorseRacing{
	static int rank = 1;
	
	public static void main(String[] args) throws InterruptedException{
		List<Horse> list = new ArrayList<>();
		
		list.add(new Horse("1����"));
		list.add(new Horse("2����"));
		list.add(new Horse("3����"));
		list.add(new Horse("4����"));
		list.add(new Horse("5����"));
		list.add(new Horse("6����"));
		list.add(new Horse("7����"));
		list.add(new Horse("8����"));
		list.add(new Horse("9����"));
		list.add(new Horse("10����"));
		
		System.out.print("�����");
		for (int i = 0; i < 30; i++) {
			Thread.sleep(100);
			System.out.print(".");
		}
		
		System.out.println("\n��� ��� ������");
		
		for (Horse horse : list) {
			horse.start();
		}
		
		for (Horse mal : list) {
			try {
				mal.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(list);
		
		System.out.println("��� ���");
		
		for (Horse horse : list) {
			System.out.println(horse.getName1() + ", " + horse.getRank() + "��");
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	Random random = new Random();
	
	private String name;
	int rank;

	public Horse(String name) {
		this.name = name;
	}

	public String getName1() {
		return name;
	}

	public void setName1(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 50; i++) {
			try {
				Thread.sleep(random.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank(HorseRacing.rank);
		HorseRacing.rank++;
	}
	
	@Override
	public int compareTo(Horse o) {
		if(rank > o.rank) {
			return 1;
		} else {
			return -1;
		}
	}
}