package com.gbce.stockmarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import stockmarket.interfaces.Stock;
import stockmarket.interfaces.StockMarket;
import stockmarket.interfaces.Trade;

public class GBCEDefaultStockMarket implements StockMarket {
	private Map<Stock, LinkedList<Trade>> trades = new HashMap<>();
	private Map<String, Stock> availableStocks = new HashMap<>();

	public GBCEDefaultStockMarket() {
		loadStockDummyData();
	}

	private void loadStockDummyData() {
		loadStock(new GBCEStock("TEA", Stock.STOCK_TYPE.COMMON, 0, 0, 100));
		loadStock(new GBCEStock("POP", Stock.STOCK_TYPE.COMMON, 8, 0, 100));
		loadStock(new GBCEStock("ALE", Stock.STOCK_TYPE.COMMON, 23, 0, 60));
		loadStock(new GBCEStock("GIN", Stock.STOCK_TYPE.PREFERRED, 8, 2, 100));
		loadStock(new GBCEStock("JOE", Stock.STOCK_TYPE.COMMON, 13, 0, 250));
	}

	@Override
	public double getDividendYield(String stockSymbol, double price) {
		Stock stock = getStockFromSymbol(stockSymbol);

		double result;
		if (stock.getStockType() == Stock.STOCK_TYPE.COMMON) {
			result = stock.getLastDividend() / price;
		} else {
			result = stock.getFixedDividend() * stock.getParValue() / price;
		}

		return result;
	}

	@Override
	public double getPERatio(String stockSymbol, double price) {
		Stock stock = getStockFromSymbol(stockSymbol);

		return price / stock.getLastDividend();
	}

	@Override
	public List<? extends Trade> getLastNTransactions(String stockSymbol, int n) {
		Stock stock = getStockFromSymbol(stockSymbol);

		List<Trade> lastNTrades = new ArrayList<>();
		Iterator<Trade> lastNtransactionsIterator = trades.get(stock).descendingIterator();
		for (int i = 0; i < n && lastNtransactionsIterator.hasNext(); i++) {
			lastNTrades.add(lastNtransactionsIterator.next());
		}

		return lastNTrades;
	}

	@Override
	public double getVolumeWeightedStockPrice(String stockSymbol, long timePeriod) {
		LinkedList<Trade> stockSpecificTrades = trades.get(getStockFromSymbol(stockSymbol));
		Date now = new Date();
		double nominator = 0;
		double denominator = 0;

		Iterator<Trade> iterator = stockSpecificTrades.descendingIterator();
		while (iterator.hasNext()) {
			Trade trade = iterator.next();
			if (timePeriod != 0 && trade.getTimeStamp().before(new Date(now.getTime() - timePeriod))) {
				break;
			}
			nominator += trade.getPrice();
			denominator += trade.getQuantity();
		}
		return nominator / denominator;
	}

	@Override
	public Trade buyStock(String stockSymbol, int amount, double price) {
		if (amount == 0 || price == 0)
			return null;
		// recording the trade
		Stock stock = getStockFromSymbol(stockSymbol);

		Trade sellTrade = new GBCETrade(stock, Trade.TradeType.BUY, amount, amount * price);
		trades.get(stock).add(sellTrade);
		return sellTrade;
	}

	@Override
	public Trade sellStock(String stockSymbol, int amount, double price) {
		if (amount == 0 || price == 0)
			return null;
		// recording the trade
		Stock stock = getStockFromSymbol(stockSymbol);

		Trade sellTrade = new GBCETrade(stock, Trade.TradeType.SELL, amount, amount * price);
		trades.get(stock).add(sellTrade);
		return sellTrade;
	}

	private Stock getStockFromSymbol(String stockSymbol) {
		Stock stock = availableStocks.get(stockSymbol);
		if (stock == null) {
			throw new NoSuchStockSymbol("Stock Symbol Not Found");
		}

		return stock;
	}

	public void loadStock(Stock stock) {
		availableStocks.put(stock.getStockSymbol(), stock);
		trades.put(stock, new LinkedList<Trade>());
	}

	@Override
	public double getGBCEAllShareIndex() {
		double product = 1.0;
		int count = 0;
		for (Map.Entry<Stock, LinkedList<Trade>> tradeEntry : trades.entrySet()) {
			if (tradeEntry.getValue().isEmpty()) {
				continue;
			}
			product *= getVolumeWeightedStockPrice(tradeEntry.getKey().getStockSymbol(), 0);
			count++;
		}
		return Math.pow(product, 1.0 / count);
	}

	@Override
	public Set<String> getAvailableStocks() {
		return availableStocks.keySet();
	}

}
