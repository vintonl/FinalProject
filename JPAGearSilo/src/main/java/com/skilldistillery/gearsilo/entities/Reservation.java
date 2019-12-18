package com.skilldistillery.gearsilo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "open_date")
	private Date openDate;

	@Column(name = "close_date")
	private Date closeDate;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "gear_id")
	private Gear gearId;

	@OneToOne(mappedBy = "reservation")
	private ReviewOfLender lenderReview;

	@OneToOne(mappedBy = "reservation")
	private ReviewOfGear gearReview;

	private boolean completed;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "shopper_user_id")
	private User userShopper;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	private boolean approved;

	public Reservation(int id, Date openDate, Date closeDate, Gear gearId, ReviewOfLender lenderReview,
			boolean completed, User userShopper, Date createdAt, Date updatedAt, boolean approved) {
		super();
		this.id = id;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.gearId = gearId;
		this.lenderReview = lenderReview;
		this.completed = completed;
		this.userShopper = userShopper;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.approved = approved;
	}

	public ReviewOfGear getGearReview() {
		return gearReview;
	}

	public void setGearReview(ReviewOfGear gearReview) {
		this.gearReview = gearReview;
	}

	public Reservation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReviewOfLender getLenderReview() {
		return lenderReview;
	}

	public void setLenderReview(ReviewOfLender lenderReview) {
		this.lenderReview = lenderReview;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Gear getGearId() {
		return gearId;
	}

	public void setGearId(Gear gearId) {
		this.gearId = gearId;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public User getUserShopper() {
		return userShopper;
	}

	public void setUserShopper(User userShopper) {
		this.userShopper = userShopper;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((closeDate == null) ? 0 : closeDate.hashCode());
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((gearId == null) ? 0 : gearId.hashCode());
		result = prime * result + ((gearReview == null) ? 0 : gearReview.hashCode());
		result = prime * result + id;
		result = prime * result + ((lenderReview == null) ? 0 : lenderReview.hashCode());
		result = prime * result + ((openDate == null) ? 0 : openDate.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((userShopper == null) ? 0 : userShopper.hashCode());
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
		Reservation other = (Reservation) obj;
		if (approved != other.approved)
			return false;
		if (closeDate == null) {
			if (other.closeDate != null)
				return false;
		} else if (!closeDate.equals(other.closeDate))
			return false;
		if (completed != other.completed)
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (gearId == null) {
			if (other.gearId != null)
				return false;
		} else if (!gearId.equals(other.gearId))
			return false;
		if (gearReview == null) {
			if (other.gearReview != null)
				return false;
		} else if (!gearReview.equals(other.gearReview))
			return false;
		if (id != other.id)
			return false;
		if (lenderReview == null) {
			if (other.lenderReview != null)
				return false;
		} else if (!lenderReview.equals(other.lenderReview))
			return false;
		if (openDate == null) {
			if (other.openDate != null)
				return false;
		} else if (!openDate.equals(other.openDate))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (userShopper == null) {
			if (other.userShopper != null)
				return false;
		} else if (!userShopper.equals(other.userShopper))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", openDate=" + openDate + ", closeDate=" + closeDate + ", gearId=" + gearId
				+ ", lenderReview=" + lenderReview + ", gearReview=" + gearReview + ", completed=" + completed
				+ ", userShopper=" + userShopper + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", approved=" + approved + "]";
	}

}
