package erp.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.StatDao;
import erp.model.Stat;
import erp.model.StatFee;
import erp.model.StatRankList;

public class StatDaoImpl extends SqlMapClientDaoSupport implements StatDao {

	@Override
	public double getStoreAmount() throws DataAccessException {
		return (Double) getSqlMapClientTemplate().queryForObject(
				"Stat.storeAmount");
	}

	@Override
	public List<Stat> listProfitByDay(Date from, Date to)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("from", from);
		param.put("to", to);
		return getSqlMapClientTemplate()
				.queryForList("Stat.profitByDay", param);
	}

	@Override
	public List<Stat> listCountByDay(Date from, Date to)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("from", from);
		param.put("to", to);
		return getSqlMapClientTemplate().queryForList("Stat.countByDay", param);
	}

	@Override
	public List<Stat> listProfitByMonth(Date from, Date to)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("from", from);
		param.put("to", to);
		return getSqlMapClientTemplate().queryForList("Stat.profitByMonth",
				param);
	}

	@Override
	public List<Stat> listCountByMonth(Date from, Date to)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("from", from);
		param.put("to", to);
		return getSqlMapClientTemplate().queryForList("Stat.countByMonth",
				param);
	}

	@Override
	public List<StatFee> listFeeByDay(Date from, Date to)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("from", from);
		param.put("to", to);
		return getSqlMapClientTemplate().queryForList("Stat.feeByDate", param);
	}

	@Override
	public List<StatRankList> listRankListByDay(Date from, Date to, int func)
			throws DataAccessException {
		Map param = new HashMap();
		param.put("from", from);
		param.put("to", to);
		param.put("func", func);
		return getSqlMapClientTemplate().queryForList("Stat.rankList", param);
	}
}
