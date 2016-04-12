package erp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import erp.dao.StatDao;
import erp.model.Stat;
import erp.model.StatFee;
import erp.model.StatRankList;

public class StatService {

	private final static Logger logger = Logger.getLogger(StatService.class);

	private StatDao statDao;

	public StatService() {
	}

	public double getStoreAmount() {
		return statDao.getStoreAmount();
	}

	public List<StatFee> listFeeByDay(Date from, Date to) {
		// Summary Express fee
		return statDao.listFeeByDay(from, to);
	}

	public List<Stat> listStatByDay(int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		Date end = c.getTime();
		c.add(Calendar.DATE, 1 - num);
		Date start = c.getTime();
		// Only date info will be used, so do not need set hour or minute

		logger.debug("ListStatByDay " + String.valueOf(num) + " start:"
				+ start.toString() + " end:" + end.toString());

		c.add(Calendar.DATE, num + num);
		end = c.getTime();

		List<Stat> lstProfit = statDao.listProfitByDay(start, end);
		List<Stat> lstCount = statDao.listCountByDay(start, end);

		logger.debug("Count:" + String.valueOf(lstCount.size())
				+ " ProfitCount:" + String.valueOf(lstProfit.size()));

		for (Stat st : lstCount) {
			for (Stat t : lstProfit) {
				if (st.getStatDate().equals(t.getStatDate())) {
					t.setNumber(st.getNumber());
					t.setFee(st.getFee());
					t.setFeeReal(st.getFeeReal());
					t.setDiscount(st.getDiscount());
					break;
				}
			}
		}

		Date tt;
		boolean found;
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		for (int i = 1; i < num; i++) {
			c.add(Calendar.DATE, -1);
			tt = c.getTime();
			found = false;
			for (Stat t : lstProfit) {
				Date sd = t.getStatDate();
				if (tt.compareTo(sd) == 0) {
					found = true;
					break;
				}
			}
			if (false == found) {
				Stat s = new Stat();
				s.setStatDate(tt);
				lstProfit.add(s);
			}
		}
		Collections.sort(lstProfit);

		return lstProfit;
	}

	public List<Stat> listStatByMonth(int num) {

		Date start = new Date();
		Date end = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(start);

		// logger.info("Calendar.YEAR:" + c.get(Calendar.YEAR));
		// logger.info("Calendar.MONTH:" + c.get(Calendar.MONTH));
		// logger.info("Calendar.DATE:" + c.get(Calendar.DATE));

		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DATE, 1);
		end = c.getTime();

		c.add(Calendar.MONTH, -num);
		start = c.getTime();

		logger.debug("listStatByMonth " + String.valueOf(num) + " start:"
				+ start.toString() + " end:" + end.toString());

		List<Stat> lstRet = new ArrayList<Stat>();
		List<Stat> lstProfit = statDao.listProfitByMonth(start, end);
		List<Stat> lstCount = statDao.listCountByMonth(start, end);
		boolean flag;

		for (Stat st : lstProfit) {
			c.setTime(st.getStatDate());
			c.set(Calendar.DATE, 1);
			flag = false;
			for (Stat t : lstRet) {
				if (c.getTime().equals(t.getStatDate())) {
					t.setProfit(t.getProfit() + st.getProfit());
					t.setAmount(t.getAmount() + st.getAmount());
					t.setDiscount(t.getDiscount() + st.getDiscount());
					flag = true;
					break;
				}
			}
			if (flag == false) {
				Stat s = new Stat();
				s.setStatDate(c.getTime());
				s.setProfit(st.getProfit());
				s.setAmount(st.getAmount());
				s.setDiscount(st.getDiscount());
				lstRet.add(s);
			}
		}

		for (Stat st : lstCount) {
			c.setTime(st.getStatDate());
			c.set(Calendar.DATE, 1);
			flag = false;
			for (Stat t : lstRet) {
				if (c.getTime().equals(t.getStatDate())) {
					t.setNumber(t.getNumber() + st.getNumber());
					t.setFee(t.getFee() + st.getFee());
					t.setFeeReal(t.getFeeReal() + st.getFeeReal());
					t.setDiscount(t.getDiscount() + st.getDiscount());
					flag = true;
					break;
				}
			}
			if (flag == false) {
				Stat s = new Stat();
				s.setStatDate(c.getTime());
				s.setNumber(st.getNumber());
				s.setFee(st.getFee());
				s.setFeeReal(st.getFeeReal());
				s.setDiscount(st.getDiscount());
				lstRet.add(s);
			}
		}

		return lstRet;
	}

	public List<StatRankList> listRankList(int dayNum, int func) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		Date end = c.getTime();
		c.add(Calendar.DATE, 1 - dayNum);
		Date start = c.getTime();
		List<StatRankList> rankList = statDao.listRankListByDay(start, end, func);
		return rankList;
	}

	public StatDao getStatDao() {
		return statDao;
	}

	public void setStatDao(StatDao statDao) {
		this.statDao = statDao;
	}

}
