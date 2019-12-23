package com.skilldistillery.gearsilo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| Field      | Type         | Null | Key | Default           | Extra                       |
//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| id         | int(11)      | NO   | PRI | NULL              | auto_increment              |
//	| first_name | varchar(100) | NO   |     | NULL              |                             |
//	| last_name  | varchar(100) | NO   |     | NULL              |                             |
//	| email      | varchar(150) | NO   |     | NULL              |                             |
//	| password   | varchar(300) | NO   |     | NULL              |                             |
//	| created_at | date         | YES  |     | NULL              |                             |
//	| updated_at | datetime     | YES  |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
//	| role       | varchar(45)  | NO   |     | 0                 |                             |
//	| image_url  | text         | YES  |     | NULL              |                             |
//	| about      | text         | YES  |     | NULL              |                             |
//	| address_id | int(11)      | YES  | MUL | NULL              |                             |
//	| phone      | varchar(45)  | NO   |     | NULL              |                             |
//	| enabled    | tinyint(4)   | YES  |     | NULL              |                             |
//	| username   | varchar(150) | NO   |     | NULL              |                             |
//	+------------+--------------+------+-----+-------------------+-----------------------------+

	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;
	
	private String username;

	private String password;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	private String role;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Gear> gearList;

	@JsonIgnore
	@OneToMany(mappedBy = "userShopper")
	private List<Reservation> reservations;

	@Column(name = "image_url")
	private String imageUrl;

	private String about;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	private String phone;
	private Boolean enabled;

	// C O N S T R U C T O R S
	public User() {
		super();
	}

	public User(int id, String firstName, String lastName, String email, String password, Date createdAt,
			Date updatedAt, String role, List<Gear> gearList, List<Reservation> reservations, String imageUrl,
			String about, Address address, String phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.gearList = gearList;
		this.reservations = reservations;
		this.imageUrl = imageUrl;
		this.about = about;
		this.address = address;
		this.phone = phone;
	}

	// G E T T E R S __A N D __ S E T T E R S

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Gear> getGearList() {
		return gearList;
	}

	public void setGearList(List<Gear> gearList) {
		this.gearList = gearList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return email;
	}

	public void setUsername(String email) {
		this.username = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", role=" + role
				+ ", imageUrl=" + imageUrl + ", about=" + about + ", address=" + address + ", phone=" + phone
				+ ", enabled=" + enabled + "]";
	}

}
