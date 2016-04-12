package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface KeywordDao {

    public boolean create(String newInstance) throws DataAccessException;

    public String read(String key) throws DataAccessException;

    public boolean update(String transientObject) throws DataAccessException;

    public boolean delete(String persistentObject) throws DataAccessException;

    public int count(int status) throws DataAccessException;

    public List<String> list() throws DataAccessException;
}
