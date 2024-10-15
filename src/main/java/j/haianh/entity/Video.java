package j.haianh.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable{

	private static final long serialVersionUID = -2342360494080313720L;
	 @Id
	 
	 @Column(name="VideoId")
	 private String videoId;
	 
	 @Column(name="VideoName")
	 private String videoname;
	 
	 @Column(name="Active")
	 private int active;
	 
	 @Column(name = "Icon", columnDefinition = "NVARCHAR(500) NULL")
		private String icon;
	 
	 @Column(name="Description", columnDefinition = "NVARCHAR(500)")
	 private String description;
	 
	 @Column(name="Poster")
	 private String poster;
	 
	 @Column(name="Title", columnDefinition = "NVARCHAR(500)")
	 private String title;
	 
	 @Column(name="Views")
	 private int views;
	 
	 @Column(name="Filename")
	 private int filename;
	 
	 @Column(name="UploadDate")
	 private Date uploaddate;
	//bi-directional many-to-one association to Category
	 @ManyToOne
	 @JoinColumn(name="CategoryId")
	 private Category category;

	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	 
	 
	 
}
