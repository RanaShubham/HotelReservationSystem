package com.bridgelabz.hotelReservationSystem;

public class BridgeWood extends Hotel
{
	final String HOTEL_NAME = "BridgeWood";
	final int RATING = 4;
	final int WEEKDAY_REGULAR_PRICE = 160;
	final int WEEKDAY_REWARD_PRICE = 110;
	final int WEEKEND_REGULAR_PRICE = 60;
	final int WEEKEND_REWARD_PRICE = 50;
	public Integer costOfStay = 0;
	
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
	@Override
	public void setCostOfStay(int cost) {
		// TODO Auto-generated method stub
		this.costOfStay = cost;
	}
	@Override
	public Integer getCostOfStay() {
		// TODO Auto-generated method stub
		return this.costOfStay;
	}
}
