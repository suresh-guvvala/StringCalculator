package com.ubs.stringCalculator;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * @author Suresh
 *
 */
public class StringCalculator {
	public static String NEWLINE = "\n";
	public static String DELIMITER_START = "//";

	/**
	 * Performs addition of numbers passed in the argument. Uses "," and "\n"
	 * as default delimiters, if delimiter is not mentioned at the beginning.
	 * 
	 * Assumed that the delimiter will be mentioned as "//DELIMITER\n".
	 * 
	 * @param numbers
	 * @return sum of numbers
	 */
	public static int add(String numbers) {
		if (StringUtils.isEmpty(numbers))
			return 0;

		// Identify the delimiter, if passed.
		String delimiter = getDelimiter(numbers);

		// Strip off delimiter, so that only numbers to be added will be left over
		if (!StringUtils.isEmpty(delimiter))
			numbers = StringUtils.substringAfter(numbers, DELIMITER_START + delimiter + NEWLINE);

		// escape any special characters in delimiter
		delimiter = escapeSpecialChars(delimiter);

		// If delimiter is not mentioned, use the default one.
		if (StringUtils.isEmpty(delimiter))
			delimiter = ",|\n";

		if (validateNumberArguments(numbers, delimiter))
			return add(numbers, delimiter);
		else 
			return 0;
	}

	/**
	 * Identify the delimiter specified at the begining, if specified.
	 * @param numbers 
	 * @return Delimiter to be used, if mentioned. Otherwise, returns null.
	 */
	private static String getDelimiter(String numbers) {
		if (StringUtils.startsWith(numbers, DELIMITER_START)) {
			return StringUtils.substringBetween(numbers, DELIMITER_START, NEWLINE);
		}

		return null;
	}

	/**
	 * Escape special characters used in String Regular expressions, if any 
	 * exists in the given delimiter.
	 * 
	 * @param delimiter
	 * @return escaped string.
	 */
	private static String escapeSpecialChars(String delimiter) {
		if (!StringUtils.isEmpty(delimiter))
			delimiter = Stream.of(delimiter.split("\\|"))
				.map (elem -> Pattern.quote(new String(elem)))
				.collect(Collectors.joining("|"));

		return delimiter;
	}

	/**
	 * Validates the numbers, by splitting the given string using the given delimiter.
	 * Returns true if validation is successful, otherwise throws InvalidArgumentsException.
	 * 
	 * As of now, only validation applied is checking for negative numbers.
	 * Can be extended for any other validations.
	 * 
	 * @param numbers string with numbers
	 * @param delimiter delimiter to be used
	 * @return true if successful, otherwise throws exception
	 */
	private static boolean validateNumberArguments(String numbers, String delimiter) {
		String negativeNumbers = Stream.of(numbers.split(delimiter))
		.map(elem -> NumberUtils.toInt(elem.trim(), 0))
		.filter(elem -> elem < 0)
		.map(elem -> elem.toString())
		.collect(Collectors.joining(","));

		if (!StringUtils.isEmpty(negativeNumbers))
			throw new InvalidArgumentException("negatives not allowed: " + negativeNumbers);

		return true;
	}

	/**
	 * Returns sum of given numbers, excluding the ones > 1000. 
	 * 
	 * @param numbers
	 * @param delimiter
	 * @return
	 */
	private static int add(String numbers, String delimiter) {
		if (StringUtils.isEmpty(numbers)) 
			return 0;

		return Stream.of(numbers.split(delimiter))
		.map(elem -> NumberUtils.toInt(elem.trim(), 0))
		.filter(elem -> elem > 0 && elem <= 1000)
		.collect(Collectors.summingInt(elem -> elem.intValue()));
	}
}
