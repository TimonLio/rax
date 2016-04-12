package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.SellItem;

public interface SellItemDao {

	public int create(SellItem newInstance) throws DataAccessException;

	public SellItem read(int id) throws DataAccessException;

	public int update(SellItem transientObject) throws DataAccessException;

	public int delete(SellItem persistentObject) throws DataAccessException;

	public int countBySellId(int sellId) throws DataAccessException;

	public List<SellItem> listBySellId(int sellId) throws DataAccessException;

}
