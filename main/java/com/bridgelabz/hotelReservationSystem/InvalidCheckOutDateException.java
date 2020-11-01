package com.bridgelabz.hotelReservationSystem;

public class InvalidCheckOutDateException extends RuntimeException 
{
	public InvalidCheckOutDateException(String message)
	{
		super(message);
	}
}
