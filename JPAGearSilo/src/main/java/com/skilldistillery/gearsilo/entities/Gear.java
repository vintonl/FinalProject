package com.skilldistillery.gearsilo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gear {

//	+-------------+--------------+------+-----+---------+----------------+
//	| Field       | Type         | Null | Key | Default | Extra          |
//	+-------------+--------------+------+-----+---------+----------------+
//	| id          | int(11)      | NO   | PRI | NULL    | auto_increment |
//	| name        | varchar(150) | NO   |     | NULL    |                |
//	| condition   | varchar(100) | YES  |     | NULL    |                |
//	| price       | double       | YES  |     | NULL    |                |
//	| description | text         | YES  |     | NULL    |                |
//	| image_url1  | text         | YES  |     | NULL    |                |
//	| available   | tinyint(4)   | NO   |     | 1       |                |
//	| active      | tinyint(4)   | NO   |     | 1       |                |
//	| user_id     | int(11)      | NO   | MUL | NULL    |                |
//	| image_url2  | text         | YES  |     | NULL    |                |
//	| image_url3  | text         | YES  |     | NULL    |                |
//	+-------------+--------------+------+-----+---------+----------------+

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String condition;
	private Double price;
	private String description;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "image_url2")
	private String imageUrl2;
	@Column(name = "image_url3")
	private String imageUrl3;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gear other = (Gear) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private Boolean available;
	private Boolean active;

	public Gear() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl2() {
		return imageUrl2;
	}

	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}

	public String getImageUrl3() {
		return imageUrl3;
	}

	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Gear [id=" + id + ", name=" + name + ", condition=" + condition + ", price=" + price + ", description="
				+ description + ", imageUrl=" + imageUrl + ", imageUrl2=" + imageUrl2 + ", imageUrl3=" + imageUrl3
				+ ", available=" + available + ", active=" + active + "]";
	}

}
