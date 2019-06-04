package com.ge.webcrawler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.webcrawler.job.FailureCrawler;
import com.ge.webcrawler.job.VisitorCrawler;
import com.ge.webcrawler.model.Pages;
import com.ge.webcrawler.model.Result;

public class Crawler {
	
	public Result crawl(Pages pages) {
		ExecutorService visitor = Executors.newSingleThreadExecutor();
		ExecutorService debugger = Executors.newSingleThreadExecutor();
		Result result =new Result();
		try {
			//Thread for error
			Future<Set<String>> errors = debugger.submit(new FailureCrawler(pages));
			
			//Thread for visited and skipped
			Future<List<String>> futures = visitor.submit(new VisitorCrawler(pages));
			
			//Get error result
			result.setErrors(errors.get());
			
			List<String> visits = futures.get();
			
			for(String visit: visits) {
				if(result.getSuccesses().contains(visit)) {
					result.getSkips().add(visit);
				}else {
					result.getSuccesses().add(visit);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			visitor.shutdown();
			debugger.shutdown();
		}
		return result;
	}
	
	public Result crawl(File file) throws JsonParseException, JsonMappingException, IOException { 
		Pages pages = new ObjectMapper().readValue(file, Pages.class);
		return crawl(pages);
	}
	
}
