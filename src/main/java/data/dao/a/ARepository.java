package data.dao.a;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import data.domain.a.User;

public interface ARepository extends CrudRepository<User, Long> {

	/**
	 * This follows the naming convention of Spring Data repository.
	 * 
	 * @param nameToFind
	 * @return
	 */
	User findByName(String nameToFind);

	/**
	 * Uses JPQL.
	 * 
	 * @param nameToFind
	 * @return
	 */
	@Query("SELECT u FROM User AS u where u.name=:name")
	User findByNameJPQL(@Param("name") String nameToFind);

	@Query(value = "SELECT count(1) FROM t_user WHERE user_name=:name", nativeQuery = true)
	Long countByNameNative(@Param("name") String nameToFind);

	// @Query(value = "SELECT new data.domain.ProjectDetail(ps.pid, ps.uid) "
	// + "FROM Projects ps JOIN User u ")
	// ProjectDetail getProjectDetails();
}
