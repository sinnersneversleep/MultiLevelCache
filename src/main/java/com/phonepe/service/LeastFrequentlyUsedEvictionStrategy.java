package main.java.com.phonepe.service;

import java.util.Map;

import main.java.com.phonepe.pojos.Level;

public class LeastFrequentlyUsedEvictionStrategy implements EvictionStrategy {

	@Override
	public String evict(Level level) {
		int minimumFrequency = Integer.MAX_VALUE;
		String key = null;
		for (Map.Entry<String, Integer> entry : level.getKeyFrequencyMap().entrySet()) {
			if (entry.getValue() < minimumFrequency) {
				key = entry.getKey();
				minimumFrequency = entry.getValue();
			}
		}
		return key;
	}

}
