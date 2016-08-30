package com.gbce.stockmarket;

/**
 * A run time exception triggered by the request of a stock symbol that doesn't exist 
 * @author clconsta
 *
 */
public class NoSuchStockSymbol extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchStockSymbol() {
		super();
	}

	public NoSuchStockSymbol(String s) {
		super(s);
	}

	public NoSuchStockSymbol(String s, Throwable throwable) {
		super(s, throwable);
	}

	public NoSuchStockSymbol(Throwable throwable) {
		super(throwable);
	}

}
