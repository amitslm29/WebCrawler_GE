package com.ge.webcrawler;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ge.webcrawler.Crawler;
import com.ge.webcrawler.model.Page;
import com.ge.webcrawler.model.Pages;
import com.ge.webcrawler.model.Result;

public class CrawlerTest {
	
	private Crawler crawler;

	@Before
	public void init() {
		this.crawler =new Crawler();
	}

	@Test
	public void testCrawlerWithJsonFile() throws JsonParseException, JsonMappingException, IOException {
		Result result = crawler.crawl(new File(getClass().getClassLoader().getResource("internet.json").getFile()));
		Assert.assertEquals(12, result.getSuccesses().size());
		Assert.assertEquals(8, result.getSkips().size());
		Assert.assertEquals(5, result.getErrors().size());
	}
	
	
	@Test
	public void testCrawlerWithDefaultPage() {
		Pages pages = getPages();
		Result result = crawler.crawl(pages);
		Assert.assertEquals(12, result.getSuccesses().size());
		Assert.assertEquals(8, result.getSkips().size());
		Assert.assertEquals(5, result.getErrors().size());
	}

	private Pages getPages() {
		Pages pages = new Pages();
		pages.setPages(Arrays.asList(
				new Page("page-01", Arrays.asList("page-02", "page-03")),
				new Page("page-02", Arrays.asList("page-01")),
				new Page("page-03", Arrays.asList("page-01", "page-02", "page-04")),
				new Page("page-04", Arrays.asList("page-01", "page-02", "page-03", "page-05")),
				new Page("page-05", Arrays.asList("page-01", "page-02", "page-06", "page-09")),
				new Page("page-06", Arrays.asList("page-07", "page-08")),
				new Page("page-07", Arrays.asList("page-05", "page-08", "page-09", "page-10")),
				new Page("page-08", Arrays.asList("page-09", "page-00")),
				new Page("page-09", Arrays.asList("page-10", "page-11", "page-12", "page-13", "page-99")),
				new Page("page-96", Arrays.asList("page-01", "page-97")),
				new Page("page-97", Arrays.asList("page-98")),
				new Page("page-98", Arrays.asList("page-99")),
				new Page("page-99", Arrays.asList("page-01", "page-02", "page-03", "page-04"))
				));
		return pages;
	}
}
