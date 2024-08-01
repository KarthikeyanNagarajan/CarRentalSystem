package com.karthik.CarRentalSystem.model;

public class Customer
{
	private String name;
	private String contact;
	private String licenseNumber;

	public Customer(String name, String contact, String licenseNumber)
	{
		this.name = name;
		this.contact = contact;
		this.licenseNumber = licenseNumber;
	}

	public String getName()
	{
		return name;
	}

	public String getContact()
	{
		return contact;
	}

	public String getLicenseNumber()
	{
		return licenseNumber;
	}
}
