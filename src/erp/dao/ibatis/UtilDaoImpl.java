package erp.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.UtilDao;
import erp.model.Util;

public class UtilDaoImpl extends SqlMapClientDaoSupport implements UtilDao {

    @Override
    public boolean create(Util newInstance) throws DataAccessException {
        getSqlMapClientTemplate().insert("Util.create", newInstance);
        return true;
    }

    @Override
    public Util read(String key) throws DataAccessException {
        return (Util) getSqlMapClientTemplate()
                .queryForObject("Util.read", key);
    }

    @Override
    public boolean update(Util transientObject) throws DataAccessException {
        getSqlMapClientTemplate().update("Util.update", transientObject);
        return true;
    }

    @Override
    public boolean delete(Util persistentObject) throws DataAccessException {
        getSqlMapClientTemplate().delete("Util.delete",
                persistentObject.getKey());
        return true;
    }

}
