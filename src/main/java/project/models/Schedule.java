package project.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private int numOfDays;
	
	private int proteins;
	private int calories;
	private int carbohydrates;
	private int fats;
	
	private String userRequirements;
	
	
	
	public int getProteins() {
		return proteins;
	}
	public void setProteins(int proteins) {
		this.proteins = proteins;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	public int getFats() {
		return fats;
	}
	public void setFats(int fats) {
		this.fats = fats;
	}
	public String getUserRequirements() {
		return userRequirements;
	}
	public void setUserRequirements(String userRequirements) {
		this.userRequirements = userRequirements;
	}
	public int getNumOfDays() {
		return numOfDays;
	}
	public void setNumOfDays(int numOfDays) {
		this.numOfDays = numOfDays;
	}
	@OneToMany(mappedBy="schedule",orphanRemoval=true)
	private List<Day> days;
	
	public List<Day> getDays() {
		return days;
	}
	public void setDays(List<Day> days) {
		this.days = days;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
