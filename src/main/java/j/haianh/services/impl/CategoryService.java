package j.haianh.services.impl;

import java.util.List;

import j.haianh.dao.ICategoryDao;
import j.haianh.dao.Impl.CategoryDaoImpl;
import j.haianh.entity.Category;
import j.haianh.services.ICategoryService;

public class CategoryService implements ICategoryService{
	
	ICategoryDao cateDao=new CategoryDaoImpl();
	@Override
	public void insert(Category category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
		
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);
		
	}

	@Override
	public Category findById(int cateid) {
		
		return cateDao.findById(cateid);
	}

	@Override
	public List<Category> findAll() {
		
		return cateDao.findAll();
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		return cateDao.findBycategoryname(catname);
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return cateDao.count();
	}

}
