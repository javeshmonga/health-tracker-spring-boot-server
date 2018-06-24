package project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import project.models.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	public User findUserByUsername(
		@Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	public User findUserByCredentials(
		@Param("username") String username,
		@Param("password") String password);

}