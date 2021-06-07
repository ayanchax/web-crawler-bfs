package com.org.ai.search.bfs.crawler;

import java.util.*;
import java.util.regex.*;

import java.net.*;
import java.io.*;

public class BFS {

	private String firstURL;
	private int maximumURLNumbers;
	private String urlPatterns;

	private Repository repository = null;

	public BFS(String firstURL, int maximumURLNumbers, String urlPatterns) {
		this.firstURL = firstURL;
		this.maximumURLNumbers = maximumURLNumbers;
		this.urlPatterns = urlPatterns;
		this.repository = new Repository();
	}

	public Set<String> bfs() throws IOException {
		this.repository.addURLToQueue(this.firstURL);

		while (!this.repository.isQueueEmpty()) {
			String currentURL = this.repository.removeURLFromQueue();

			URL url = null;
			BufferedReader br = null;

			boolean correctURLFound = false;
			while (!correctURLFound) {
				try {
					url = new URL(currentURL);
					br = new BufferedReader(new InputStreamReader(url.openStream()));
					correctURLFound = true;
				} catch (MalformedURLException e) {
					System.out.println("Error in URL: " + currentURL);
					currentURL = this.repository.removeURLFromQueue();
					correctURLFound = false;
				} catch (IOException e) {
					System.out.println("Error in URL: " + currentURL);
					currentURL = this.repository.removeURLFromQueue();
					correctURLFound = false;
				}
			}

			StringBuilder sb = new StringBuilder();

			while ((currentURL = br.readLine()) != null) {
				sb.append(currentURL);
			}

			currentURL = sb.toString();
			Pattern pattern = Pattern.compile(this.urlPatterns);
			Matcher matcher = pattern.matcher(currentURL);

			while (matcher.find()) {
				String urlString = matcher.group();

				if (!this.repository.setHasURL(urlString)) {
					this.repository.addURLToSet(urlString);
					System.out.println("URL crawled just now: " + urlString);
					this.repository.addURLToQueue(urlString);
					if (this.repository.getUrlNo() > this.maximumURLNumbers) {
						return this.repository.getUrlVisited();
					}

				}
			}
		}

		return this.repository.getUrlVisited();
	}
}
