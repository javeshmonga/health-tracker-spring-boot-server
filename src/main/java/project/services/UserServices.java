package project.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.Schedule;
import project.models.User;
import project.repositories.ScheduleRepository;
import project.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserServices {

	private User currentUser;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@GetMapping("/api/user")
	public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
	
	private User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user) {
		
		this.currentUser = null;
		
		if (findUserByUsername(user.getUsername()) != null) {
			user.setUsername(null);
			return user;
		}
		
		User newUser = userRepository.save(user);
		
		if (user.getUserType().equals("Member")) {
			String title = "Schedule_" + newUser.getUsername();
			Schedule schedule = new Schedule();
			schedule.setTitle(title);
			
			newUser.setSchedule(schedule);
			scheduleRepository.save(schedule);
		}
		else {
			newUser.setSchedule(null);
		}
		
		this.currentUser = newUser;
		return newUser;
	}
	
	@GetMapping("/api/profile")
	public User profile() {
		User currentUser = this.currentUser;   
		return currentUser;
	}
	
	@DeleteMapping("/api/logout")
	public void logout() {
	    this.currentUser = null;
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user) {
		
		this.currentUser = null;
		
		User resultUser = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
		if(resultUser == null) {
			user.setUsername(null);
			return user;
		}
		this.currentUser = resultUser;
		return resultUser;
	}


}
