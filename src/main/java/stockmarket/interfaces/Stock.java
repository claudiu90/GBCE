package stockmarket.interfaces;

/**
 * The representation of a Stock.<br>
 * The stock is an item available for buying or selling on a market.<br>
 * Buying or Selling a Stock will result in a {@link Trade}
 * @author clconsta
 *
 */
public interface Stock {
	/**
	 * A common or preferred stock type
	 * @author clconsta
	 *
	 */
	public enum STOCK_TYPE {
		COMMON, PREFERRED
	}

	/**
	 * 
	 * @return COMMON or PREFERRED, instance of {@link STOCK_TYPE}
	 */
	public STOCK_TYPE getStockType();

	/**
	 * 
	 * @param stockType sets COMMON or PREFERRED, instance of {@link STOCK_TYPE}
	 */
	public void setStockType(STOCK_TYPE stockType);

	/**
	 * 
	 * @return a three letter string representation of the stock
	 */
	public String getStockSymbol();

	/**
	 * 
	 * @param stockSymbol sets a three letter string representation of the stock
	 */
	public void setStockSymbol(String stockSymbol);

	/**
	 * 
	 * @return the last dividend of this stock
	 */
	public double getLastDividend();

	/**
	 * 
	 * @param lastDividend sets the last dividend of this stock
	 */
	public void setLastDividend(double lastDividend);

	/**
	 * 
	 * @return a fixed dividend
	 */
	public double getFixedDividend();

	/**
	 * 
	 * @param fixedDividend sets the stock fixed dividend
	 */
	public void setFixedDividend(double fixedDividend);

	/**
	 * 
	 * @return returns the stock par value
	 */
	public double getParValue();

	/**
	 * 
	 * @param parValue sets the stock par value
	 */
	public void setParValue(double parValue);
}