/**
 * 
 */
package com.otrs.restaurant.utils;

/**
 * @author Kundan
 *
 */
public enum SaveStatus {
	SUCCESS("Saved Successfully"),
	FAILED("Data could not be saved"),
	DUPLICATE("Duplicates are not allowed");
	
	private String statusText;
	
	SaveStatus(String statusText){
		this.statusText = statusText;
	}
	
	public String statusText() {
		return statusText;
	}
}
