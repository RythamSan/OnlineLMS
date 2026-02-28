package model;

public class Course {
	private int cid;
	private String title;
	private String description;
	public Course() {
		
	}
	public Course(int cid, String title, String description) {
		super();
		this.cid = cid;
		this.title = title;
		this.description = description;
	}
	public int getcid() {
		return cid;
	}
	public void setcid(int cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
