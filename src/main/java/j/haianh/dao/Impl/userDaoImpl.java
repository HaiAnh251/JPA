package j.haianh.dao.Impl;

import java.util.List;

import j.haianh.config.JPAConfig;
import j.haianh.dao.IuserDao;
import j.haianh.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;





public class userDaoImpl implements IuserDao {


	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}

	}

	

	public User findById(int userid) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, userid);
		return user;
	}

	@Override
	public User findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, username);
		return user;
	}

	@Override
	public boolean checkExistUsername(String username) {
		IuserDao userdao = new userDaoImpl();
		User user = userdao.findByUsername(username);
		if (user != null)
			return true;
		return false;
	}

	@Override
	public boolean checkExistEmail(String email) {
		IuserDao userdao = new userDaoImpl();
		User user = userdao.findByEmail(email);
		if (user != null)
			return true;
		return false;

	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}



	

	@Override
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		
	}

	@Override
	public User findByEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, email);
		return user;
	}

	

}
