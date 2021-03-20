package DBest;

public class DepData {

	private String code, department, major;
	
	public DepData(String code, String department, String major){
		this.code = code;
		this.department = department;
		this.major = major;
	}
	
	public String[] toArray(){
		String[] returnValue = {code, department, major};
		return returnValue;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
}
