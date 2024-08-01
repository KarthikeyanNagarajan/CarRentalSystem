package com.karthik.CarRentalSystem.model;

import java.time.LocalDate;
import com.karthik.CarRentalSystem.model.Car;
import com.karthik.CarRentalSystem.model.Customer;

public class Reservation
{
	private String reservationId;
	private Customer customer;
	private Car car;
	private LocalDate startDate;
	private LocalDate endDate;
	private double totalPrice;

	public Reservation(String reservationId, Customer customer, Car car, LocalDate startDate, LocalDate endDate,
			double totalPrice)
	{
		this.reservationId = reservationId;
		this.customer = customer;
		this.car = car;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
	}

	public String getReservationId()
	{
		return reservationId;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public Car getCar()
	{
		return car;
	}

	public LocalDate getStartDate()
	{
		return startDate;
	}

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public double getTotalPrice()
	{
		return totalPrice;
	}
}
