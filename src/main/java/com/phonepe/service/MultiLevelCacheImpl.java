package main.java.com.phonepe.service;

import java.util.ArrayList;
import java.util.List;

import main.java.com.phonepe.pojos.Level;

public class MultiLevelCacheImpl implements MultiLevelCache {
	private List<Level> levels;
	private int maxCapacityOfLevels;
	private EvictionStrategy evictionStrategy;

	public MultiLevelCacheImpl(int maxCapacityOfLevels, EvictionStrategy evictionStrategy) {
		super();
		this.levels = new ArrayList<>();
		this.maxCapacityOfLevels = maxCapacityOfLevels;
		this.evictionStrategy = evictionStrategy;
	}

	@Override
	public String read(String key) {

		for (Level level : levels) {
			if (level.containsKey(key)) {
				String value = level.get(key);
				write(key, value);
				return value;
			}
		}
		return null;
	}

	@Override
	public boolean write(String key, String value) {
		return write(key, value, 0);

	}

	private boolean write(String key, String value, int index) {
		if (index == maxCapacityOfLevels) { // if the max number of levels is reached.
			return false;
		}

		if (index >= levels.size()) { // if there are levels available. But previous levels were full.
			Level level = new Level();
			level.put(key, value);
			levels.add(level);
			return true;
		}

		Level level = levels.get(index);
		if (level.containsKey(key)) {
			level.put(key, value);
			return true;
		}
		if (level.isCacheFull()) { // if this cache level is full evict the key value pair and update it in the
									// next levels.
			String keyEvicted = evictionStrategy.evict(level);
			String valueEvicted = level.get(keyEvicted);

			level.remove(keyEvicted);

			level.put(key, value);
			return write(keyEvicted, valueEvicted, index + 1);

		} else {
			level.put(key, value);
			return true;
		}

	}

	@Override
	public void delete(String key) {

		for (Level level : levels) {
			if (level.containsKey(key)) {
				level.remove(key);
			}
		}
	}

	@Override
	public boolean isFull() {
		return levels.size() == maxCapacityOfLevels;
	}

	@Override
	public String toString() {
		return "MultiLevelCacheImpl [levels=" + levels + ", maxCapacityOfLevels=" + maxCapacityOfLevels
				+ ", evictionStrategy=" + evictionStrategy + "]";
	}

}
