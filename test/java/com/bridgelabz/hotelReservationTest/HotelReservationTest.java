package com.bridgelabz.hotelReservationTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.bridgelabz.hotelReservationSystem.Hotel;
import com.bridgelabz.hotelReservationSystem.HotelReservationSystem;
import com.bridgelabz.hotelReservationSystem.InvalidCheckOutDateException;
import com.bridgelabz.hotelReservationSystem.RidgeWood;

public class HotelReservationTest {

	@Test
	public void addingHotelObjectToTheHtoelmanagementSsetm_ShouldContainCorrectPricesAndNameOfTheHotel() 
	{
		//Hotel object for which we need to create HotelReservationSystem object
		RidgeWood ridgeWood = new RidgeWood();
		
		HotelReservationSystem newHotel = HotelReservationSystem.addHotelForRegularCustomer(ridgeWood.getHotelName(), ridgeWood.getWeekDayRegularPrice(), ridgeWood.getWeekEndRegularPrice(), ridgeWood.getRating());
		boolean result = newHotel.hotelName.equals(ridgeWood.getHotelName()) && 
		newHotel.regularWeekDayRate ==  ridgeWood.getWeekDayRegularPrice() &&
		newHotel.regularWeekEndPrice == ridgeWood.getWeekEndRegularPrice();

		assertTrue(result);
	}
	
	@Test
	public void gettingCheapestHotelOnWeekDay_whenStayingForOneDay_ShouldReturnLakeWood()
	{

		String checkInDateAsString = "02Nov2020";
		String checkOutDateAsString = "03Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		Hotel cheaptestHotel = HotelReservationSystem.findCheapestHotelForRegularCustomer(checkInDate, checkOutDate);
		
		assertEquals("LakeWood", cheaptestHotel.getHotelName());
	}
	
	@Test
	public void gettingCheapestHotel_withBestRating_WhenStayingForWeekendShoudlReturnBridgeWood()
	{

		String checkInDateAsString = "31Oct2020";
		String checkOutDateAsString = "01Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		Hotel cheaptestHotel = HotelReservationSystem.findCheapestHotelForRegularCustomer(checkInDate, checkOutDate);
		
		assertEquals("BridgeWood", cheaptestHotel.getHotelName());
	}
	
	@Test(expected = InvalidCheckOutDateException.class)
	public void whenEnteringCheckOutDate_IfEnteredCheckoutDateSmallerThanCheckInDate_SHouldThrowInvalidCheckoutDateException()
	{
		String checkInDateAsString = "06Nov2020";
		String checkOutDateAsString = "03Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		Hotel cheaptestHotel = HotelReservationSystem.findCheapestHotelForRegularCustomer(checkInDate, checkOutDate);
	}
	
	@Test
	public void searchingForHighestRated_ShoudlReturnRidgeWood()
	{

		String checkInDateAsString = "31Oct2020";
		String checkOutDateAsString = "01Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		Hotel highestRated = HotelReservationSystem.findHighestRatedHotelForRegularCustomer(checkInDate, checkOutDate);
		
		assertEquals("RidgeWood", highestRated.getHotelName());
	}
}
