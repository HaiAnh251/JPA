package j.haianh.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {

	private static final long serialVersionUID = 995741083229879773L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private int categoryId;
	@Column(name = "Categoryname", columnDefinition = "NVARCHAR(50) NOT NULL")
	private String categoryname;
	@Column(name = "Images", columnDefinition = "NVARCHAR(500) NULL")
	private String images;
	@Column(name = "Icon", columnDefinition = "NVARCHAR(500) NULL")
	private String icon;
	
	@Column(name = "Status")
	private int status;
	// bi-directional many-to-one association to Video
	@OneToMany(mappedBy = "category")
	private List<Video> videos;

	

	public Video addVideo(Video video) {

		getVideos().add(video);

		video.setCategory(this);

		return video;

	}

	public Video removeVideo(Video video) {

		getVideos().remove(video);

		video.setCategory(null);

		return video;

	}

	

	
}
