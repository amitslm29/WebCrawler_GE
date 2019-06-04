package com.ge.webcrawler.job;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

import com.ge.webcrawler.model.Page;
import com.ge.webcrawler.model.Pages;

public class FailureCrawler implements Callable<Set<String>>{
	private Pages pages;

	public FailureCrawler(Pages pages) {
		this.pages = pages;
	}
	
	public Set<String> call() throws Exception {
		Set<String> errors =new HashSet<String>();
		Set<String> addresses = new HashSet<String>();
		for(Page page: pages.getPages()) {
			addresses.add(page.getAddress());
		}
		
		for(Page page: pages.getPages()) {
			for(String link: page.getLinks()) {
				if(!addresses.contains(link)) {
					errors.add(link);
				}
			}
		}
		return errors;
	}
}
