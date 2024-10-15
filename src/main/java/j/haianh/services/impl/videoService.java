package j.haianh.services.impl;

import java.util.List;

import j.haianh.dao.IvideoDao;
import j.haianh.dao.Impl.VideoDaoImpl;
import j.haianh.entity.Video;
import j.haianh.services.IvideoService;

public class videoService implements IvideoService{
	IvideoDao videodao=new VideoDaoImpl();
	@Override
	public void insert(Video video) {
		videodao.insert(video);
	}

	@Override
	public void update(Video video) {
		videodao.update(video);
	}

	@Override
	public void delete(int videoid) throws Exception {
		videodao.delete(videoid);
	}

	@Override
	public Video findById(int videoid) {
		return videodao.findById(videoid);
	}

	@Override
	public List<Video> findAll() {
		return videodao.findAll();
	}

	@Override
	public List<Video> findByVideoname(String videoname) {
		return videodao.findByvideoname(videoname);
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videodao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return videodao.count();
	}

}
