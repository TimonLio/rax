package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Express;

public interface ExpressDao {

	public int create(Express newInstance) throws DataAccessException;

	public Express read(int id) throws DataAccessException;

	public int update(Express transientObject) throws DataAccessException;

	public int delete(Express persistentObject) throws DataAccessException;

	public int count() throws DataAccessException;

	public List<Express> list() throws DataAccessException;
}
