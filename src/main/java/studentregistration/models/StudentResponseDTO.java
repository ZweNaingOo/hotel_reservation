package studentregistration.models;

import java.io.Serializable;

public class StudentResponseDTO implements Serializable{
	private static final long serialVersionUID = -8484447208249033651L;
	private int id;
	private String name;
	private String courses;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	
	
}
