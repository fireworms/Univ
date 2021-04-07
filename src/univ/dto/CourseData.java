package univ.dto;

public class CourseData {

	private String[] courseData = new String[9]; 

	public CourseData(String[] courseData) {
		for(int i = 0; i < courseData.length; i++){
			this.courseData[i] = courseData[i];
		}
	}
	
	public String[] toArray(){
		return courseData;
	}

	public int length(){
		return courseData.length;
	}
}
