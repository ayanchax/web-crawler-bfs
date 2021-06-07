package com.org.ai.search.bfs.crawler;

import java.util.*;
import java.util.regex.*;
import java.net.*;
import java.io.*;

public class WebCrawler {

	public static void main(String[] args) {

		String firstWebsite = "https://www.udemy.com/";
		String patterns = "http[s]*://(\\w+\\.)*(\\w+)";

		BFS myCrawler = new BFS(firstWebsite, 26, patterns);

		try {
			Set<String> urlsCrawled = myCrawler.bfs();
			System.out.println(urlsCrawled.size() + " web sites crawled!");
			System.out.println("Here is the list: ");
			for (String s : urlsCrawled) {
				System.out.println(s);

			}
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}

	}
}
