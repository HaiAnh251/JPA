package j.haianh.services.impl;


import java.util.Random;

import j.haianh.dao.IuserDao;
import j.haianh.dao.Impl.userDaoImpl;
import j.haianh.entity.User;
import j.haianh.services.userService;



public class userServiceImpl implements userService {
	IuserDao userDao = new userDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.findByname(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	public User findByname(String username) {

		return userDao.findByUsername(username);
	}

	@Override
	public User get(String username) {

		return null;
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);

	}



	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);

	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}



	
	

	public String RandomPassword() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    return generatedString;
	}

	@Override
	public void updatePassword(String password, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean register(String username, String email, String fullname, String phone, String password,String images) {
		if(userDao.checkExistEmail(email))
		{
			return false;
		}
		
		
		if (userDao.checkExistUsername(username)) {
			return false;
			}
			User user=new User(0, username,0, false, false, email,fullname,phone, password, images);
			long millis=System.currentTimeMillis();
			java.sql.Date data=new java.sql.Date(millis);
			userDao.insert(user);
			return true;
	}




}
