package j.haianh.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UserID")
	private int userid;
	@Id
	@Column(name="Username")
	private String username;

	@Column(name="RoleID")
	private int roleid;
	
	@Column(name="Active")
	private boolean active;

	@Column(name="Admin")
	private boolean admin;

	@Column(name="Email")
	@Email(message = "Nhap dung dinh dang email")
	@NotEmpty (message = "Hay nhap email")
	private String email;
	
	@Column(name="Phone")
	@Pattern (regexp = "^\\d{8,10}$", message = "So dien thoai tu 8-10 so")
	@NotEmpty (message = "Hay nhap sdt")
	private String phone;


	@Column(name="Fullname", columnDefinition = "nvarchar(255)")
	private String fullname;

	@Column(name="Password", columnDefinition = "nvarchar(255)")
	private String password;
	
	@Column(name="Images", columnDefinition = "nvarchar(500)")
	private String images;
}
