package erp.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;

import erp.model.Vendor;
import erp.service.UtilService;
import erp.service.VendorService;

public class VendorAction implements Action {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(VendorAction.class);

	private VendorService vendorService = null;
	private UtilService utilService = null;

	private List<Vendor> vendorList;
	private Map<Integer, String> imTypeSel;

	private Vendor vendor;
	private int id;

	public String get() throws Exception {
		vendor = vendorService.getVendorById(id);
		return SUCCESS;
	}

	public String list() throws Exception {
		vendorList = vendorService.list();
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		try {
			Vendor v = new Vendor();
			v.setId(id);
			v.setTitle(vendor.getTitle());
			v.setName(vendor.getName());
			v.setPhone1(vendor.getPhone1());
			v.setPhone2(vendor.getPhone2());
			v.setAddress(vendor.getAddress());
			v.setIM(vendor.getIM());
			v.setIMType(vendor.getIMType());
			v.setComment(vendor.getComment());
			if (id > 0)
				vendorService.updateVendor(v);
			else
				vendorService.createVendor(v);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ERROR;
		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		try {
			vendorService.deleteVendorById(id);
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return ERROR;
	}

	public Map<Integer, String> getImTypeSel() {
		if (imTypeSel == null)
			imTypeSel = utilService.getIMTypeSel();
		return imTypeSel;
	}

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);

		VendorAction action = new VendorAction();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	public void setUtilService(UtilService utilService) {
		this.utilService = utilService;
	}

}
