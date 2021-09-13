package com.example.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import com.example.Utils.CheckedException;
import com.example.Utils.Either;
import com.example.Utils.Util;

public class ExceptionHandlingTests {

	public Integer transformOrThrowException(Integer i) throws CheckedException {
		// some code
		if (i < 0)
			throw new CheckedException();
		return i * i;
	}
	public Integer transform2(Integer i) throws Exception {
		return i;
	}

	@Test
	public void testExceptionHandlingInStreams() {
		List<Integer> myList = Arrays.asList(1, 2, 3, -4, 5, -6);
		List<Pair<Integer,Exception>> transformedList = myList.stream()
				.map(Util.either(this::transformOrThrowException))
				.filter(either -> either.isLeft())
				.map(either -> (Pair)either.getLeft().get())
				.collect(Collectors.toList());
		
		/*
		 * transformedList.forEach(el -> {
		 * System.out.println("item :"+el.getLeft()+" Exception:"+el.getRight()); });
		 */
		/*
		 * List<Integer> transformedList = myList.stream()
		 * .map(Util.mapOrWrapException(item ->
		 * transformOrThrowException(item))).collect(Collectors.toList());
		 */
		/*
		 * List<Integer> resultList = myList.stream().map(Util.either(item ->
		 * transformOrThrowException(item))) .filter(item -> item.isRight()).map(item ->
		 * (Integer)item.getRight().get()) .collect(Collectors.toList());
		 * List<Pair<CheckedException,Integer>> exceptionsList =
		 * myList.stream().map(Util.either(item -> transformOrThrowException(item)))
		 * .filter(item -> item.isLeft()).map(item ->
		 * (Pair<CheckedException,Integer>)item.getLeft().get())
		 * .collect(Collectors.toList()); resultList.forEach(item ->
		 * System.out.println(item)); exceptionsList.forEach(pair ->
		 * System.out.println(pair.getLeft()+ " --- "+ pair.getRight()));
		 */
	}

	// simple way of handling exception
	public Integer handleException(Integer item) {
		try {
			return transformOrThrowException(item);
		} catch (CheckedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	// simple way of handling exception
	/*
	 * public void simpleRuntimeExceptionHandlingExample() { List<Integer> integers
	 * = Arrays.asList(3, 9, 7, 0, 10, 20); integers.forEach(i -> { try {
	 * System.out.println(50 / i); } catch (ArithmeticException e) {
	 * System.err.println("Arithmetic Exception occured : " + e.getMessage()); }
	 * //System.out.println(50 / i); }); }
	 */

}
