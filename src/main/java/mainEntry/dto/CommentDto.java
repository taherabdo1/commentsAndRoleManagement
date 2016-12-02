package mainEntry.dto;


//this is a wrapper class to hold the data sent from client holding the new comment data.
public class CommentDto {
	
	String description;
	String userEmail;
	
	
	public CommentDto(){}
	
	public CommentDto(String description, String userEmail) {
		super();
		this.description = description;
		this.userEmail = userEmail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
