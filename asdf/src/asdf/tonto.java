package asdf;

import java.util.Random;

public class tonto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float mark;
		Random random = new Random();
		mark = (random.nextFloat()* 100f);
		System.out.println(mark);

		while(mark <100f){
			mark = (random.nextFloat()* 100f + 0.0001f);
			System.out.printf("%2.2f%n", mark);
		}
		System.out.printf("%2.2f", mark);
//		do{
//		mark = (random.nextFloat()* 100f);
//		System.out.println(mark);
//		}while(mark<(float)99 && mark>(float)101);

	}

}
