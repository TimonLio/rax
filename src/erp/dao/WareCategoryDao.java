package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.WareCategory;

public interface WareCategoryDao {

    public int create(WareCategory newInstance) throws DataAccessException;

    public WareCategory read(int id) throws DataAccessException;

    public int update(WareCategory transientObject) throws DataAccessException;

    public int delete(WareCategory persistentObject) throws DataAccessException;

    public int count() throws DataAccessException;

    public List<WareCategory> list() throws DataAccessException;

}
