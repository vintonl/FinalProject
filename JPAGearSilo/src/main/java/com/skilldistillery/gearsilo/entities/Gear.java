package com.skilldistillery.gearsilo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

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
	private Boolean available;
	private Boolean active;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "gear_category", joinColumns = @JoinColumn(name = "gear_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	// C T O R S

	public Gear(int id, String name, String condition, Double price, String description, String imageUrl,
			String imageUrl2, String imageUrl3, Boolean available, Boolean active, List<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.condition = condition;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
		this.imageUrl2 = imageUrl2;
		this.imageUrl3 = imageUrl3;
		this.available = available;
		this.active = active;
		this.categories = categories;
	}

	// G E T T E R S __ A N D __ S E T T E R S
	public Gear() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((imageUrl2 == null) ? 0 : imageUrl2.hashCode());
		result = prime * result + ((imageUrl3 == null) ? 0 : imageUrl3.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (imageUrl2 == null) {
			if (other.imageUrl2 != null)
				return false;
		} else if (!imageUrl2.equals(other.imageUrl2))
			return false;
		if (imageUrl3 == null) {
			if (other.imageUrl3 != null)
				return false;
		} else if (!imageUrl3.equals(other.imageUrl3))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gear [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", condition=");
		builder.append(condition);
		builder.append(", price=");
		builder.append(price);
		builder.append(", description=");
		builder.append(description);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", imageUrl2=");
		builder.append(imageUrl2);
		builder.append(", imageUrl3=");
		builder.append(imageUrl3);
		builder.append(", available=");
		builder.append(available);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

}
