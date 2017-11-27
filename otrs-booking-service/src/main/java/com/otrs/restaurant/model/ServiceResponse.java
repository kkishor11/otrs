package com.otrs.restaurant.model;

public class ServiceResponse {

	private String reponse;
	private String responseCode;

	public ServiceResponse(String reponse, String responseCode) {
		this.reponse = reponse;
		this.responseCode = responseCode;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceResponse [reponse=");
		builder.append(reponse);
		builder.append(", responseCode=");
		builder.append(responseCode);
		builder.append("]");
		return builder.toString();
	}

}
