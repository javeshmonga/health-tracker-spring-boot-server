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
import project.models.Widget;
import project.repositories.DayRepository;
import project.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetServices {
	
	@Autowired
	DayRepository dayRepository;
	
	@Autowired
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/day/{dayId}/widget")
	public List<Widget> findAllWidgetsForDays(
			@PathVariable("dayId") int dayId) {
		Optional<Day> data = dayRepository.findById(dayId);
		if(data.isPresent()) {
			Day day = data.get();
			return day.getWidgets();
		}
		return null;		
	}
	
	@PostMapping("/api/day/{dayId}/widget")
	public Widget createWidget(
			@PathVariable("dayId") int dayId,
			@RequestBody Widget widget) {
		Optional<Day> data = dayRepository.findById(dayId);
		
		if(data.isPresent()) {
			Day day = data.get();
			widget.setDay(day);
			
			return widgetRepository.save(widget);
		}
		return null;		
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int id) {
		widgetRepository.deleteById(id);
	}

}
