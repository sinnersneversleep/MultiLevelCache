package main.java.com.phonepe.service;

import main.java.com.phonepe.pojos.Level;

public interface EvictionStrategy {
	public String evict(Level level);
}
