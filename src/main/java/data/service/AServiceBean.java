package data.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import data.dao.a.ARepository;
import data.domain.a.AUser;

@Service("u1")
@Transactional("transactionManager")
public class AServiceBean implements UserService<AUser> {

	@Autowired
	private ARepository crudRepo;

	@Override
	public List<AUser> getAll() {
		List<AUser> list = new ArrayList<AUser>();
		Iterator<AUser> it = crudRepo.findAll().iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	@Override
	@Transactional
	public void updateProfile(Long id, String newName) {
		AUser one = crudRepo.findOne(id);
		one.setName(newName);
		crudRepo.save(one);
	}

	@Override
	// @Transactional("transactionManager")
	public AUser create(AUser u) {
		return crudRepo.save(u);
	}

	@Override
	public AUser findOne(Long id) {
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
