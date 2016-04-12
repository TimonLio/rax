package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.SellItemDao;
import erp.model.SellItem;

public class SellItemDaoImpl extends SqlMapClientDaoSupport implements
        SellItemDao {

    @Override
    public int create(SellItem newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "SellItem.create", newInstance));
        return newInstance.getId();
    }

    @Override
    public SellItem read(int id) throws DataAccessException {
        return (SellItem) getSqlMapClientTemplate().queryForObject(
                "SellItem.read", id);
    }

    @Override
    public int update(SellItem transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("SellItem.update",
                transientObject);
    }

    @Override
    public int delete(SellItem persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("SellItem.delete",
                persistentObject.getId());
    }

    @Override
    public int countBySellId(int sellId) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject("SellItem.count", sellId);
    }
    
    @Override
    public List<SellItem> listBySellId(int sellId) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("SellItem.list", sellId);
    }
    
}
