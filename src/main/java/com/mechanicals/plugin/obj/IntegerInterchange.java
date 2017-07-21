package com.mechanicals.plugin.obj;

public class IntegerInterchange {

	private int max;
	private int current;
	private boolean isMax = false;
	
	public IntegerInterchange(int max, int current) {
		this.max = max;
		this.current = current;
		if (current > max) {
			current = max;
			isMax = true;
		}
	}
	
	public int getMax() {
		return max;
	}
	
	public int getCurrent() {
		return current;
	}
	
	public boolean isMax() {
		return isMax;
	}
	
	public void setToMax() {
		current = max;
	}
	
	public void setMax(int max) {
		this.max = max;
		if (current >= max) {
			current = max;
			isMax = true;
		} else if (current < max) {
			isMax = false;
		}
	}
	
	public void setCurrent(int current) {
		this.current = current;
		if (this.current >= max) {
			this.current = max;
			isMax = true;
		}
	}
	
	public void add(int add) {
		current += add;
		if (current >= max) {
			current = max;
			isMax = true;
		}
	}
}
