package tests;

import java.util.Iterator;
import java.util.List;

import DOMClasses.ExampleDOMTwo;
import services.ReflectionJdbcDaoImpl;


public class Test {
	
	public static void main(String[] args) {
		ReflectionJdbcDaoImpl<ExampleDOMTwo> myRefService = new ReflectionJdbcDaoImpl<>();
		ExampleDOMTwo myEx = new ExampleDOMTwo(new Long(1));
		myEx.setName("test1");
		myRefService.update(myEx);
		ExampleDOMTwo myEx3 = new ExampleDOMTwo(new Long(2));
		myEx3.setName("test2");
		myRefService.update(myEx3);
		List<ExampleDOMTwo> list = myRefService.selectAll(ExampleDOMTwo.class);
		Iterator<ExampleDOMTwo> it = list.iterator();
		while(it.hasNext()) {
			ExampleDOMTwo current = it.next(); 
			System.out.println(current.getObjId() + " " + current.getName());		
		}
	}
}
