package services;

import java.lang.reflect.Field;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Util.HibernateSessionFactory;

public class ReflectionJdbcDaoImpl<T> implements ReflectionJdbcDao<T>{

	@Override
	public void insert(T object) {
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession(); 
		session.beginTransaction();
		session.save(object);
		session.getTransaction().commit();
	    session.close();
		System.out.println("successfully saved"); 
	}

	@Override
	public void update(T object) {
		// TODO Auto-generated method stub
		Class<? extends Object> clazz = object.getClass();
		System.out.println(clazz.getSimpleName());
		Field[] fields = clazz.getFields();
		String fi = "";
		for (Field field : fields) {
			if (!field.getName().equals("id")) {
			try {
				
				fi += field.getName() + " = \'" + field.get(object) + "\',";
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		 
		Transaction tx = session.beginTransaction();
		fi = fi.substring(0, fi.length() - 1);
		
		String str = " set " + fi + 
				" where id = :id";
		Query query = session.createQuery("update " + clazz.getSimpleName() + str);//" set name = 'lal' where objId = :id");
			
		try {
			query.setParameter("id", clazz.getField("objId").get(object));
		} catch (IllegalArgumentException | IllegalAccessException 
				| NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = query.executeUpdate();
		tx.commit();
		session.close();
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
