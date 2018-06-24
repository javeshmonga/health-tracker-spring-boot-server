package project.repositories;

import org.springframework.data.repository.CrudRepository;

import project.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget,Integer> {

}
