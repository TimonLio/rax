package erp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import erp.model.Vendor;

public interface VendorDao {

	public int create(Vendor newInstance) throws DataAccessException;

	public Vendor read(int id) throws DataAccessException;

	public int update(Vendor transientObject) throws DataAccessException;

	public int delete(Vendor persistentObject) throws DataAccessException;

	public int count() throws DataAccessException;

	public List<Vendor> list() throws DataAccessException;
	
	public List<Map> listHistoryOffer(int id) throws DataAccessException;

}
