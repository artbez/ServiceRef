package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import DOMClasses.ExampleDOMTwo;
import Util.HibernateSessionFactory;
import services.ReflectionJdbcDaoImpl;

public class ReflectionJdbcDaoTest {
	
	ReflectionJdbcDaoImpl<ExampleDOMTwo> myRefService;
	
	@Before
	public void prepare() {
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		session.beginTransaction();
		Query query = session.createQuery("delete ExampleDOMTwo where objId = '-1'");
		query.executeUpdate();
		session.getTransaction().commit();
		
		session.beginTransaction();
		query = session.createQuery("delete ExampleDOMTwo where objId = '-2'");
		query.executeUpdate();
		session.getTransaction().commit();
		
		session.close();
		
		myRefService = new ReflectionJdbcDaoImpl<>();
	}
	
	@Test
	public void insertTest() {

		Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		ExampleDOMTwo myEx1 = new ExampleDOMTwo(new Long(-1));
		myEx1.setName("test1");
		
		myRefService.insert(myEx1);
		
		Query query = session.createQuery("from ExampleDOMTwo where objId = '-1'");
		List<ExampleDOMTwo> list = query.list();
		assertEquals(myEx1.getObjId(), list.get(0).getObjId());
		assertEquals(myEx1.getName(), list.get(0).getName());		
	
		session.close();
	}
	
	@Test
	public void updateTest(){
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		ExampleDOMTwo myEx1 = new ExampleDOMTwo(new Long(-1));
		
		myEx1.setName("test1");
		myRefService.insert(myEx1);
		
		myEx1.setName("updatedTest1");
		myRefService.update(myEx1);
		
		Query query = session.createQuery("from ExampleDOMTwo where objId = '-1'");
		List<ExampleDOMTwo> list = query.list();
		assertEquals(myEx1.getName(), list.get(0).getName());
		
		session.close();
	}
	
	@Test
	public void deleteByKeyTest(){
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		
		ExampleDOMTwo myEx1 = new ExampleDOMTwo(new Long(-1));
		myEx1.setName("testDelete");
		
		session.beginTransaction();
		Query query = session.createQuery("from  ExampleDOMTwo where objId = '-1'");
		session.getTransaction().commit();
		boolean exists = query.uniqueResult() != null;
		assertFalse(exists);
		
		myRefService.insert(myEx1);
		session.beginTransaction();
		query = session.createQuery("from  ExampleDOMTwo where objId = '-1'");
		session.getTransaction().commit();
		exists = query.uniqueResult() != null;
		assertTrue(exists);
		
		myRefService.deleteByKey(myEx1);
		session.beginTransaction();
		query = session.createQuery("from  ExampleDOMTwo where objId = '-1'");
		session.getTransaction().commit();
		exists = query.uniqueResult() != null;
		assertFalse(exists);
		
		session.close();
	}
	
	@Test
	public void selectByKeyTest() {
		
		ExampleDOMTwo myEx1 = new ExampleDOMTwo(new Long(-1));
		myEx1.setName("test1");

		myRefService.insert(myEx1);
		
		ExampleDOMTwo myEx2 = new ExampleDOMTwo(new Long(-1));
		myEx2 = myRefService.selectByKey(myEx2);
		
		assertEquals(myEx1.name, myEx2.name);
	}

	@Test
	public void selectAllTest(){

		Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		
		ExampleDOMTwo myEx1 = new ExampleDOMTwo(new Long(-1));
		myEx1.setName("test1");
		myRefService.insert(myEx1);
		
		ExampleDOMTwo myEx2 = new ExampleDOMTwo(new Long(-2));
		myEx1.setName("test2");
		List<ExampleDOMTwo> mList = null;
		try {
			mList = myRefService.selectAll(Class.forName("DOMClasses.ExampleDOMTwo"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from ExampleDOMTwo");
		List<ExampleDOMTwo> expectedList = query.list();
		tx.commit();
		
		boolean allEquals = mList.size() == expectedList.size();
		for (int i = 0; i < mList.size(); ++i) 
			if (mList.get(i).equals(expectedList.get(i)))
				allEquals = false;
		assertTrue(allEquals);
		
		session.close();
	}

}
