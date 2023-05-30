package main.java.com.phonepe.service;

public interface MultiLevelCache {
	public String read(String key);

	public boolean write(String key, String value);

	public void delete(String key);

	public boolean isFull();

}
