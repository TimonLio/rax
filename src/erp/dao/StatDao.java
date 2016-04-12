package erp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Stat;
import erp.model.StatFee;
import erp.model.StatRankList;

public interface StatDao {

	public double getStoreAmount() throws DataAccessException;

	public List<Stat> listProfitByDay(Date from, Date to)
			throws DataAccessException;

	public List<Stat> listCountByDay(Date from, Date to)
			throws DataAccessException;

	public List<Stat> listProfitByMonth(Date from, Date to)
			throws DataAccessException;

	public List<Stat> listCountByMonth(Date from, Date to)
			throws DataAccessException;

	public List<StatFee> listFeeByDay(Date from, Date to)
			throws DataAccessException;

	public List<StatRankList> listRankListByDay(Date from, Date to, int func)
			throws DataAccessException;
}
