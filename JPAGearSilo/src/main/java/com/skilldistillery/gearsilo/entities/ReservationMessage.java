package com.skilldistillery.gearsilo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reservation_message")
public class ReservationMessage {

	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	private String message;

	@Column(name = "message_date")
	private String messageDate;

//	@Column(name = "reservation_id")
//	private String reservationId;

	@Column(name = "shopper_user_id")
	private int shopperUserId;

	// C O N S T R U C T O R S

	public ReservationMessage() {
		super();
	}

	// G E T T E R S __ A N D __ S E T T E R S

	public ReservationMessage(int id, Reservation reservation, String message, String messageDate,
			int shopperUserId) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.message = message;
		this.messageDate = messageDate;
		this.shopperUserId = shopperUserId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public int getShopperUserId() {
		return shopperUserId;
	}

	public void setShopperUserId(int shopperUserId) {
		this.shopperUserId = shopperUserId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((messageDate == null) ? 0 : messageDate.hashCode());
		result = prime * result + ((reservation == null) ? 0 : reservation.hashCode());
		result = prime * result + shopperUserId;
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
		ReservationMessage other = (ReservationMessage) obj;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageDate == null) {
			if (other.messageDate != null)
				return false;
		} else if (!messageDate.equals(other.messageDate))
			return false;
		if (reservation == null) {
			if (other.reservation != null)
				return false;
		} else if (!reservation.equals(other.reservation))
			return false;
		if (shopperUserId != other.shopperUserId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReservationMessage [id=" + id + ", message=" + message + ", messageDate=" + messageDate
				+ ", shopperUserId=" + shopperUserId + "]";
	}

}
