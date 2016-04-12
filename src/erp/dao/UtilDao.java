package erp.dao;

import org.springframework.dao.DataAccessException;

import erp.model.Util;

public interface UtilDao {

    public boolean create(Util newInstance) throws DataAccessException;

    public Util read(String key) throws DataAccessException;

    public boolean update(Util transientObject) throws DataAccessException;

    public boolean delete(Util persistentObject) throws DataAccessException;

}
