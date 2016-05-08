package tests;

import DOMClasses.ExampleDOMTwo;
import services.ReflectionJdbcDaoImpl;


public class Test {
	
	public static void main(String[] args) {
		ReflectionJdbcDaoImpl<ExampleDOMTwo> myRefService = new ReflectionJdbcDaoImpl<>();
		ExampleDOMTwo myEx = new ExampleDOMTwo(1, "name3");
		myRefService.deleteByKey(myEx);
	}
}
