package com.karthik.CarRentalSystem.model;

public class Car
{
	private String make;
	private String model;
	private int year;
	private String licensePlate;
	private double rentalPricePerDay;
	private boolean isAvailable;

	public Car(String make, String model, int year, String licensePlate, double rentalPricePerDay)
	{
		this.make = make;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.rentalPricePerDay = rentalPricePerDay;
		this.isAvailable = true;
	}

	public String getMake()
	{
		return make;
	}

	public String getModel()
	{
		return model;
	}

	public int getYear()
	{
		return year;
	}

	public String getLicensePlate()
	{
		return licensePlate;
	}

	public double getRentalPricePerDay()
	{
		return rentalPricePerDay;
	}

	public boolean isAvailable()
	{
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}

}
