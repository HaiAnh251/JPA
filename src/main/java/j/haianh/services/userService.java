package j.haianh.services;

import j.haianh.entity.User;

public interface userService {
	User login(String Username, String Password);

	User get(String Username);

	void insert(User user);

	

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	
	void updatePassword(String password, String email);


	

	boolean register(String username, String email, String fullname, String phone, String password, String images);

}
