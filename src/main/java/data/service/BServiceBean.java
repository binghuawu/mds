package data.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import data.dao.b.BRepository;
import data.domain.b.User;

@Service("u2")
@Transactional(value = "transactionManagerB")
public class BServiceBean implements UserService<User> {

	@Autowired
	private BRepository jpaRepo;

	@Override
	public List<User> getAll() {
		List<User> list = new ArrayList<User>();
		Iterator<User> it = jpaRepo.findAll().iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}

		return list;
	}

	@Override
	@Transactional
	public void updateProfile(Long id, String newName) {
		User one = jpaRepo.findOne(id);
		one.setName(newName);
		jpaRepo.save(one);
	}

	@Override
	// @Transactional("transactionManager")
	public User create(User u) {
		return jpaRepo.save(u);
	}

	@Override
	public User findOne(Long id) {
		return jpaRepo.findOne(id);
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
