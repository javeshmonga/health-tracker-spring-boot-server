package project.services;

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
	
	@PostMapping("/api/user")
	public User createSchedule(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteSchedule(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}
	
}
