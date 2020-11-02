package com.bridgelabz.hotelReservationTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.bridgelabz.hotelReservationSystem.Hotel;
import com.bridgelabz.hotelReservationSystem.HotelReservationSystem;
import com.bridgelabz.hotelReservationSystem.InvalidCheckOutDateException;
import com.bridgelabz.hotelReservationSystem.InvalidCustomerTypeException;

public class HotelReservationTest {
	
	@Test
	public void gettingCheapestHotelOnWeekDay_whenStayingForOneDay_ShouldReturnLakeWood()
	{

		String checkInDateAsString = "02Nov2020";
		String checkOutDateAsString = "03Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		Hotel cheaptestHotel = HotelReservationSystem.getCostOfHotelStay(checkInDate, checkOutDate, "Regular");
		
		assertEquals("LakeWood", cheaptestHotel.getHotelName());
	}
	
	@Test
	public void gettingCheapestHotelForRegularCutomer_withBestRating_WhenStayingForWeekendShoudlReturnBridgeWood()
	{

		String checkInDateAsString = "31Oct2020";
		String checkOutDateAsString = "01Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		Hotel cheaptestHotel = HotelReservationSystem.getCostOfHotelStay(checkInDate, checkOutDate, "Regular");
		
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

		HotelReservationSystem.getCostOfHotelStay(checkInDate, checkOutDate, "Regular");
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
	
	@Test(expected = InvalidCustomerTypeException.class)
	public void whenEnteringCustomerType_IfEnteredWrong_ShouldThrowInvalidCustomerTypeException()
	{
		String checkInDateAsString = "06Nov2020";
		String checkOutDateAsString = "09Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		HotelReservationSystem.getCostOfHotelStay(checkInDate, checkOutDate, "Reular");
	}
	
	@Test(expected = InvalidCustomerTypeException.class)
	public void whenEnteringCustomerType_IfEnterednull_ShouldThrowInvalidCustomerTypeException()
	{
		String checkInDateAsString = "06Nov2020";
		String checkOutDateAsString = "09Nov2020";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dMMMyyyy" );
		LocalDate checkInDate = LocalDate.parse(checkInDateAsString , formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOutDateAsString , formatter);

		HotelReservationSystem.getCostOfHotelStay(checkInDate, checkOutDate, null);
	}
}
