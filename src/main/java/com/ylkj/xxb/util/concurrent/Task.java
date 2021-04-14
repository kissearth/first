package com.ylkj.xxb.util.concurrent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class Task<T> extends RecursiveTask<T> {

	private static final long serialVersionUID = 1L;
	
	private int groupSize;

	private Collection<T> collection;
	private Callback<T> callback;

	public Task(Collection<T> collection, Callback<T> callback, int groupSize) {
		this.collection = collection;
		this.callback = callback;
		this.groupSize = groupSize;
	}

	@Override
	protected T compute() {
		if (collection.size() <= groupSize) {
			for (T item : collection) {
				callback.execute(item);
			}
		} else {
			Set<T> collection1 = new HashSet<>();
			Set<T> collection2 = new HashSet<>();
			int index = 1;
			for (T item : collection) {
				if ((index & 1) == 1) {
					collection1.add(item);
				} else {
					collection2.add(item);
				}
				index++;
			}
			Task<T> task1 = new Task<T>(collection1, callback, groupSize);
			Task<T> task2 = new Task<T>(collection2, callback, groupSize);
			invokeAll(task1, task2);
			task1.join();
			task2.join();
		}
		return null;
	}

}
