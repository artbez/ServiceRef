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
		Field[] fields = clazz.getFields();
		String fi = "";
		for (Field field : fields)
			try {
				fi += field.getName() + " = \'" + field.get(object) + "\',";
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		 
		Transaction tx = session.beginTransaction();
		fi = fi.substring(0, fi.length() - 1);
		
		String str = " set " + fi +	" where id = :id";
		Query query = session.createQuery("update " + clazz.getSimpleName() + str);
		try {
			query.setParameter("id", clazz.getField("objId").get(object));
		} catch (IllegalArgumentException | IllegalAccessException 
				| NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	@Override
	public void deleteByKey(T key) {
		Class<? extends Object> clazz = key.getClass();
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete " + clazz.getSimpleName() 
				+ " where objId = :id");
		try {
			query.setParameter("id", clazz.getField("objId").get(key));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	@Override
	public T selectByKey(T key) {
		// TODO Auto-generated method stub
		Class<? extends Object> clazz = key.getClass();
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + clazz.getSimpleName() 
				+ " where objId = :id");
		try {
			query.setParameter("id", clazz.getField("objId").get(key));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		tx.commit();
		session.close();
		return  (T) list.get(0);
	}

	@Override
	public List<T> selectAll(Class<?> clazz) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from " + clazz.getSimpleName());
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		tx.commit();
		session.close();
		return list;	
	}
}
