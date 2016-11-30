package mainEntry.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private boolean confirmed;

	private String description;

	@JsonBackReference
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}