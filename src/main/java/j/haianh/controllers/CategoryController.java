package j.haianh.controllers;



import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import j.haianh.entity.Category;
import j.haianh.services.ICategoryService;
import j.haianh.services.impl.CategoryService;
import j.haianh.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;




@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/catergory-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/catergory-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));

			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/catergory-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				cateService.delete(Integer.parseInt(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		else if (url.contains("search"))
		{
			String search = req.getParameter("search");
			if (search !=null || search.strip().length()!=0)
			{
				List<Category> list = cateService.findByCategoryname(search);
				req.setAttribute("listcate", list);
				req.getRequestDispatcher("/views/admin/catergory-list.jsp").forward(req, resp);
				return;
			}
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/catergory-list.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			Category category = new Category();
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			String images=req.getParameter("images");
			int statuss = Integer.parseInt(status);
			category.setStatus(statuss);
			category.setCategoryname(categoryname);
			category.setImages(images);
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);

					// Ghi ten file vao data
					category.setImages(fname);
				}
			} catch (Exception e) {
				e.printStackTrace();

			}

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")) {

			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			String images = req.getParameter("images");
			int statuss = Integer.parseInt(status);

			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			Category cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			category.setImages(fileold);
			if (images != null || images.strip().length() != 0)

				category.setImages(images);
			category.setStatus(statuss);
			// Lưu hình cũ

			// Xử lý images
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);

					// Ghi ten file vao data
					category.setImages(fname);
				} else {

				}
			} catch (Exception e) {
				e.printStackTrace();

				
			}
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/views/admin/categories");
		}
	}
}