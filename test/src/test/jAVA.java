package test;

import java.util.stream.IntStream;

public class jAVA {
	public static void main(final String[] args) {
		Integer ias = IntStream.range(1, 999).filter(i -> i % 3 == 0 || i % 5 == 0).reduce(0, Integer::sum);
		System.out.println(ias);
	}
}
