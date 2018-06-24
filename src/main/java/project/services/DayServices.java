package project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.Day;
import project.models.Schedule;
import project.repositories.DayRepository;
import project.repositories.ScheduleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DayServices {
	
	@Autowired
	DayRepository dayRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@GetMapping("/api/schedule/{scheduleId}/day")
	public List<Day> findAllDaysForSchedule(
			@PathVariable("scheduleId") int scheduleId) {
		Optional<Schedule> data = scheduleRepository.findById(scheduleId);
		if(data.isPresent()) {
			Schedule schedule = data.get();
			return schedule.getDays();
		}
		return null;		
	}
	
	@PostMapping("/api/schedule/{scheduleId}/day")
	public Day createDay(
			@PathVariable("scheduleId") int scheduleId,
			@RequestBody Day day) {
		Optional<Schedule> data = scheduleRepository.findById(scheduleId);
		
		if(data.isPresent()) {
			Schedule schedule = data.get();
			day.setSchedule(schedule);
			day.setTitle("New Day");
			
			return dayRepository.save(day);
		}
		return null;		
	}
	
	@DeleteMapping("/api/day/{dayId}")
	public void deleteDay(@PathVariable("dayId") int id) {
		dayRepository.deleteById(id);
	}
}
