package com.bridgelabz.hotelReservationSystem;

public abstract class Hotel
{
	/**Returns rating of a Hotel in integer.
	 * @return
	 */
	public abstract Integer getRating();
	/**Returns week day price of a hotel for normal customers.
	 * @return
	 */
	public abstract Integer getWeekDayRegularPrice();
	/**Returns week day prices of a hotel for special customers.
	 * @return
	 */
	public abstract Integer getWeekDayRewardPrice();
	/**Returns week end price for a normal customer.
	 * @return
	 */
	public abstract Integer getWeekEndRegularPrice();
	/**Returns week end prices for special customer.
	 * @return
	 */
	public abstract Integer getWeeKEndRewardPrice();
	/**sets cost of stay for hotel object
	 * @return
	 */
	public abstract void setCostOfStay(int cost);
	/**Returns the cost of stay
	 * @return
	 */
	public abstract Integer getCostOfStay();
	/**Returns Hotel name.
	 * @return
	 */
	public abstract String getHotelName();
}
