package com.ubs.stringCalculator;

/**
 * 
 * @author Suresh
 *
 */
public class InvalidArgumentException extends RuntimeException {
	public InvalidArgumentException(String exceptionMessage){
		super(exceptionMessage);
	}
}
