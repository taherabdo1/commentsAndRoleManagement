package resources;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import mainEntry.repositories.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class CommentResource {

	@Autowired
	CommentRepository commentRepository;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/addComment", consumes="application/json")
	public String addComment(@RequestBody String json, HttpServletResponse  response) {
		
		ObjectMapper mapper = new ObjectMapper();
	    
		System.out.println(json);
	    
	    return "";
	}
}
