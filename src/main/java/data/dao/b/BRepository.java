package data.dao.b;

import org.springframework.data.repository.CrudRepository;

import data.domain.b.BUser;

public interface BRepository extends CrudRepository<BUser, Long> {

}
