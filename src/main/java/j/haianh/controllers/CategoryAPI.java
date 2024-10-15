package j.haianh.controllers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import j.haianh.entity.Category;
import j.haianh.services.ICategoryService;
import j.haianh.util.Constant;
import j.haianh.util.HttpUtil;
import j.haianh.util.UploadUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet(urlPatterns = { "/api-admin-category" })
public class CategoryAPI extends HttpServlet {

	private ICategoryService categoryService;

	private static final long serialVersionUID = 7513532388122593398L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		mapper.writeValue(resp.getOutputStream(), categoryService.findAll());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		// khỏi tạo đối tượng entity
		Category category = new Category();
		// sử dụng BeanUtils để tự lấy các name Field trên form
		// tên field phải trùng với entity
		try {
			BeanUtils.populate(category, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// xử lý hình ảnh
		String fileName = "" + System.currentTimeMillis();
		category.setIcon(UploadUtils.processUpload("icon", req, Constant.DIR + "\\category\\", fileName));
		categoryService.insert(category);
		mapper.writeValue(resp.getOutputStream(), category);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		// lấy dữ liệu từ jsp bằng BeanUtils
		Category category = new Category();
		try {
			BeanUtils.populate(category, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// khởi tạo DAO
		Category oldcate = categoryService.findById(category.getCategoryId());
		// xử lý hình ảnh
		if (req.getPart("icon").getSize() == 0) {
			category.setIcon(oldcate.getIcon());
		} else {
			if (oldcate.getIcon() != null) {
				// XOA ANH CU DI
				String fileName = oldcate.getIcon();
				File file = new File(Constant.DIR + "\\category\\" + fileName);
				if (file.delete()) {
					System.out.println("Đã xóa thành công");
				} else {
					System.out.println(Constant.DIR + "\\category\\" + fileName);
				}
			}
			String fileName = "" + System.currentTimeMillis();
			category.setIcon(UploadUtils.processUpload("icon", req, Constant.DIR + "\\category\\", fileName));
		}
		categoryService.update(category);
		mapper.writeValue(resp.getOutputStream(), category);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			ObjectMapper mapper = new ObjectMapper();
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			Category cateModel =
			HttpUtil.of(req.getReader()).toModel(Category.class);
			try {
			categoryService.delete(cateModel.getCategoryId());
			} catch (Exception e) {
			e.printStackTrace();
			}
			mapper.writeValue(resp.getOutputStream(), "{Đã xóa thành công}");
			}

	}

