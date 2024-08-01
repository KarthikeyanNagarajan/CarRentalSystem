package com.karthik.CarRentalSystem.payment;

public class UPIPayment implements PaymentProcessor
{

	@Override
	public boolean processPayment(double amount)
	{
		System.out.println("Rs." + amount + "  Processed via UPI Payment");
		return true;
	}

}
