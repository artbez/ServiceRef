package tests;

import DOMClasses.ExampleDomOne;
import DOMClasses.ExampleDOMTwo;
import services.ReflectionJdbcDao;
import services.ReflectionJdbcDaoImpl;
import org.hibernate.Session; 
import org.hibernate.SessionFactory; 
import org.hibernate.Transaction; 
import org.hibernate.cfg.Configuration;


public class Test {
	
	public static void main(String[] args) {
		ReflectionJdbcDaoImpl<ExampleDOMTwo> myRefService = new ReflectionJdbcDaoImpl<>();
		ExampleDOMTwo myEx = new ExampleDOMTwo(1, "name2");
		myRefService.insert(myEx);
/*		Configuration cfg=new Configuration(); 
		cfg.configure("hibernate.cfg.xml");
		//populates the data of the configuration file	
		//creating seession factory object 
		SessionFactory factory=cfg.buildSessionFactory();	
		//creating session object 
		Session session=factory.openSession(); 
		//creating transaction object 
		Transaction t=session.beginTransaction(); 
		ExampleDOMTwo e1 = new ExampleDOMTwo(1, "sdf"); 
		session.persist(e1); //persisting the object 
		t.commit(); //transaction is commited 
		session.close(); 
		System.out.println("successfully saved"); 	
*/
	}
}
