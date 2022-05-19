package com.caocity.baraka.assignment.model;

import java.io.Serializable;

public class Candle implements Serializable{


	private static final long serialVersionUID = 1L;

	private long time;
	private double open;
	private double high;
	private double low;
	private double close;
	private String symbol;
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Candle(Stock stock,long period) {
		super();
		this.time = period;
		this.open = stock.getP();
		this.high = stock.getP();
		this.low = stock.getP();
		this.close = stock.getP();
		this.symbol = stock.getS();
	}

}
