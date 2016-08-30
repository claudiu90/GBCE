package stockmarket.interfaces;

import java.sql.Timestamp;

/**
 * A representation of a Trade. <br>
 * Trade is the result of buying or selling a {@link Stock}
 * @author clconsta
 *
 */
public interface Trade {
	/**
	 * 
	 * a buying or selling trade type
	 */
	enum TradeType{
		/**
		 * a buying trade
		 */
		BUY, 
		/**
		 * a selling trade
		 */
		SELL
	}
	/**
	 * 
	 * @return the time stamp of this trade
	 */
	public Timestamp getTimeStamp();
	
	/**
	 * 
	 * @return the amount of stocks bought/sold in this trade
	 */
	public int getQuantity();
	
	/**
	 * 
	 * @return the total price of this trade
	 */
	public double getPrice();
	
	/**
	 * 
	 * @return returns {@link TradeType.BUY} or {@link TradeType.SELL} representing the nature of this trade
	 */
	public TradeType getTradeType();
	
	/**
	 * 
	 * @return returns the {@link Stock} of this trade
	 */
	public Stock getStock();
}
