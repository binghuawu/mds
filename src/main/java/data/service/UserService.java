package data.service;

import java.util.List;

public interface UserService<T> {

	List<T> getAll();

	T findOne(Long id);

	T create(T u);

	void updateProfile(Long id, String newName);
}
