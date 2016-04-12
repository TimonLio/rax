package erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Sell;

public interface SellDao {

	public int create(Sell newInstance) throws DataAccessException;

	public Sell read(int id) throws DataAccessException;

	public int update(Sell transientObject) throws DataAccessException;

	public int delete(Sell persistentObject) throws DataAccessException;

	public int count(int status) throws DataAccessException;

	public List<Sell> list(int index, int num, int status)
			throws DataAccessException;

	public List<Sell> listByExpress(int expressId, Date from, Date to) throws DataAccessException;

	public List<Sell> findByKeyword(String keyword) throws DataAccessException;

}
