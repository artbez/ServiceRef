package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import DOMClasses.ExampleDOMTwo;
import Util.HibernateSessionFactory;
import services.ReflectionJdbcDaoImpl;

public class ReflectionJdbcDaoTest {	
	
	@Test
	public void insertTest() {

		Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		session.beginTransaction();
		Query query = session.createQuery("delete ExampleDOMTwo where objId = '-1'");
		query.executeUpdate();
		session.getTransaction().commit();
		session.getTransaction().begin();
		query = session.createQuery("delete ExampleDOMTwo where objId = '-2'");
		query.executeUpdate();
		session.getTransaction().commit();
		session.getTransaction().begin();
		ReflectionJdbcDaoImpl<ExampleDOMTwo> myRefService = new ReflectionJdbcDaoImpl<>();
		ExampleDOMTwo myEx1 = new ExampleDOMTwo(new Long(-1));
		myEx1.setName("test1");
		ExampleDOMTwo myEx2 = new ExampleDOMTwo(new Long(-2));
		myEx2.setName("test2");
		myRefService.insert(myEx1);
		myRefService.insert(myEx2);
		
		query = session.createQuery("from ExampleDOMTwo where objId = '-1'");
		List<ExampleDOMTwo> list = query.list();
		assertEquals(myEx1.getObjId(), list.get(0).getObjId());
		assertEquals(myEx1.getName(), list.get(0).getName());
		query = session.createQuery("from ExampleDOMTwo where objId = '-2'");
		list = query.list();
		assertEquals(myEx2.getObjId(), list.get(0).getObjId());
		assertEquals(myEx2.getName(), list.get(0).getName());
		
		session.getTransaction().commit();
	    session.close();
		
	}

}
