package com.bridgelabz.hotelReservationSystem;

public class RidgeWood extends Hotel
{
	private final String HOTEL_NAME = "RidgeWood";
	private final int RATING = 5;
	private final int WEEKDAY_REGULAR_PRICE = 220;
	private final int WEEKDAY_REWARD_PRICE = 100;
	private final int WEEKEND_REGULAR_PRICE = 150;
	private final int WEEKEND_REWARD_PRICE = 40;
	public int costOfStay = 0;
	
	@Override
	public Integer getRating() {
		// TODO Auto-generated method stub
		return RATING;
	}
	@Override
	public Integer getWeekDayRegularPrice() {
		// TODO Auto-generated method stub
		return WEEKDAY_REGULAR_PRICE;
	}
	@Override
	public Integer getWeekDayRewardPrice() {
		// TODO Auto-generated method stub
		return WEEKDAY_REWARD_PRICE;
	}
	@Override
	public Integer getWeekEndRegularPrice() {
		// TODO Auto-generated method stub
		return WEEKEND_REGULAR_PRICE;
	}
	@Override
	public Integer getWeeKEndRewardPrice() {
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
		this.costOfStay = cost;
	}
	@Override
	public Integer getCostOfStay() {
		return this.costOfStay;
	}
}
