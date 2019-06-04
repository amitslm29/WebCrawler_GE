package com.ge.webcrawler.job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import com.ge.webcrawler.model.Page;
import com.ge.webcrawler.model.Pages;

public class VisitorCrawler implements Callable<List<String>>{
	private Pages pages;

	public VisitorCrawler(Pages pages) {
		this.pages = pages;
	}

	@Override
	public List<String> call() throws Exception {
		List<String> visitor = new ArrayList<>();
		Set<String> addresses = new HashSet<String>();
		for(Page page: pages.getPages()) {
			addresses.add(page.getAddress());
		}
		
		for(Page page: pages.getPages()) {
			for(String link: page.getLinks()) {
				if(addresses.contains(link)) {
					visitor.add(link);
				}
			}
		}
		return visitor;
	}
}
