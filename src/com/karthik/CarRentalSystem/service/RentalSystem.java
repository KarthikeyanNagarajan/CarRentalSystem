package com.karthik.CarRentalSystem.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.karthik.CarRentalSystem.model.Car;
import com.karthik.CarRentalSystem.model.Customer;
import com.karthik.CarRentalSystem.model.Reservation;
import com.karthik.CarRentalSystem.payment.PaymentProcessor;
import com.karthik.CarRentalSystem.payment.UPIPayment;

public class RentalSystem
{
	private static RentalSystem instance;
	private Map<String, Car> cars;
	private Map<String, Reservation> reservations;
	private PaymentProcessor paymentProcessor;

	public static RentalSystem getInstance()
	{
		if (instance == null)
			return new RentalSystem();
		return instance;
	}

	private RentalSystem()
	{
		cars = new HashMap<>();
		reservations = new HashMap<>();
		paymentProcessor = new UPIPayment();
	}

	public void addCar(Car car)
	{
		cars.put(car.getLicensePlate(), car);
	}

	public void removeCar(String licensePlate)
	{
		cars.remove(licensePlate);
	}

	public List<Car> searchCars(String make, String model, LocalDate startDate, LocalDate endDate)
	{
		List<Car> availableCars = new ArrayList<>();
		for (Car car : cars.values())
		{
			if (car.getMake().equals(make) && car.getModel().equals(model) && car.isAvailable())
			{
				if (isCarAvailable(car, startDate, endDate))
				{
					availableCars.add(car);
				}
			}
		}
		return availableCars;
	}

	private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate)
	{
		for (Reservation reservation : reservations.values())
		{
			if (reservation.getCar().equals(car))
			{
				if (startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate()))
				{
					return false;
				}
			}
		}
		return true;
	}

	public synchronized Reservation makeReservation(Customer customer, Car car, LocalDate startDate, LocalDate endDate)
	{
		if (isCarAvailable(car, startDate, endDate))
		{
			String reservationId = generateReservationId();
			double totalPrice = calculateTotalPrice(car, startDate, endDate);
			Reservation reservation = new Reservation(reservationId, customer, car, startDate, endDate, totalPrice);
			reservations.put(reservationId, reservation);
			car.setAvailable(false);
			return reservation;
		}
		return null;
	}
	
	private double calculateTotalPrice(Car car, LocalDate startDate, LocalDate endDate)
	{
		long daysRented = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		return car.getRentalPricePerDay() * daysRented;
	}

	public synchronized void cancelReservation(String reservationId)
	{
		Reservation reservation = reservations.remove(reservationId);
		if (reservation != null)
			reservation.getCar().setAvailable(true);
	}

	public boolean processPayment(Reservation reservation)
	{
		return paymentProcessor.processPayment(reservation.getTotalPrice());
	}

	private String generateReservationId()
	{
		return "RES" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}
}
