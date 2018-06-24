package project.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.User;
import project.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserServices {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")
	public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		User newUser = userRepository.save(user);
		session.setAttribute("currentUser", newUser);
		return newUser;
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");    
		return currentUser;
	}
	
	@DeleteMapping("/api/logout")
	public void logout(HttpSession session) {
	    session.invalidate();
	}


}
