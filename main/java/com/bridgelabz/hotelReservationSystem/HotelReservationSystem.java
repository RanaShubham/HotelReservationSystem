package com.bridgelabz.hotelReservationSystem;

public class HotelReservationSystem
{
	public String hotelName;
	public int regularWeekDayRate;
	public int regularWeekEndPrice;
	
	public HotelReservationSystem(String hotelname, int regularWeekDayPrice, int regularWeekEndPrice)
	{
		this.hotelName = hotelname;
		this.regularWeekDayRate = regularWeekDayPrice;
		this.regularWeekEndPrice = regularWeekEndPrice;
	}
	
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Hotel Reservation System");		
	}

	/**
	 * @param hotel name
	 * @param weekday regular price
	 * @param weekend regular price
	 * @return Returns a HotelReservationSystem object with name and rates for regular customer.
	 */
	public static HotelReservationSystem addHotelForRegularCustomer(String hotelName, int weekdayRegularPrice, int weekendRegularPrice) 
	{
		return new HotelReservationSystem(hotelName, weekdayRegularPrice, weekendRegularPrice);
	}
}
