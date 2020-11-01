package com.bridgelabz.hotelReservationSystem;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Enter check in date as DDMMMYYYY");
		String checkInDateAsString = scan.next();
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		
		System.out.println("Enter check out date as DDMMMYYYY");
		String checkOutDateAsString = scan.next();
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);
		
		Hotel bestHotel = findCheapestHotelForRegularCustomer(checkInDate, checkOutDate);
		
		System.out.println(bestHotel.getHotelName()+", Total rates: $"+bestHotel.getCostOfStay());
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
	
	/**
	 * 
	 * @param checkInDate
	 * @param checkoutDate
	 */
	public static Hotel findCheapestHotelForRegularCustomer(LocalDate checkInDate, LocalDate checkoutDate)
	{	
		Period stayDuration = Period.between(checkInDate, checkoutDate);
		if(stayDuration.getDays() <= 0)
			throw new InvalidCheckOutDateException("Check in date must be greater than Checkout date");
		int daysOfStay = stayDuration.getDays();
		
		RidgeWood ridgeWood = new RidgeWood();
		LakeWood lakeWood = new LakeWood();
		BridgeWood bridgeWood = new BridgeWood();
		
		ridgeWood.setCostOfStay(ridgeWood.getWeekDayRegularPrice()*daysOfStay);
		lakeWood.setCostOfStay(lakeWood.getWeekDayRegularPrice()*daysOfStay);;
		bridgeWood.setCostOfStay(bridgeWood.getWeekDayRegularPrice()*daysOfStay);;
		
		return getBestChoice(ridgeWood, lakeWood, bridgeWood );
	}

	private static Hotel getBestChoice(RidgeWood ridgeWood, LakeWood lakeWood, BridgeWood bridgeWood) 
	{
		ArrayList<Hotel> availablechoices = new ArrayList<>();
		
		availablechoices.add(bridgeWood);
		availablechoices.add(lakeWood);
		availablechoices.add(ridgeWood);
		
		Collections.sort(availablechoices, (hotelObj1, hotelObj2) ->hotelObj1.getCostOfStay().compareTo(hotelObj2.getCostOfStay()));
		
		return availablechoices.get(0);
	}

}
