package univ.dto;

public class CourseData {

	private String[] courseData = new String[9]; 
	private String[] enrolData = new String[6];

	public CourseData(String[] courseData) {
		int j = 0;
		for(int i = 0; i < courseData.length; i++){
			this.courseData[i] = courseData[i];
			if(i == 0 || i == 1 || i == 3 || i == 6 || i == 7 || i == 8){
				this.enrolData[j++] = courseData[i]; 
			}
		}
	}
	
	public String[] toArray(){
		return courseData;
	}
	
	public String[] toEnrolArray(){
		return enrolData;
	}

	public int length(){
		return courseData.length;
	}
}
