package data.dao;

import org.springframework.data.repository.CrudRepository;

import data.domain.User;

public interface UserJPARepository extends CrudRepository<User, Long> {

}
