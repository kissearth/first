package com.ylkj.xxb.util.concurrent;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentExecutor {

	private static final int DEFAULT_POOL_SIZE = 100;
	private static final int DEFAULT_GROUP_SIZE = 3;

	private int poolSize = DEFAULT_POOL_SIZE;
	private int groupSize = DEFAULT_GROUP_SIZE;
	
	private ForkJoinPool fjp;

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
	
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public void initialize() {
		fjp = new ForkJoinPool(poolSize);
	}

	public <T> void each(Collection<T> collection, Callback<T> callback) {
		fjp.invoke(new Task<T>(collection, callback, groupSize));
	}

}
