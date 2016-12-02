package resources;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import mainEntry.dto.CommentDto;
import mainEntry.model.Comment;
import mainEntry.model.User;
import mainEntry.repositories.CommentRepository;
import mainEntry.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CommentResource {

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/addComment", consumes = "application/json")
	public String addComment(@RequestBody String json,
			HttpServletResponse response) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			CommentDto comment = mapper.readValue(json, CommentDto.class);
		
			Comment newComment = new Comment();
			User user = userRepository.findByEmail(comment.getUserEmail());
			newComment.setUser(user);
			newComment.setDescription(comment.getDescription());
			commentRepository.save(newComment);
			System.out.println(comment.getDescription());

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/confirmComment")
	public String conformComment(@RequestBody String commentId,
			HttpServletResponse response) {

		System.out.println("comment id is: " + commentId);
//		ObjectMapper mapper = new ObjectMapper();
//			int id = mapper.readValue(commentId, Integer.class);
			int id = Integer.parseInt(commentId);
			Comment comment = commentRepository.findOne(id);
			comment.setConfirmed(true);
			commentRepository.confirmComment(id);
			response.setHeader("Status", "200");
			ObjectMapper mapper = new ObjectMapper();
		    OutputStream writer = null;
			try {
				writer = response.getOutputStream();
				mapper.writeValue(writer,"done");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response.toString();		

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getAllCommentsForUser",produces = "application/json" )
	public String getAllCommentsForUser(HttpServletResponse response) {

//		System.out.println("comment id is: " + commentId);
		ObjectMapper mapper = new ObjectMapper();
//			int id = Integer.parseInt(commentId);
			List<Comment> comments = commentRepository.findByConfirmed(true);
			response.setHeader("Status", "200");
		    OutputStream writer = null;
			try {
				writer = response.getOutputStream();
				mapper.writeValue(writer,comments);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response.toString();
		

	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/getAllUnconfirmedComments",produces = "application/json" )
	public String getAllUnconfirmedComments(HttpServletResponse response) {

//		System.out.println("comment id is: " + commentId);
		ObjectMapper mapper = new ObjectMapper();
//			int id = Integer.parseInt(commentId);
			List<Comment> comments = commentRepository.findByConfirmed(false);
			response.setHeader("Status", "200");
		    OutputStream writer = null;
			try {
				writer = response.getOutputStream();
				mapper.writeValue(writer,comments);
//				writer.write(body.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response.toString();
		

	}
}
