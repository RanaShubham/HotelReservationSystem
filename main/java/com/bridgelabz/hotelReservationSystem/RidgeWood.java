package com.bridgelabz.hotelReservationSystem;

public class RidgeWood extends Hotel
{
	private final String HOTEL_NAME = "RidgeWood";
	private final int RATING = 5;
	private final int WEEKDAY_REGULAR_PRICE = 220;
	private final int WEEKDAY_REWARD_PRICE = 100;
	private final int WEEKEND_REGULAR_PRICE = 150;
	private final int WEEKEND_REWARD_PRICE = 40;
	
	@Override
	public int getRating() {
		// TODO Auto-generated method stub
		return RATING;
	}
	@Override
	public int getWeekDayRegularPrice() {
		// TODO Auto-generated method stub
		return WEEKDAY_REGULAR_PRICE;
	}
	@Override
	public int getWeekDayRewardPrice() {
		// TODO Auto-generated method stub
		return WEEKDAY_REWARD_PRICE;
	}
	@Override
	public int getWeekEndRegularPrice() {
		// TODO Auto-generated method stub
		return WEEKEND_REGULAR_PRICE;
	}
	@Override
	public int getWeeKEndRewardPrice() {
		// TODO Auto-generated method stub
		return WEEKEND_REWARD_PRICE;
	}
	@Override
	public String getHotelName() {
		// TODO Auto-generated method stub
		return HOTEL_NAME;
	}
}
