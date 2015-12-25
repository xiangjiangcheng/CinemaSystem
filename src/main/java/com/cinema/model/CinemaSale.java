package com.cinema.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * CinemaSale
 * Created by rayn on 2015/12/24.
 */
@Entity
@Table(name = "cinema_sale")
public class CinemaSale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "start_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime startTime;

	@Column(name = "end_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime endTime;

	@Column(scale = 2)
	private double money;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cinema_hall_id", nullable = false)
	private CinemaHall cinemaHall;

	@ManyToOne(cascade = CascadeType.ALL)
	private Film film;

	@OneToMany(
			mappedBy = "cinemaSale",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	@OrderBy("order_time desc")
	private Set<Order> orders = new TreeSet<Order>();

	@OneToMany(
			mappedBy = "cinemaSale",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.LAZY
	)
	@OrderBy("row_number asc, col_number asc")
	private Set<Seat> seats = new TreeSet<Seat>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public CinemaHall getCinemaHall() {
		return cinemaHall;
	}

	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
}
