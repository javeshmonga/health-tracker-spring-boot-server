package project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.Schedule;
import project.repositories.ScheduleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ScheduleServices {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@GetMapping("/api/schedule")
	public Iterable<Schedule> findAllSchedule() {
		return scheduleRepository.findAll(); 
	}
	
	@PostMapping("/api/schedule")
	public Schedule saveSchedule(@RequestBody Schedule schedule) {
		return scheduleRepository.save(schedule); 
	}
	
	@GetMapping("/api/schedule/{scheduleId}")
	public Schedule findScheduleById(@PathVariable("scheduleId") int id) {
		Optional<Schedule> data = scheduleRepository.findById(id);
		if(data.isPresent()) {
			Schedule schedule = data.get();
			return schedule;
		}
		return null; 
	}
	
	@DeleteMapping("/api/schedule/{scheduleId}")
	public void deleteSchedule(@PathVariable("scheduleId") int id) {
		scheduleRepository.deleteById(id);
	}
}
