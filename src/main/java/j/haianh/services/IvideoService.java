package j.haianh.services;

import java.util.List;

import j.haianh.entity.Video;

public interface IvideoService {

	void insert(Video video);
	void update(Video video);
	void delete(int videoid) throws Exception;
	Video findById(int videoid);
	List<Video> findAll();
	List<Video> findByVideoname(String videoname);
	List<Video> findAll(int page,int pagesize);
	int count();

}
