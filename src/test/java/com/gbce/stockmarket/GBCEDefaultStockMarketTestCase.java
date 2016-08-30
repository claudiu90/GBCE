package com.gbce.stockmarket;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import stockmarket.interfaces.StockMarket;
import stockmarket.interfaces.Trade;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GBCEDefaultStockMarketTestCase {

	@Test
	public void testDividendYield() {
		double dividentYeld = new GBCEDefaultStockMarket().getDividendYield("TEA", 100);
		
		double expectedDividentYeld = 0.001 / 100;
		
		assertEquals(expectedDividentYeld, dividentYeld, 0.01);
	}

	@Test
	public void testDividendYieldStockTypePreferred() {
		double dividentYeld = new GBCEDefaultStockMarket().getDividendYield("GIN", 30);

		double expectedDividentYeld = 2.0 * 100 / 30;

		assertEquals(expectedDividentYeld, dividentYeld, 0.01);
	}

	@Test(expected = NoSuchStockSymbol.class)
	public void testDividendYieldNoStock() {
		new GBCEDefaultStockMarket().getDividendYield("PleaseDontExist", 100);
	}

	@Test
	public void testPERatio() {
		double peRatio = new GBCEDefaultStockMarket().getPERatio("GIN", 100);

		double expectedPERatio = 100.0 / 8;

		assertEquals(expectedPERatio, peRatio, 0.01);
	}

	@Test(expected = NoSuchStockSymbol.class)
	public void testPERatioNoStock() {
		new GBCEDefaultStockMarket().getPERatio("PleaseDontExist", 100);
	}

	@Test
	public void testCheckSellTrade() {
		StockMarket market = new GBCEDefaultStockMarket();
		Trade sellTrade = market.sellStock("JOE", 10, 100);
		Trade sellTrade2 = market.sellStock("ALE", 3, 50);
		Trade sellTrade3 = market.sellStock("JOE", 1, 80);

		Trade lastJoeTrade = market.getLastNTransactions("JOE", 1).get(0);
		assertThat(lastJoeTrade, not(sellTrade));
		assertThat(lastJoeTrade, not(sellTrade2));
		assertThat(lastJoeTrade, is(sellTrade3));
	}
	
	@Test
	public void testCheckSellTradeZeroAmountAndPrice() {
		StockMarket market = new GBCEDefaultStockMarket();
		Trade sellTrade = market.sellStock("JOE", 0, 80);
		Trade sellTrade1 = market.sellStock("JOE", 10, 0);
		
		Trade nullTrade = null; 
		
		assertThat(sellTrade, is(nullTrade));
		assertThat(sellTrade1, is(nullTrade));
	}

	@Test
	public void testCheckBuyTrade() {
		StockMarket market = new GBCEDefaultStockMarket();
		Trade sellTrade = market.buyStock("ALE", 10, 100);
		Trade sellTrade2 = market.buyStock("JOE", 3, 50);
		Trade sellTrade3 = market.buyStock("ALE", 1, 80);

		Trade lastAleTrade = market.getLastNTransactions("ALE", 1).get(0);
		assertThat(lastAleTrade, not(sellTrade));
		assertThat(lastAleTrade, not(sellTrade2));
		assertThat(lastAleTrade, is(sellTrade3));
	}
	
	@Test
	public void testCheckBuyTradeZeroAmountAndPrice() {
		StockMarket market = new GBCEDefaultStockMarket();
		Trade buyTrade = market.sellStock("JOE", 0, 80);
		Trade buyTrade1 = market.sellStock("JOE", 10, 0);
		
		Trade nullTrade = null; 
		
		assertThat(buyTrade, is(nullTrade));
		assertThat(buyTrade1, is(nullTrade));
	}

	@Test
	public void testVolumeWeightedPrice() throws InterruptedException {
		StockMarket market = new GBCEDefaultStockMarket();
		// this transaction must be ignored
		market.buyStock("POP", 30, 3);

		Thread.sleep(2_000);
		market.buyStock("POP", 20, 3);
		market.sellStock("POP", 20, 10);
		double vwsp = market.getVolumeWeightedStockPrice("POP", 2000);

		assertEquals((20.0 * 3 + 20 * 10) / 40, vwsp, 0.001);
	}

	@Test
	public void testAllShareIndex() {
		StockMarket market = new GBCEDefaultStockMarket();
		market.buyStock("ALE", 10, 100);
		market.sellStock("JOE", 3, 50);
		market.buyStock("ALE", 2, 80);
		market.sellStock("GIN", 1, 5);
		
		//pow(1160/12 * 50  * 5 , 1/3) ...... 28.9116
		assertEquals(28.9116,market.getGBCEAllShareIndex(),.00001);
		
	}

}
