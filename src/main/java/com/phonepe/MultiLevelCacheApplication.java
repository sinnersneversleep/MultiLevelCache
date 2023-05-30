package main.java.com.phonepe;

import main.java.com.phonepe.service.EvictionStrategy;
import main.java.com.phonepe.service.LeastFrequentlyUsedEvictionStrategy;
import main.java.com.phonepe.service.MultiLevelCache;
import main.java.com.phonepe.service.MultiLevelCacheImpl;

public class MultiLevelCacheApplication {

	public static void main(String[] args) {
		EvictionStrategy evictionStrategy = new LeastFrequentlyUsedEvictionStrategy();
		MultiLevelCache cache = new MultiLevelCacheImpl(3, evictionStrategy);

		System.out.println(cache);
		cache.write("one", "sdflkj");
		System.out.println(cache);
		cache.write("two", "sdflkj");
		System.out.println(cache);
		cache.write("three", "dfk");
		System.out.println(cache);
		cache.write("four", "dfk");
		System.out.println(cache);

	}

}
