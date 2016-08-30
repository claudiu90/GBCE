package stockmarket.interfaces;

import java.util.List;
import java.util.Set;

/**
 * A representation of the StockMarket
 * @author clconsta
 *
 */
public interface StockMarket {
	/**
	 * 
	 * @param price
	 *            Value of the Stock in pennies
	 * @param stock
	 *            Stock Symbol
	 * @return the dividend yield in pennies
	 */
	public double getDividendYield(String stock, double price);

	/**
	 * 
	 * @param price
	 *            Value of the Stock in pennies
	 * @param stock
	 *            Stock Symbol
	 * @return the P/E ratio
	 */
	public double getPERatio(String stock, double price);

	/**
	 * 
	 * @param n
	 *            number of last N trades
	 * @param stock
	 *            Stock Symbol
	 * @return a {@link List} of last N {@link Trade}
	 */
	public List<? extends Trade> getLastNTransactions(String stock, int n);

	/**
	 * 
	 * @param timePeriod
	 *            time in milliseconds to process
	 * @param stock
	 *            Stock Symbol
	 * @return volume weighted stock price
	 */
	public double getVolumeWeightedStockPrice(String stock, long timePeriod);

	/**
	 * 
	 * @param stock
	 *            Stock Symbol
	 * @param amount
	 *            the number of items to buy
	 * @param price
	 *            the value of one item
	 * @return an instance of {@link Trade} representing a market trade
	 */
	public Trade buyStock(String stock, int amount, double price);

	/**
	 * 
	 * @param stock
	 *            Stock Symbol
	 * @param amount
	 *            the number of items to sell
	 * @param price
	 *            the value of one item
	 * @return an instance of {@link Trade} representing a market trade
	 */
	public Trade sellStock(String stock, int amount, double price);

	/**
	 * 
	 * @return the All Share Index of this market
	 */
	public double getGBCEAllShareIndex();
	
	/**
	 * 
	 * @return a Set of Strings representing the symbols of all the available stocks
	 */
	public Set<String> getAvailableStocks();

}