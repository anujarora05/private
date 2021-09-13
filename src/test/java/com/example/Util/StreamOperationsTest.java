package com.example.Util;

import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.example.Utils.Employee;

public class StreamOperationsTest {
	private List<List<String>> namesNested = Arrays.asList( 
		      Arrays.asList("Anuj", "Sahil"), 
		      Arrays.asList("Manu", "Aman"), 
		      Arrays.asList("Nutan", "Mayank"),
		      Arrays.asList("Shyaam", "Aman"));
	private static Employee[] arrayOfEmps = { 
			new Employee(1, "EMP1", (long) 100000),
			new Employee(2, "EMP2", (long) 200000), 
			new Employee(3, "EMP3", (long) 300000),
			new Employee(4, "EMP4", (long) 500000),
			new Employee(3, "EMP3", (long) 500000),
			new Employee(4, "EMP5", (long) 800000),
			new Employee(4, "EMP6", (long) 600000),
			new Employee(4, "EMP7", (long) 700000),
			new Employee(5, "EMP7", (long) 700000)
			};
	private List<String> words = Arrays.asList("Hello", "world", "word");
	
	@Test
	public void testStreamOperations() {
		//testFilter();
		//testMap();
		//getUniqueNames();
		//getAllLetters();
		//createEmpIdToNameMap();
		//createEmpNameToTotalSalayMap();
		//createEmpNameToTotalSalaryMap2();
		//getFrequencyMapOfNames();
		//getDistinctElementsBasedOnPropertyWithoutStreams();
		
		//getDistinctElementsBasedOnPropertyWithStreams2();
		//createEmpNameToTotalSalayMap();
		//getFrequencyMapOfNames();
		getDistinctElementsBasedOnPropertyWithStreams2();
		
	}
	//filter all employees less than 500000 salary
	public void testFilter() {
		List<Employee> givenList = Arrays.asList(arrayOfEmps);
		List<Employee> filteredList = givenList.stream().filter(el  -> el.getSalary()> 500000)
				.collect(Collectors.toList());
		
		System.out.println(filteredList.toString());
	}
	
	//map salary of all employees to dollars by dividing it by 75
	public void testMap() {
		List<Employee> givenList = Arrays.asList(arrayOfEmps);
		List<Long> modified = givenList.stream().map(el -> el.getSalary()/75)
				.collect(Collectors.toList());
		
		System.out.println(modified.toString());
	}
	
	//flatmap -> get unique names from namesNested
	public void getUniqueNames() {
		List<String> teamMembers =  namesNested.stream().map(Collection::stream).flatMap(stream -> stream)
		 .distinct()
		 .collect(Collectors.toList());
		
		System.out.println(teamMembers.toString());
	}
	
	//flatmap -> get all distinct letters in sorted order from the list of words hello world
	public void getAllLetters() {
		List<String> charString = words.stream()
				.map(word -> word.toLowerCase().split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.sorted()
				.limit(2)
				.collect(Collectors.toList()); 
		System.out.println(charString.toString());
	}
	
	//limit -> limit results to 3
	public void testLimit() {
		
	}
	
	//create map of Id to name using collectors, in case of conflict consider first one.
	public void createEmpIdToNameMap() {
		List<Employee> givenList = Arrays.asList(arrayOfEmps);
		Map<Integer,String> nameToIdMap = givenList
				.stream()
				.collect(Collectors.toMap(Employee::getId, Employee::getName , (v1,v2) -> v1));
		
		nameToIdMap.entrySet().forEach(entry -> {
			System.out.println("Id: "+entry.getKey()+" Name: "+entry.getValue());
		});
	}
	
	//create map of name to total Salary using toMap
	public void createEmpNameToTotalSalayMap() {
		List<Employee> givenList = Arrays.asList(arrayOfEmps);
		Map<String,Long> nameToIdMap = givenList.stream()
				.collect(Collectors.toMap(Employee::getName, Employee::getSalary, (v1,v2) -> v1+v2));
			
		nameToIdMap.entrySet().forEach(entry -> {
			System.out.println("Id: "+entry.getKey()+" Name: "+entry.getValue());
			
		});
		
	}
	//create map of Name to total Salary using grouping By
	public void createEmpNameToTotalSalaryMap2() {
		List<Employee> givenList = Arrays.asList(arrayOfEmps);
		Map<String, Optional<Employee>> nameToIdMap = givenList.stream()
				.collect(Collectors.groupingBy(Employee::getName, 
						Collectors.maxBy(Comparator.comparingLong(Employee::getSalary))));

		nameToIdMap.entrySet().forEach(entry -> {
			System.out.println("Id: " + entry.getKey() + " Salary: " + entry.getValue());
		});
	}
	//get freq of names using groupingBy
	public void getFrequencyMapOfNames() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Map<String, Long> nameToFreqMap = empList.stream()
				.collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));

		nameToFreqMap.entrySet().forEach(entry -> {
			System.out.println("Id: " + entry.getKey() + " Freq: " + entry.getValue());
		});
		
	}
	
	public void findFirstandAny() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Optional<Employee> optionalEmp = empList.stream().findFirst();
		
		
		
	}
	//using mapToInt
	public void findMaxSalary() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		OptionalLong maxSalary = empList.stream().mapToLong(Employee::getSalary).max();
		

	}
	public void reduce() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Long sumSal = empList.stream()
			      .map(Employee::getSalary)
			      .reduce((long) 0, Long::sum);
		
	}
	private int compare(Employee a , Employee b) {
		if(a.getId() < b.getId()) return -1;
		else if(a.getId() > b.getId()) return 1;
		else if(a.getSalary() < b.getSalary())return 1;
		else if(a.getSalary() > b.getSalary())return -1;
		return 0;
	}
	
	// print distinct employee objects
	public void getDistinctElements() {
		List<Employee> empListOG = Arrays.asList(arrayOfEmps);
		List<Employee> res = new ArrayList<>();
		System.out.println(res.toString());
	}
	
	
	//not most optimised -> brute force
	/*
	 * public void getDistinctElementsBasedOnPropertyWithoutStreams() {
	 * List<Employee> empList = Arrays.asList(arrayOfEmps);
	 * Collections.sort(empList, this :: compare); Set<Employee> empSet = new
	 * TreeSet<>(comparingInt(Employee::getId)); empSet.addAll(empList);
	 * List<Employee> resList = new ArrayList<>(empSet);
	 * System.out.println(resList.toString());
	 * 
	 * }
	 */
	//optimised but verbose
	public void getDistinctElementsBasedOnPropertyWithoutStreams2() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		Map<Integer, Employee> empMap = new HashMap<>();
		for(Employee emp : empList) {
			if(!empMap.containsKey(emp.getId()))empMap.put(emp.getId(), emp);
			else if(empMap.get(emp.getId()).getSalary() < emp.getSalary()) empMap.put(emp.getId(), emp);
		}
		List<Employee> result = empMap.values().stream().sorted(this::compare).collect(Collectors.toList());
		System.out.println(result.toString());
	}
	
	//using streams nd collectors
	/*
	 * public void getDistinctElementsBasedOnPropertyWithStreams() { List<Employee>
	 * empList = Arrays.asList(arrayOfEmps); List<Employee> resList =
	 * empList.stream() .sorted(this::compare) .collect(Collectors
	 * .collectingAndThen(Collectors .toCollection(() -> new
	 * TreeSet<>(comparingInt(Employee::getId))), ArrayList::new));
	 * 
	 * System.out.println(resList.toString());
	 * 
	 * }
	 */
		
	public void getDistinctElementsBasedOnPropertyWithStreams2() {
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		List<Employee> resList = empList.stream()
				.collect(Collectors
						.groupingBy(Employee::getId, Collectors.maxBy(Comparator.comparingLong(Employee::getSalary))))
				.values()
				.stream()
				.map(opt  -> opt.get())
				.collect(Collectors.toList());
				
		
		System.out.println(resList.toString());
	}
	
	
	
	
	

}
