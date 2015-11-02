package data.service;

import java.util.List;

import data.domain.User;

public interface UserService {

	List<User> getAll();

	User findOne(Long id);

	User create(User u);

	void updateProfile(Long id, String newName);
}
