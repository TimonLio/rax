package erp.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.WareCategoryDao;
import erp.model.WareCategory;

public class WareCategoryDaoImpl extends SqlMapClientDaoSupport implements
        WareCategoryDao {

    @Override
    public int create(WareCategory newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "WareCategory.create", newInstance));
        return newInstance.getId();
    }

    @Override
    public WareCategory read(int id) throws DataAccessException {
        return (WareCategory) getSqlMapClientTemplate().queryForObject(
                "WareCategory.read", id);
    }

    @Override
    public int update(WareCategory transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("WareCategory.update",
                transientObject);
    }

    @Override
    public int delete(WareCategory persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("WareCategory.delete",
                persistentObject.getId());
    }

    @Override
    public int count() throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "WareCategory.count");
    }

    @Override
    public List<WareCategory> list() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("WareCategory.list");
    }

}
