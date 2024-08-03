package edu.tus.wizworld.errors;

public enum ErrorMessages {
	EMPTY_FIELDS("One or more empty fields"),
	ALREADY_EXISTS("Title already exists"),
	ONLINE_PRICE_HIGH("Online price cannot exceed retail price"),
	BOOK_NOT_FOUND("No book found");
	
	private String errorMessage;
	
	ErrorMessages(String errMsg){
		this.errorMessage=errMsg;
	}
	
	public String getMsg(){
		return errorMessage;
	}
}
