/**
 * Made with <3 by e3ndr.
 * 
 * Licensed under MIT, do as you please.
 */
package cf.e3ndr.UltimateLib.Util;

import java.util.ArrayList;

public class TextUtil {
	public static boolean containsExcessChars(char[] allowedCharacters, String toCheck) {
		String n = toCheck;
		for (char c : allowedCharacters) n = n.replace(String.valueOf(c), "");
		if (n.length() > 0) return true;
		return false;
	}
	
	private static final String seperator = "\", \"";
	public static <E> String arrayToString(E[] arr) {
		String str = "[\"";
		
		for (E obj : arr) str += (String.valueOf(obj)
				.replace("\b", "\\b")
				.replace("\t", "\\t")
				.replace("\n", "\\n")
				.replace("\f", "\\f")
				.replace("\r", "\\r")
				.replace("\'", "\\\'")
				.replace("\"", "\\\"")
				.replace(",", "\\,")
				.replace("[", "\\[")
				.replace("]", "\\]"));
		
		return (str += "]").replace(seperator + "]", "\"]");
	}
	
	public static String[] stringToArray(String arr) {
		ArrayList<String> ret = new ArrayList<>();
		String array = arr.substring(2).substring(0, arr.length() - 3);
		String[] ar = array.split(seperator);
		
		for (String s : ar) ret.add(s
				.replace("\\b", "\b")
				.replace("\\t", "\t")
				.replace("\\n", "\n")
				.replace("\\f", "\f")
				.replace("\\r", "\r")
				.replace("\\\'", "\'")
				.replace("\\\"", "\"")
				.replace("\\,", ",")
				.replace("\\[", "[")
				.replace("\\]", "]"));
		
		return ret.toArray(new String[0]);
	}

}
