package j.haianh.dao;

import java.util.List;

import j.haianh.entity.Video;

public interface IvideoDao {

	void insert(Video video);
	void update(Video video);
	void delete(int videoid ) throws Exception;
	Video findById( int videoid);
	List<Video> findAll();
	List<Video> findByvideoname(String videoname);
	List<Video> findAll(int page, int pagesize);
	int count();
}
