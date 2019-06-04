package com.ge.webcrawler;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ge.webcrawler.model.Result;

public class Main {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Crawler crawler = new Crawler();
		Result result = crawler.crawl(new File(Main.class.getClassLoader().getResource("internet.json").getFile()));
		System.out.println("Success:");
		System.out.println(result.getSuccesses());
		System.out.println();
		System.out.println("Skipped:");
		System.out.println(result.getSkips());
		System.out.println();
		System.out.println("Error:");
		System.out.println(result.getErrors());
	}
}
