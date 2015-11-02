package data.dao.b;

import org.springframework.data.repository.CrudRepository;

import data.domain.b.User;

public interface BRepository extends CrudRepository<User, Long> {

}
