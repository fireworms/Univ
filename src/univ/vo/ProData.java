package univ.vo;

public class ProData {

	private String proCode, proName, proJuminNum_1, proJuminNum_2, proAddr,
			proCellphone, proPhone, proHireYear, proDegree, proMajor, proLab;

	public ProData(String proCode, String proName, String proJuminNum_1,
			String proJuminNum_2, String proAddr, String proCellphone,
			String proPhone, String proHireYear, String proDegree,
			String proMajor, String proLab) {
		this.proCode = proCode;
		this.proName = proName;
		this.proJuminNum_1 = proJuminNum_1;
		this.proJuminNum_2 = proJuminNum_2;
		this.proAddr = proAddr;
		this.proCellphone = proCellphone;
		this.proPhone = proPhone;
		this.proHireYear = proHireYear;
		this.proDegree = proDegree;
		this.proMajor = proMajor;
		this.proLab = proLab;
	}

	public String[] toArray() {
		String[] returnValue = { proCode, proName, proAddr, getProJuminNum(),
				proCellphone, proPhone, proHireYear, proDegree, proMajor, proLab };
		return returnValue;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProJuminNum_1() {
		return proJuminNum_1;
	}

	public void setProJuminNum_1(String proJuminNum_1) {
		this.proJuminNum_1 = proJuminNum_1;
	}

	public String getProJuminNum_2() {
		return proJuminNum_2;
	}
	
	public String getProJuminNum(){
		return proJuminNum_1 + "-" + proJuminNum_2;
	}

	public void setProJuminNum_2(String proJuminNum_2) {
		this.proJuminNum_2 = proJuminNum_2;
	}

	public String getProAddr() {
		return proAddr;
	}

	public void setProAddr(String proAddr) {
		this.proAddr = proAddr;
	}

	public String getProCellphone() {
		return proCellphone;
	}

	public void setProCellphone(String proCellphone) {
		this.proCellphone = proCellphone;
	}

	public String getProPhone() {
		return proPhone;
	}

	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}

	public String getProHireYear() {
		return proHireYear;
	}

	public void setProHireYear(String proHireYear) {
		this.proHireYear = proHireYear;
	}

	public String getProDegree() {
		return proDegree;
	}

	public void setProDegree(String proDegree) {
		this.proDegree = proDegree;
	}

	public String getProMajor() {
		return proMajor;
	}

	public void setProMajor(String proMajor) {
		this.proMajor = proMajor;
	}

	public String getProLab() {
		return proLab;
	}

	public void setProLab(String proLab) {
		this.proLab = proLab;
	}

	

}
