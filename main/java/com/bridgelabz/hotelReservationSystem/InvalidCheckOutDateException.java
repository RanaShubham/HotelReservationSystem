package com.bridgelabz.hotelReservationSystem;

public class InvalidCheckOutDateException extends RuntimeException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCheckOutDateException(String message)
	{
		super(message);
	}
}
