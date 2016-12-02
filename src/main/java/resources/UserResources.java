package resources;

import java.security.Principal;

import mainEntry.model.Role;
import mainEntry.model.User;
import mainEntry.repositories.RoleRepository;
import mainEntry.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
// @RequestMapping("/api/users")
public class UserResources {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String testService() {
		User abdelghafar = new User();
		abdelghafar.setEmail("test@test.com");
		abdelghafar.setFirstName("abdelghafar");
		abdelghafar.setLastName("ahmed");
		abdelghafar.setPassword("12345");
		Role userRole = roleRepository.findAll().iterator().next();
		userRole = roleRepository.findByName("moderator");
		System.out.println("role Id is: "+userRole.getId()+"##############################");
		abdelghafar.setRole(userRole);
		userRepository.save(abdelghafar);
		return "test successfully, found ";
	}
	@RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
}
