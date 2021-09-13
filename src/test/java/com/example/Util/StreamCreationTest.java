package com.example.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.collectingAndThen;

import org.junit.jupiter.api.Test;

import com.example.Utils.Employee;

public class StreamCreationTest {
	private static Employee[] arrayOfEmps = { 
			new Employee(1, "EMP1", (long) 100000),
			new Employee(2, "EMP2", (long) 200000), 
			new Employee(3, "EMP3", (long) 300000),
			new Employee(3, "EMP3", (long) 300000),
			new Employee(4, "EMP4", (long) 500000),
			new Employee(4, "EMP5", (long) 800000),
			new Employee(4, "EMP6", (long) 600000),
			new Employee(4, "EMP7", (long) 700000),
			new Employee(5, "EMP7", (long) 700000)
			};
	private static int[] primitiveArray = {1,2,3,4};

	@Test
	public void testCreateStream() {
		

	}

	public void useStreamOnlyOnce() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Stream<Integer> stream = list.stream();
		stream.forEach(System.out::println);
		stream.forEach(System.out::println);
	}

	public void createStreamFromArrays() {
		Stream<Employee> empStream = Stream.of(arrayOfEmps);
		empStream.forEach(emp -> System.out.println(emp.getName()));
	}

	public void createStreamFromExistingList() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Stream<Employee> empStream = empList.stream();
		empStream.forEach(emp -> System.out.println(emp.getName()));
	}
	
	public void createStreamfromPrimitiveArray() {
		IntStream stream = Arrays.stream(primitiveArray);
		long max = stream.count();
		int count  = stream.max().getAsInt();
	}

	public void getDistinctElements() {
		List<Employee> empListOG = Arrays.asList(arrayOfEmps);
		List<Employee> res = empListOG.stream().distinct().collect(Collectors.toList());
		System.out.println(res.toString());
	}
	
	public void getDistinctElementsBasedOnPropertyWithoutStreams() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Map<Integer, Employee> empMap = new HashMap<>();
		for(Employee emp : empList) {
			if(!empMap.containsKey(emp.getId()))empMap.put(emp.getId(), emp);
			else if(empMap.get(emp.getId()).getSalary() < emp.getSalary()) empMap.put(emp.getId(), emp);
		}
		List<Employee> result = empMap.values().stream().collect(Collectors.toList());
		System.out.println(result.toString());
	}
	
	public void getDistinctElementsBasedOnPropertyWithoutStreams2() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Collections.sort(empList, this :: compare);
		Set<Employee> empSet = new TreeSet<>(comparingInt(Employee::getId));
		empSet.addAll(empList);
		List<Employee> resList = new ArrayList<>(empSet);
		System.out.println(resList.toString());
		
	}
	public void getDistinctElementsBasedOnPropertyByGroup() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		List<Employee> resList = empList.stream()
		 .collect(Collectors.groupingBy(emp -> emp.getId(), 
				 Collectors.maxBy(Comparator.comparingLong(Employee::getSalary))))
		 .values()
		 .stream()
		 .map(opt -> opt.get())
		 .collect(Collectors.toList());
		System.out.println(resList.toString());
	}
	
	public void getDistinctElementsBasedOnProperty() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		List<Employee> resList = empList.stream()
				.sorted(this :: compare)
				.collect(
				collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Employee::getId))), ArrayList::new));
		
		System.out.println(resList.toString());
	}
	
	public void getFrequencyMapOfNames() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Map<String,Integer> freqMap = empList.stream()
				.collect(Collectors.groupingBy(Employee::getName,Collectors.summingInt(e -> 1)));
		freqMap.entrySet().forEach(entry -> System.out.println("Name: "+entry.getKey()+" freq: "+entry.getValue()));

	}
	
	private int compare(Employee a , Employee b) {
		if(a.getId() < b.getId()) return -1;
		else if(a.getId() > b.getId()) return 1;
		else if(a.getSalary() < b.getSalary())return 1;
		else if(a.getSalary() > b.getSalary())return -1;
		return 0;
	}

}
