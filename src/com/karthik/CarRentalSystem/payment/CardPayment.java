package com.karthik.CarRentalSystem.payment;

public class CardPayment implements PaymentProcessor
{

	@Override
	public boolean processPayment(double amount)
	{
		System.out.println("Rs." + amount + "  Processed via Card Payment");
		return true;
	}

}
