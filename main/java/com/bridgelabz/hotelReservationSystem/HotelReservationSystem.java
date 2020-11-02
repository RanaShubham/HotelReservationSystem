package com.bridgelabz.hotelReservationSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HotelReservationSystem
{
	public String hotelName;
	public int regularWeekDayRate;
	public int regularWeekEndPrice;
	public int rating;
	public int rewardPriceWeekDay;
	public int rewardPriceWeekEnd;

	public HotelReservationSystem(String hotelName, int regularWeekDayPrice, int regularWeekEndPrice, int rating, int rewardPriceWeekDay, int rewardPriceWeekEnd)
	{
		this.hotelName = hotelName;
		this.regularWeekDayRate = regularWeekDayPrice;
		this.regularWeekEndPrice = regularWeekEndPrice;
		this.rating = rating;
		this.rewardPriceWeekDay = rewardPriceWeekDay;
		this.rewardPriceWeekEnd = rewardPriceWeekEnd;
	}
	
	
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Hotel Reservation System");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "ddMMMyyyy" );
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Enter check in date as DDMMMYYYY");
		String checkInDateAsString = scan.next();
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		
		System.out.println("Enter check out date as DDMMMYYYY");
		String checkOutDateAsString = scan.next();
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);
//		
//		Hotel bestHotel = findCheapestHotelForRegularCustomer(checkInDate, checkOutDate);
//		
//			System.out.println(bestHotel.getHotelName()+", Rating: "+bestHotel.getRating()+" and Total rates: $"+bestHotel.getCostOfStay());
		Hotel bestHotel = findHighestRatedHotelForRegularCustomer(checkInDate, checkOutDate);
		
		System.out.println(bestHotel.getHotelName()+", Rating: "+bestHotel.getRating()+" and Total rates: $"+bestHotel.getCostOfStay());
	}

	/**
	 * To find the hotel with highest rating
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
	 * @param hotel name
	 * @param weekday regular price
	 * @param weekend regular price
	 * @return Returns a HotelReservationSystem object with name and rates for regular customer.
	 */
	public static HotelReservationSystem addHotelForRegularCustomer(String hotelName, int weekdayRegularPrice, int weekendRegularPrice, int rating, int rewardPriceWeekday, int rewardPriceWeekend) 
	{
		return new HotelReservationSystem(hotelName, weekdayRegularPrice, weekendRegularPrice, rating, rewardPriceWeekday, rewardPriceWeekend);
	}
	
	/**
	 * Calculates the price of all hotels based on stay duration.
	 * @param checkInDate
	 * @param checkoutDate
	 */
	public static Hotel findCheapestHotelForRegularCustomer(LocalDate checkInDate, LocalDate checkoutDate)
	{	
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
