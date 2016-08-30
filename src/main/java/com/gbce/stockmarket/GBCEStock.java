package com.gbce.stockmarket;

import stockmarket.interfaces.Stock;

public class GBCEStock implements Stock{

	String stockSymbol;
	STOCK_TYPE stockType;
	double lastDividend;
	double fixedDividend;
	double parValue;

	public GBCEStock(String stockSymbol, STOCK_TYPE stockType, double lastDividend, double fixedDividend,
			double parValue) {
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	@Override
	public STOCK_TYPE getStockType() {
		return stockType;
	}

	@Override
	public void setStockType(STOCK_TYPE stockType) {
		this.stockType = stockType;
	}

	@Override
	public String getStockSymbol() {
		return stockSymbol;
	}

	@Override
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	@Override
	public double getLastDividend() {
		return lastDividend;
	}

	@Override
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	@Override
	public double getFixedDividend() {
		return fixedDividend;
	}

	@Override
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	@Override
	public double getParValue() {
		return parValue;
	}

	@Override
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
}
