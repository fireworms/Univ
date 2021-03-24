package univ.vo;

public class StuData {

	private String stuCode, stuName, stuJuminNum_1, stuJuminNum_2, stuAddr,
			stuCellphone, stuPhone, stuEntYear, stuHighSchool, stuGrad, stuDepCode, stuProCode;

	public StuData(String stuCode, String stuName, String stuJuminNum_1,
			String stuJuminNum_2, String stuAddr, String stuCellphone,
			String stuPhone, String stuEntYear, String stuHighSchool,
			String stuGrad, String stuDepCode, String stuProCode) {
		this.stuCode = stuCode;
		this.stuName = stuName;
		this.stuJuminNum_1 = stuJuminNum_1;
		this.stuJuminNum_2 = stuJuminNum_2;
		this.stuAddr = stuAddr;
		this.stuCellphone = stuCellphone;
		this.stuPhone = stuPhone;
		this.stuEntYear = stuEntYear;
		this.stuHighSchool = stuHighSchool;
		this.stuGrad = stuGrad;
		this.stuDepCode = stuDepCode;
		this.stuProCode = stuProCode;
	}

	public String getStuProCode() {
		return stuProCode;
	}

	public void setStuProCode(String stuProCode) {
		this.stuProCode = stuProCode;
	}

	public String[] toArray() {
		String[] returnValue = { stuCode, stuName, stuAddr, getStuJuminNum(),
				stuCellphone, stuPhone, stuEntYear, stuHighSchool, stuGrad, stuDepCode , stuProCode};
		return returnValue;
	}

	public String getStuCode() {
		return stuCode;
	}

	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuJuminNum_1() {
		return stuJuminNum_1;
	}

	public void setStuJuminNum_1(String stuJuminNum_1) {
		this.stuJuminNum_1 = stuJuminNum_1;
	}

	public String getStuJuminNum_2() {
		return stuJuminNum_2;
	}
	
	public String getStuJuminNum(){
		return stuJuminNum_1 + "-" + stuJuminNum_2;
	}

	public void setStuJuminNum_2(String stuJuminNum_2) {
		this.stuJuminNum_2 = stuJuminNum_2;
	}

	public String getStuAddr() {
		return stuAddr;
	}

	public void setStuAddr(String stuAddr) {
		this.stuAddr = stuAddr;
	}

	public String getStuCellphone() {
		return stuCellphone;
	}

	public void setStuCellphone(String stuCellphone) {
		this.stuCellphone = stuCellphone;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public String getStuEntYear() {
		return stuEntYear;
	}

	public void setStuEntYear(String stuEntYear) {
		this.stuEntYear = stuEntYear;
	}

	public String getStuHighSchool() {
		return stuHighSchool;
	}

	public void setStuHighSchool(String stuHighSchool) {
		this.stuHighSchool = stuHighSchool;
	}

	public String getStuGrad() {
		return stuGrad;
	}

	public void setStuGrad(String stuGrad) {
		this.stuGrad = stuGrad;
	}

	public String getStuDepCode() {
		return stuDepCode;
	}

	public void setStuDepCode(String stuDepCode) {
		this.stuDepCode = stuDepCode;
	}

	

}
