package com.puresound.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Stream {
	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList (
				new Dish("pork", false, 100, Dish.Type.MEAT),
				new Dish("beef", false, 200, Dish.Type.MEAT),
				new Dish("chicken", false, 300, Dish.Type.MEAT),
				new Dish("french fries", true, 430, Dish.Type.MEAT),
				new Dish("rice", true, 550, Dish.Type.MEAT),
				new Dish("season fruit", true, 120, Dish.Type.MEAT),
				new Dish("pizza", true, 550, Dish.Type.MEAT),
				new Dish("prawns", false, 300, Dish.Type.MEAT),
				new Dish("salmon", false, 250, Dish.Type.MEAT)
		);
		List<String> highCaloricDishes = new ArrayList<>();
		Iterator<Dish> iterator = menu.iterator();
		while(iterator.hasNext()) {
			Dish d = iterator.next();
			if (d.getCalories() > 300) {
				highCaloricDishes.add(d.getName());
			}
		}
		
		
		List<Dish> highCaloricDishes2 = menu.stream()
											.filter(x -> x.getCalories() > 300)
											.collect(toList());
		
		List<Dish> highCaloricDishes3 = menu.stream()
											.dropWhile(x -> x.getCalories() <= 300)
											.collect(toList());
		System.out.println("highCaloricDishes3.........");
		highCaloricDishes3.forEach(System.out::println);
		
	
		
		menu = Arrays.asList (
				new Dish("pork", false, 700, Dish.Type.MEAT),
				new Dish("beef", false, 600, Dish.Type.MEAT),
				new Dish("chicken", false, 700, Dish.Type.MEAT),
				new Dish("french fries", true, 430, Dish.Type.MEAT),
				new Dish("rice", true, 350, Dish.Type.MEAT),
				new Dish("season fruit", true, 220, Dish.Type.MEAT),
				new Dish("pizza", true, 150, Dish.Type.MEAT),
				new Dish("prawns", false, 100, Dish.Type.MEAT),
				new Dish("salmon", false, 150, Dish.Type.MEAT)
		);
		
		List<Dish> highCaloricDishes4 = menu.stream()
				.dropWhile(x -> x.getCalories() <= 300)
				.collect(toList());
		System.out.println("highCaloricDishes4.........");
		highCaloricDishes4.forEach(System.out::println);
		
		List<Integer> numbers = Arrays.asList(10, 11, 12, 13);
		List<Integer> result = numbers.stream().skip(1).collect(toList());
		Optional<Integer> k = numbers.stream().reduce((x, y) -> x > y? 1: 0);
		if(k.isPresent()) {
			System.out.println("K: " + k.get());
		}
	}
}
