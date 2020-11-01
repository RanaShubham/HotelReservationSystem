package com.bridgelabz.hotelReservationTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bridgelabz.hotelReservationSystem.HotelReservationSystem;
import com.bridgelabz.hotelReservationSystem.RidgeWood;

public class HotelReservationTest {

	@Test
	public void addingHotelObjectToTheHtoelmanagementSsetm_ShouldContainCorrectPricesAndNameOfTheHotel() 
	{
		//Hotel object for which we need to create HotelReservationSystem object
		RidgeWood ridgeWood = new RidgeWood();
		
		HotelReservationSystem newHotel = HotelReservationSystem.addHotelForRegularCustomer(ridgeWood.getHotelName(), ridgeWood.getWeekDayRegularPrice(), ridgeWood.getWeekEndRegularPrice());
		boolean result = newHotel.hotelName.equals(ridgeWood.getHotelName()) && 
		newHotel.regularWeekDayRate ==  ridgeWood.getWeekDayRegularPrice() &&
		newHotel.regularWeekEndPrice == ridgeWood.getWeekEndRegularPrice();
		
		assertTrue(result);
	}
}
