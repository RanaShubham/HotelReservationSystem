package com.bridgelabz.hotelReservationSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelReservationSystem
{
	//Hotel reservation list in form of customer name as key and booked hotel object as value
	public static HashMap <String, Hotel> HotelReservationList = new HashMap<>();
	
	public static String cutomerType;

	public static String DATE_REGEX = "^[0-3]{1}[0-9]{1}[A-Z]{1}[a-z]{2}[0-9]{4}$";
	
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
		verifyDate(checkInDateAsString, DATE_REGEX);
		
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		
		System.out.println("Enter check out date as DDMMMYYYY");
		String checkOutDateAsString = scan.next();
		verifyDate(checkOutDateAsString, DATE_REGEX);
		
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);
		
		System.out.println("Enter customer type as 'Reward' or 'Regular'");
		String customerType = scan.next();
				
		//Getting cheapest hotel for both customer types
		Hotel bestHotel = getBestHotelHotelByCostAndRating(checkInDate, checkOutDate, customerType);
		System.out.println("Most affordable hotel for "+customerType+" customer is "+bestHotel.getHotelName()+", Rating: "+bestHotel.getRating()+" and Total rates: $"+bestHotel.getCostOfStay());
	}

	public static void verifyDate(String date, String DATE_REGEX) 
	{
		try {
			if(date.isBlank())
				throw new InvalidCheckOutDateException("Date cannot be empty");
			Pattern datePattern = Pattern.compile(DATE_REGEX);
			Matcher match = datePattern.matcher(date);
			
			if(!match.find())
				throw new InvalidCheckOutDateException("Entered incorrect date");
		
		} catch (NullPointerException e) 
		{
			throw new InvalidCheckOutDateException("Dates cannot be null");
		}
	}


	/**
	 * Sorts the available hotel on the basis of rating and returns the cost of highest rated hotel for regular customer
	 * @param checkInDate
	 * @param checkOutDate
	 * @return
	 */
	public static Hotel findHighestRatedHotelForCustomer(LocalDate checkInDate, LocalDate checkOutDate, String customerType) 
	{
		if(customerType == null)
		{
			throw new InvalidCustomerTypeException("Customer Type cannot be null");
		}
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
		
		if(customerType.equals("Regular"))
		{
			availablechoices.get(0).setCostOfStay(availablechoices.get(0).getWeekDayRegularPrice()*weekDaysCounter+availablechoices.get(0).getWeekEndRegularPrice()*weekEndCounter);		
			return availablechoices.get(0);
		}
		else if(customerType.equals("Reward"))
		{
			availablechoices.get(0).setCostOfStay(availablechoices.get(0).getWeekDayRewardPrice()*weekDaysCounter+availablechoices.get(0).getWeekEndRegularPrice()*weekEndCounter);		
			return availablechoices.get(0);
		}
		else
		{
			throw new InvalidCustomerTypeException("Customer type not recognized");
		}
	}
	
	/**
	 * Fetches best hotel for regular as well as reward customer for among all hotels based on rating and price.
	 * @param checkInDate
	 * @param checkoutDate
	 */
	public static Hotel getBestHotelHotelByCostAndRating(LocalDate checkInDate, LocalDate checkoutDate, String customerType)
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
	 * @return Hotel that has best rating among multiple cheapest hotels
	 */
	private static Hotel getBestChoice(RidgeWood ridgeWood, LakeWood lakeWood, BridgeWood bridgeWood) 
	{
		ArrayList<Hotel> availablechoices = new ArrayList<>();
		
		availablechoices.add(bridgeWood);
		availablechoices.add(lakeWood);
		availablechoices.add(ridgeWood);
		
		Collections.sort(availablechoices, (hotelObj1, hotelObj2) ->hotelObj1.getCostOfStay().compareTo(hotelObj2.getCostOfStay()));
		
		Hotel choice = availablechoices.stream().filter(hotelObj1 -> hotelObj1.getCostOfStay().equals(availablechoices.get(0).getCostOfStay())).
		sorted((HotelObj1, HotelObj2) -> -1*HotelObj1.getRating().compareTo(HotelObj2.getRating())).findFirst().get();
		
		return choice;
	}
}
