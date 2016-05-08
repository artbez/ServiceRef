package services;

import java.lang.reflect.Field;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DOMClasses.ExampleDOMTwo;

public class ReflectionJdbcDaoImpl<T> implements ReflectionJdbcDao<T>{

	@Override
	public void insert(T object) {
		// TODO Auto-generated method stub
		Class<? extends Object> clazz = object.getClass();
		System.out.println(clazz.getSimpleName());
		Field[] fields = clazz.getFields();
		String fi = "";
		for (Field field : fields) {
			if (!field.getName().equals("id")) {
			System.out.println(field.getName());
			fi += field.getName() + ",";}
		}
		fi = fi.substring(0, fi.length() - 1);
		System.out.println("fi = " + fi);
		Configuration cfg=new Configuration(); 
		cfg.configure("hibernate.cfg.xml");
		//populates the data of the configuration file	
		//creating seession factory object 
		SessionFactory factory=cfg.buildSessionFactory();	
		//creating session object 
		Session session=factory.openSession(); 
		//Query query = session.createQuery("insert into :className ( :fields )");
		//System.out.println(query.toString());
		//query.setParameter("className", clazz.getSimpleName());
		//query.setParameter("fields", fi);
		//System.out.println(query.toString());
		//int result = query.executeUpdate();
		session.save(object);
		System.out.println("successfully saved"); 
	}

	@Override
	public void update(T object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByKey(T key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T selectByKey(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
