package com.puresound.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class QuickSort {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("str1", "str2", "str3");
		
		
		List<String> finalList = names.stream().filter(a -> a.length() > 2)
					.limit(2)
					.collect(toList());
		
		finalList.forEach(System.out::println);
					
	}
}
