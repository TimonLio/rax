package erp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import erp.model.StatWare;
import erp.model.Ware;

public interface WareDao {

	public int create(Ware newInstance) throws DataAccessException;

	public Ware read(int id) throws DataAccessException;

	public int update(Ware transientObject) throws DataAccessException;

	public int delete(Ware persistentObject) throws DataAccessException;

	public int count(int status) throws DataAccessException;

	public List<Ware> list(int status, int index, int num)
			throws DataAccessException;

	public List<Ware> listByCategoryId(int id, int status)
			throws DataAccessException;

	public List<Ware> listLowNumber(int status) throws DataAccessException;

	public List<Ware> findByBarcode(String barcode) throws DataAccessException;

	public List<Ware> findByKeywords(List<String> keywordList)
			throws DataAccessException;

	public List<Ware> fullTextSearch(String content, int status)
			throws DataAccessException;

	public int updateFullTextIndex(int id, String tokens)
			throws DataAccessException;

	public List<Map> listHistoryPrice(int id) throws DataAccessException;

	public StatWare getStatById(int id) throws DataAccessException;
}
