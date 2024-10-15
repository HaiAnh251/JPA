package j.haianh.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="Product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProductID;
	private int stoke;
	private int Amount;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String Description;
	@Column(columnDefinition = "nvarchar(255)")
	private String ProductName;
	private String imageLink;
	private int SellerID;
	private int Price;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CategoryID")
	private Category category;
}

