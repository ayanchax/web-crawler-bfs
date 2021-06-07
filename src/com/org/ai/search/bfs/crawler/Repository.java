package com.org.ai.search.bfs.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Repository {
	private Queue<String> bfsQueue = new LinkedList<String>();
	private Set<String> urlVisited = new HashSet<String>();
	private int urlNo;

	public Repository() {
		this.bfsQueue = new LinkedList<String>();
		this.urlVisited = new HashSet<String>();
		this.setUrlNo(0);
	}

	public void addURLToQueue(String url) {
		this.bfsQueue.add(url);
	}

	public String removeURLFromQueue() {
		return bfsQueue.poll();
	}

	public void addURLToSet(String url) {
		this.urlVisited.add(url);
		this.setUrlNo(this.getUrlNo() + 1);
	}

	public int getUrlNo() {
		return urlNo;
	}

	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}

	public boolean isQueueEmpty() {
		return this.bfsQueue.isEmpty();
	}

	public boolean setHasURL(String url) {
		return this.urlVisited.contains(url);
	}

	public Set<String> getUrlVisited() {
		return this.urlVisited;
	}

}
