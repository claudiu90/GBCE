package com.gbce.stockmarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

import stockmarket.interfaces.Trade;

public class GBCEMarketDemo {

	public static void main(String[] args) {
		GBCEDefaultStockMarket market = new GBCEDefaultStockMarket();
		
		for(String stockSymbol : market.getAvailableStocks()){
			double price = round(Math.random()*1000+1,2);
			int amount = (int)(Math.random()*100+1);
			
			double dividendYeld = market.getDividendYield(stockSymbol, price);
			double peRatio = market.getPERatio(stockSymbol, price);
			market.buyStock(stockSymbol, 1*amount, price);
			market.buyStock(stockSymbol, 2*amount, price);
			Trade trade3 = market.buyStock(stockSymbol, 3*amount, price);
			double vwsp = market.getVolumeWeightedStockPrice(stockSymbol, 5*60*1000);
			
			System.out.println("Stock: " + stockSymbol);
			System.out.println("Price: "+ price);
			System.out.println("Divident Yeld: "+ dividendYeld);
			System.out.println("PE Ratio: "+ peRatio);
			System.out.println("Last trade:\n"+trade3);
			System.out.println("Volume Weighted stock price: "+vwsp);
			System.out.println("");
		}
		
		double allShareIndex = market.getGBCEAllShareIndex();
		System.out.println("\nGBCE All Share Index is "+allShareIndex);

	}
	
	public static double round(double value, int places) {
		return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
	}

}
