package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import data.dao.UserCrudRepository;
import data.domain.User;

@Service("u2")
@Transactional("transactionManagerB")
public class CrudUserServiceBean implements UserService {

	@Autowired
	private UserCrudRepository crudRepo;

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	@Transactional
	public void updateProfile(Long id, String newName) {
		User one = crudRepo.findOne(id);
		one.setName(newName);
		crudRepo.save(one);
	}

	@Override
	// @Transactional("transactionManager")
	public User create(User u) {
		return crudRepo.save(u);
	}

	@Override
	public User findOne(Long id) {
		return crudRepo.findOne(id);
	}

	// @Override
	// public User findByNameJPACriteriaBuilder(final String name) {
	// return userRepo.findOne(new Specification<User>() {
	// @Override
	// public Predicate toPredicate(Root<User> root,
	// CriteriaQuery<?> query, CriteriaBuilder cb) {
	// return cb.equal(root.<String> get("name"), name);
	// }
	// });
	// }
}
