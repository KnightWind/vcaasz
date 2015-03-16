package com.bizconf.vcaasz.task.support;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 固定size的阻塞队列 (线程安全)
 * 
 * @author Chris Gao
 * @param <E>
 */
public class FixedBlockingQueue<E> extends AbstractQueue<E> implements
		BlockingQueue<E> {

	public int drainTo(Collection<? super E> c, int maxElements) {
		return queue.drainTo(c, maxElements);
	}

	public int drainTo(Collection<? super E> c) {
		return queue.drainTo(c);
	}

	public boolean offer(E o, long timeout, TimeUnit unit)
			throws InterruptedException {
		return queue.offer(o, timeout, unit);
	}

	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		return queue.poll(timeout, unit);
	}

	public void put(E o) throws InterruptedException {
		queue.put(o);
	}

	public int remainingCapacity() {
		return queue.remainingCapacity();
	}

	public E take() throws InterruptedException {
		return queue.take();
	}

	private BlockingQueue<E> queue;

	/**
	 * 构造函数，固定大小的阻塞队列实现
	 */
	public FixedBlockingQueue(int size) {
		queue = new java.util.concurrent.LinkedBlockingQueue<E>(size);
	}

	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}

	@Override
	public int size() {
		return queue.size();
	}

	public boolean offer(E o) {
		try {
			queue.put(o);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public E peek() {
		return queue.peek();
	}

	public E poll() {
		return queue.poll();
	}

}