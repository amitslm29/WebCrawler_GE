package com.ge.webcrawler.model;

import java.util.HashSet;
import java.util.Set;

public class Result {
	private Set<String> successes;
	private Set<String> skips;
	private Set<String> errors;
	
	public Result() {
		this.successes = new HashSet<String>();
		this.skips = new HashSet<String>();
		this.errors = new HashSet<String>();
	}
	public Result(Set<String> successes, Set<String> skips, Set<String> errors) {
		this.successes = successes;
		this.skips = skips;
		this.errors = errors;
	}
	
	public Set<String> getSuccesses() {
		return successes;
	}
	public void setSuccesses(Set<String> successes) {
		this.successes = successes;
	}
	public Set<String> getSkips() {
		return skips;
	}
	public void setSkips(Set<String> skips) {
		this.skips = skips;
	}
	public Set<String> getErrors() {
		return errors;
	}
	public void setErrors(Set<String> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return "Result [successes=" + successes + ", skips=" + skips + ", errors=" + errors + "]";
	}
}
