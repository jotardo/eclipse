package oop;

public class StockTest {
public static void main(String[] args) {
	Stock s1 = new Stock("ORCL", "Oracle Corporation");
	s1.setPreviousClosingPrice(34.5d);
	s1.setCurrentPrice(34.35d);
	System.out.println(s1.getChangePercent());
}
}
