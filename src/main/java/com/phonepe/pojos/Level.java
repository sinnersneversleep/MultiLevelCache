package main.java.com.phonepe.pojos;

import java.util.HashMap;
import java.util.Map;

public class Level {

	private static final int defaultCapacity = 1;
	private int maxCapacity;
	private Map<String, String> cache;
	private Map<String, Integer> keyFrequencyMap;

	public Level(int max_capacity) {
		super();
		this.maxCapacity = max_capacity;
		this.cache = new HashMap<>();
		this.keyFrequencyMap = new HashMap<>();
	}

	public Level() {

		this.maxCapacity = defaultCapacity;
		this.cache = new HashMap<>();
		this.keyFrequencyMap = new HashMap<>();
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public Map<String, Integer> getKeyFrequencyMap() {
		return keyFrequencyMap;
	}

	public boolean isCacheFull() {
		return this.cache.size() == maxCapacity;
	}

	public void remove(String key) {
		cache.remove(key);
		keyFrequencyMap.remove(key);

	}

	@Override
	public String toString() {
		return "Level [maxCapacity=" + maxCapacity + ", cache=" + cache + ", keyFrequencyMap=" + keyFrequencyMap + "]";
	}

	public String get(String key) {
		updateKeyFrequencyMap(key);
		return cache.get(key);
	}

	private void updateKeyFrequencyMap(String key) {
		if (!keyFrequencyMap.containsKey(key)) {
			keyFrequencyMap.put(key, 0);
		}
		keyFrequencyMap.put(key, keyFrequencyMap.get(key) + 1);
	}

	public boolean containsKey(String key) {
		return cache.containsKey(key);
	}

	public void put(String key, String value) {
		updateKeyFrequencyMap(key);
		cache.put(key, value);

	}

}
