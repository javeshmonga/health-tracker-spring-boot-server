package project.services;

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
	public Schedule createSchedule(@RequestBody Schedule schedule) {
		return scheduleRepository.save(schedule);
	}
	
	@DeleteMapping("/api/schedule/{scheduleId}")
	public void deleteSchedule(@PathVariable("scheduleId") int id) {
		scheduleRepository.deleteById(id);
	}
}
