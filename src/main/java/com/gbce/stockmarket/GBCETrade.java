package com.gbce.stockmarket;

import java.sql.Timestamp;
import java.util.Date;

import stockmarket.interfaces.Stock;
import stockmarket.interfaces.Trade;

public class GBCETrade implements Trade{
	private Stock stock;
	private TradeType tradeType;
	private int quantity;
	private double price;
	private Timestamp timestamp;
	public GBCETrade(Stock stock, TradeType tradeType, int quantity, double price){
		this.stock=stock;
		this.tradeType=tradeType;
		this.quantity=quantity;
		this.price=price;
		this.timestamp = new Timestamp(new Date().getTime());
	}

	@Override
	public Timestamp getTimeStamp() {
		return timestamp;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public TradeType getTradeType() {
		return tradeType;
	}

	@Override
	public Stock getStock() {
		return stock;
	}
	
	@Override
	public String toString(){
		StringBuilder tradeDetails = new StringBuilder();
		
		tradeDetails.append(stock.getStockSymbol()).append(" ").append(tradeType).append(" Trade").append("\n");
		tradeDetails.append("Transaction price: ").append(price).append("\n");
		tradeDetails.append("Ammount traded: ").append(quantity).append("\n");
		tradeDetails.append("Trade time stamp: ").append(timestamp);
		
		return tradeDetails.toString();
	}
	
}
