package com.ylkj.xxb.util.concurrent;

@FunctionalInterface
public interface Callback<T> {

	void execute(T object);
	
}
