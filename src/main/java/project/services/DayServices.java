package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.Day;
import project.repositories.DayRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DayServices {
	
	@Autowired
	DayRepository dayRepository;
	
	@GetMapping("/api/schedule/{scheduleId}/day")
	public Iterable<Day> findAllDaysForSchedule() {
		return dayRepository.findAll(); 
	}
	
	@PostMapping("/api/schedule/{scheduleId}/day")
	public Day createDay(@RequestBody Day day) {
		return dayRepository.save(day);
	}
	
	@DeleteMapping("/api/day/{dayId}")
	public void deleteDay(@PathVariable("dayId") int id) {
		dayRepository.deleteById(id);
	}
}
