package services;

import java.util.List;

public interface ReflectionJdbcDao<T> {

	public void insert(T object);
	
	public void update(T object);
	
	public void deleteByKey(T key);
	
	public T selectByKey(T key);

	public List<T> selectAll(Class<?> clazz);
}