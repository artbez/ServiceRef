package services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ReflectionJdbcDao<SpecialDOM> {

	@WebMethod
	public void insert(SpecialDOM object);
	
	@WebMethod
	public void update(SpecialDOM object);
	
	@WebMethod
	public void deleteByKey(SpecialDOM key);
	
	@WebMethod
	public SpecialDOM selectByKey(SpecialDOM key);
	
	@WebMethod
	List<SpecialDOM> selectAll();
}
