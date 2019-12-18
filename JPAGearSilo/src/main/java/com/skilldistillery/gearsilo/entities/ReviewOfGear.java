package com.skilldistillery.gearsilo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review_of_gear")
public class ReviewOfGear {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Integer rating;
	private String review;

	@OneToOne
	@JoinColumn(name = "gear_id")
	private Reservation reservation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((review == null) ? 0 : review.hashCode());
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
		ReviewOfGear other = (ReviewOfGear) obj;
		if (id != other.id)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		return true;
	}

	public ReviewOfGear(int id, Integer rating, String review) {
		super();
		this.id = id;
		this.rating = rating;
		this.review = review;
	}

	public ReviewOfGear() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReviewOfGear [id=");
		builder.append(id);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", review=");
		builder.append(review);
		builder.append("]");
		return builder.toString();
	}

}