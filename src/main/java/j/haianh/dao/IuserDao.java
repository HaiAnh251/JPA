package j.haianh.dao;

import java.util.List;

import j.haianh.entity.User;


public interface IuserDao {

	List<User> findAll();

	User findById(int id);

	User findByUsername(String username);

	void insert(User user);

	
	User findByEmail(String email);
	

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	boolean checkExistPhone(String phone);
	
	void update(User user);

	

}
