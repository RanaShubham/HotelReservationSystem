package com.bridgelabz.hotelReservationSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HotelReservationSystem
{
	//Hotel reservation list in form of customer name as key and booked hotel object as value
	public static HashMap <String, Hotel> HotelReservationList = new HashMap<>();
	
	public static String cutomerType;
	
	public Object hotel;
	public String customerName;

	public HotelReservationSystem(String customerName, Hotel hotel)
	{
		this.customerName = customerName;
		this.hotel = hotel;
	}
	
	
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Hotel Reservation System");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "ddMMMyyyy" );
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Enter check in date as DDMMMYYYY");
		String checkInDateAsString = scan.next();
		if(checkInDateAsString == null)
		{
			throw new InvalidCheckOutDateException("CheckIn date cannot be null");
		}
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		
		System.out.println("Enter check out date as DDMMMYYYY");
		String checkOutDateAsString = scan.next();
		if(checkOutDateAsString == null)
		{
			throw new InvalidCheckOutDateException("CheckOut date cannot be null");
		}
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);
		
		System.out.println("Enter customer type as 'Reward' or 'Regular'");
		String customerType = scan.next();
		
		Hotel bestHotel = getCostOfHotelStay(checkInDate, checkOutDate, customerType);
		
		System.out.println(bestHotel.getHotelName()+", Rating: "+bestHotel.getRating()+" and Total rates: $"+bestHotel.getCostOfStay());
//		Hotel bestHotel = findHighestRatedHotelForRegularCustomer(checkInDate, checkOutDate);
		
//		System.out.println(bestHotel.getHotelName()+", Rating: "+bestHotel.getRating()+" and Total rates: $"+bestHotel.getCostOfStay());
	}

	/**
	 * Sorts the available hotel on the basis of rating and returns the cost of highest rated hotel for regular customer
	 * @param checkInDate
	 * @param checkOutDate
	 * @return
	 */
	public static Hotel findHighestRatedHotelForRegularCustomer(LocalDate checkInDate, LocalDate checkOutDate) 
	{
		RidgeWood ridgeWood = new RidgeWood();
		LakeWood lakeWood = new LakeWood();
		BridgeWood bridgeWood = new BridgeWood();

		ArrayList<Hotel> availablechoices = new ArrayList<>();
		
		availablechoices.add(bridgeWood);
		availablechoices.add(lakeWood);
		availablechoices.add(ridgeWood);
		
		Collections.sort(availablechoices, (hotelObj1, hotelObj2) -> hotelObj1.getRating() > (hotelObj2.getRating()) ? -1 : 1);
				
		int weekEndCounter = 0;
		int weekDaysCounter = 0;
		LocalDate startingDate =  checkInDate;
		
		long stayDuration = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
				
		if(stayDuration <= 0)
			throw new InvalidCheckOutDateException("Checkin date must be greater than Checkout date");

		while(!startingDate.equals(checkOutDate))
		{
			if(checkInDate.getDayOfWeek() == DayOfWeek.SATURDAY || checkInDate.getDayOfWeek() == DayOfWeek.SUNDAY)
				weekEndCounter++;
			else
				weekDaysCounter++;
			startingDate = startingDate.plusDays(1);
		}
		
		availablechoices.get(0).setCostOfStay(availablechoices.get(0).getWeekDayRegularPrice()*weekDaysCounter+availablechoices.get(0).getWeekEndRegularPrice()*weekEndCounter);		
		return availablechoices.get(0);
	}
	
	/**
	 * Calculates the total price of stay for regular as well as reward customer for all hotels
	 * @param checkInDate
	 * @param checkoutDate
	 */
	public static Hotel getCostOfHotelStay(LocalDate checkInDate, LocalDate checkoutDate, String customerType)
	{
		if(customerType == null)
		{
			throw new InvalidCustomerTypeException("Customer Type cannot be null");
		}
		
		int weekEndCounter = 0;
		int weekDaysCounter = 0;
		LocalDate startingDate =  checkInDate;
		
		long stayDuration = ChronoUnit.DAYS.between(checkInDate, checkoutDate);
				
		if(stayDuration <= 0)
			throw new InvalidCheckOutDateException("Checkin date must be greater than Checkout date");
		
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
		
		if (customerType.equals("Regular"))
		{
			ridgeWood.setCostOfStay(ridgeWood.getWeekDayRegularPrice()*weekDaysCounter+ridgeWood.getWeekEndRegularPrice()*weekEndCounter);
			lakeWood.setCostOfStay(lakeWood.getWeekDayRegularPrice()*weekDaysCounter+lakeWood.getWeekEndRegularPrice()*weekEndCounter);;
			bridgeWood.setCostOfStay(bridgeWood.getWeekDayRegularPrice()*weekDaysCounter+bridgeWood.getWeekEndRegularPrice()*weekEndCounter);
		
			return getBestChoice(ridgeWood, lakeWood, bridgeWood);
		}
		else if (customerType.equals("Reward"))
		{
			ridgeWood.setCostOfStay(ridgeWood.getWeekDayRewardPrice()*weekDaysCounter+ridgeWood.getWeeKEndRewardPrice()*weekEndCounter);
			lakeWood.setCostOfStay(lakeWood.getWeekDayRewardPrice()*weekDaysCounter+lakeWood.getWeeKEndRewardPrice()*weekEndCounter);;
			bridgeWood.setCostOfStay(bridgeWood.getWeekDayRewardPrice()*weekDaysCounter+bridgeWood.getWeeKEndRewardPrice()*weekEndCounter);;
			
			return getBestChoice(ridgeWood, lakeWood, bridgeWood);
		}
		else
		{
			throw new InvalidCustomerTypeException("Customer type not recognized");
		}
	}
	
	/**
	 * Returns cheapest and best rated hotel among all options.
	 * @param ridgeWood
	 * @param lakeWood
	 * @param bridgeWood
	 * @return Hotel that is cheapest
	 */
	private static Hotel getBestChoice(RidgeWood ridgeWood, LakeWood lakeWood, BridgeWood bridgeWood) 
	{
		ArrayList<Hotel> availablechoices = new ArrayList<>();
		
		availablechoices.add(bridgeWood);
		availablechoices.add(lakeWood);
		availablechoices.add(ridgeWood);
		
		Collections.sort(availablechoices, (hotelObj1, hotelObj2) ->hotelObj1.getCostOfStay().compareTo(hotelObj2.getCostOfStay()));
		
		//If all the hotels have same rates for stay duration then choose the one with highest rating
		if(availablechoices.get(0).getCostOfStay().equals(availablechoices.get(1).getCostOfStay()) && availablechoices.get(0).getCostOfStay().equals(availablechoices.get(2)))
		{
			if(availablechoices.get(0).getRating() > availablechoices.get(1).getRating() && availablechoices.get(0).getRating() > availablechoices.get(2).getRating())
				return availablechoices.get(0);
			if(availablechoices.get(1).getRating() > availablechoices.get(0).getRating() && availablechoices.get(1).getRating() > availablechoices.get(2).getRating())
				return availablechoices.get(1);
			if(availablechoices.get(2).getRating() > availablechoices.get(0).getRating() && availablechoices.get(2).getRating() > availablechoices.get(1).getRating())
				return availablechoices.get(1);
		}
		//if first two hotels have same price then we choose hotel with higer rating
		if (availablechoices.get(0).getCostOfStay().equals(availablechoices.get(1).getCostOfStay()))
		{
			if(availablechoices.get(0).getRating() > availablechoices.get(1).getRating())
				return availablechoices.get(0);
			else
				return availablechoices.get(1);
		}
		else
		{
			Hotel choice = availablechoices.get(0);
			return choice;
		}
	}
}
