import java.util.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer length = (int) (Math.random() * 15+1);
		System.out.println("Se van a generar " + length + " datos");
		System.out.println("Insertando:");
		HashSet<Integer> hset = new HashSet<Integer>();
		LinkedHashSet<Integer> lhset = new LinkedHashSet<Integer>();
		TreeSet<Integer> tset = new TreeSet<Integer>();
		for (int i = 0; i < length; i++) {
			Integer num;
			num = (int) (Math.random() * 7001 + 1000);
			System.out.print(num + " * ");
			hset.add(num);
			lhset.add(num);
			tset.add(num);
		}

		System.out.println("\nValores de HashSet");
		for (Integer i : hset) {
			System.out.print(i + " * ");
		}

		System.out.println("\nValores de LinkedHashSet");
		for (Integer i : lhset) {
			System.out.print(i + " * ");
		}

		System.out.println("\nValores de TreeSet");
		for (Integer i : tset) {
			System.out.print(i + " * ");
		}
	}

}
