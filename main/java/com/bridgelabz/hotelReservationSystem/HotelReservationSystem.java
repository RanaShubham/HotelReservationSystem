package com.bridgelabz.hotelReservationSystem;

import java.time.DayOfWeek;
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

	public HotelReservationSystem(String hotelName, int regularWeekDayPrice, int regularWeekEndPrice)
	{
		this.hotelName = hotelName;
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
		
		Hotel[] bestHotel = findCheapestHotelForRegularCustomer(checkInDate, checkOutDate);
		
		if(bestHotel.length == 1)
			System.out.println(bestHotel[0].getHotelName()+", Total rates: $"+bestHotel[0].getCostOfStay());
		else
			System.out.println(bestHotel[0].getHotelName()+" and "+bestHotel[1].getHotelName()+", Total rates: $"+bestHotel[0].getCostOfStay());
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
	 * Calculates the price of all hotels based on stay duration.
	 * @param checkInDate
	 * @param checkoutDate
	 */
	public static Hotel[] findCheapestHotelForRegularCustomer(LocalDate checkInDate, LocalDate checkoutDate)
	{	
		int weekEndCounter = 0;
		int weekDaysCounter = 0;
		LocalDate startingDate =  checkInDate;
		
		Period stayDuration = Period.between(checkInDate, checkoutDate);
		
		if(stayDuration.getDays() <= 0)
			throw new InvalidCheckOutDateException("Check in date must be greater than Checkout date");
		
		RidgeWood ridgeWood = new RidgeWood();
		LakeWood lakeWood = new LakeWood();
		BridgeWood bridgeWood = new BridgeWood();
		
		while(!startingDate.equals(checkoutDate))
		{
			if(checkInDate.getDayOfWeek() == DayOfWeek.SATURDAY || checkInDate.getDayOfWeek() == DayOfWeek.SUNDAY)
				weekEndCounter++;
			else
				weekDaysCounter++;
			startingDate = startingDate.plusDays(1);
		}
		
		ridgeWood.setCostOfStay(ridgeWood.getWeekDayRegularPrice()*weekDaysCounter+ridgeWood.getWeekEndRegularPrice()*weekEndCounter);
		lakeWood.setCostOfStay(lakeWood.getWeekDayRegularPrice()*weekDaysCounter+lakeWood.getWeekEndRegularPrice()*weekEndCounter);;
		bridgeWood.setCostOfStay(bridgeWood.getWeekDayRegularPrice()*weekDaysCounter+bridgeWood.getWeekEndRegularPrice()*weekEndCounter);;
		
		return getBestChoice(ridgeWood, lakeWood, bridgeWood);
	}

	/**
	 * To calculate rates of various available choices to return cheapest hotel.
	 * @param ridgeWood
	 * @param lakeWood
	 * @param bridgeWood
	 * @return Hotel that is cheapest
	 */
	private static Hotel[] getBestChoice(RidgeWood ridgeWood, LakeWood lakeWood, BridgeWood bridgeWood) 
	{
		ArrayList<Hotel> availablechoices = new ArrayList<>();
		
		availablechoices.add(bridgeWood);
		availablechoices.add(lakeWood);
		availablechoices.add(ridgeWood);
		
		Collections.sort(availablechoices, (hotelObj1, hotelObj2) ->hotelObj1.getCostOfStay().compareTo(hotelObj2.getCostOfStay()));
		
		//if first two hotels have same price then we have two cheapest hotels
		if (availablechoices.get(0).getCostOfStay().equals(availablechoices.get(1).getCostOfStay()))
		{
			Hotel [] choices = { availablechoices.get(0), availablechoices.get(1) };
			return choices;
		}
		else
		{
			Hotel [] choices = { availablechoices.get(0)};
			return choices;
		}
	}

}
