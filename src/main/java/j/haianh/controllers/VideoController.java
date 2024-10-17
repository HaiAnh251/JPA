package j.haianh.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import j.haianh.entity.Video;
import j.haianh.services.IvideoService;
import j.haianh.services.impl.videoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = {"/admin/videos",
		"/admin/video/add", "/admin/video/edit", 
		"/admin/video/update", "/admin/video/insert", 
		"/admin/video/delete"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10 * 5)
public class VideoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public IvideoService videoSer = new videoService();
    
    private static final String UPLOAD_DIRECTORY = "uploads/videos";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
    	String url = req.getRequestURI();

        if (url.contains("videos")) {
            List<Video> videos = videoSer.findAll();
            req.setAttribute("videos", videos);
            req.getRequestDispatcher("/views/admin/Video/video-list.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoSer.findById(id);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/Video/video-edit.jsp").forward(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/views/admin/Video/video-add.jsp").forward(req, resp);
        } else if (url.contains("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                videoSer.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
                // Optionally, set an error message to show in the JSP
                req.setAttribute("error", "Failed to delete the video.");
                req.getRequestDispatcher("/admin/video-list.jsp").forward(req, resp);
                return; // Stop further processing to avoid redirect
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/admin/video/insert")) {
            Video video = new Video();
            video.setTitle(req.getParameter("title"));
            video.setDescription(req.getParameter("description"));
            video.setUploaddate(new Date());

            String fileName = saveFile(req.getPart("file"));
            video.setVideoname(fileName);

            videoSer.insert(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("/admin/video/update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoSer.findById(id);
            video.setTitle(req.getParameter("title"));
            video.setDescription(req.getParameter("description"));

            String fileName = saveFile(req.getPart("file"));
            if (fileName != null && !fileName.isEmpty()) {
                video.setVideoname(fileName);
            }

            videoSer.update(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    private String saveFile(Part filePart) throws IOException {
        if (filePart.getSize() <= 0) {
            return null;
        }
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(filePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String newFileName = System.currentTimeMillis() + "_" + fileName;
        filePart.write(filePath + File.separator + newFileName);
        return newFileName;
    }
}
