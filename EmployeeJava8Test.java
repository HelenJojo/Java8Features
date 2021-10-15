package com.helen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee2 {

	int id;
	String name;
	int age;
	String gender;
	String department;
	int yearOfJoining;
	double salary;
}

public class EmployeeJava8Test {

	public static void main(String[] args) {

		List<Employee2> list = new ArrayList<>();
		list.add(new Employee2(100, "Alex", 25, "Male", "IT", 2021, 35000.0));
		list.add(new Employee2(101, "Helen", 26, "Female", "HR", 2021, 35000.0));
		list.add(new Employee2(102, "Saniya", 27, "Female", "Product Development", 2020, 40000.0));
		list.add(new Employee2(103, "Fatmana", 28, "Female", "IT", 2020, 34100.0));
		list.add(new Employee2(104, "Michael", 29, "Male", "HR", 2019, 43000.0));
		list.add(new Employee2(105, "Behar", 30, "Male", "IT", 2002, 35000.0));
		list.add(new Employee2(106, "Creigh", 31, "Male", "IT", 2005, 35000.0));
		list.add(new Employee2(107, "Saran", 25, "Male", "Accounts", 2015, 22000.0));

		// 1. How many male and female emps are there in this class? Optimized code?
		Map<String, Long> noOfMandf = list.stream()
				.collect(Collectors.groupingBy(Employee2::getGender, Collectors.counting()));
		System.out.println("-------------");

		// 2. Print all unique departments in this class
		list.stream().map(n -> n.getDepartment()).distinct().forEach(System.out::println);
		list.stream().map(Employee2::getDepartment).distinct().forEach(System.out::println);
		System.out.println("-------------");

		// 3. Find the average salary of male and female employees?
		Map<String, Double> avgsalmalefemale = list.stream()
				.collect(Collectors.groupingBy(Employee2::getGender, Collectors.averagingDouble(Employee2::getSalary)));
		System.out.println("-------------");

		// 4. Find the youngest male employee in the IT department?
		Employee2 youngmaleIT = list.stream()
				.filter(n -> n.getDepartment().equals("IT") && n.getGender().equals("Male"))
				.min(Comparator.comparing(Employee2::getAge)).get();
		System.out.println(youngmaleIT);
		System.out.println("-------------");

		// 5. Find out the most working experience in this class?

		Employee2 seniorMostEmployeeWrapper = list.stream().sorted(Comparator.comparingInt(Employee2::getYearOfJoining))
				.findFirst().get();
		System.out.println("------------");
		// 6. List don the employee name of all employees in each department?
		Map<String, List<Employee2>> employeesofDept = list.stream()
				.collect(Collectors.groupingBy(Employee2::getDepartment));

		System.out.println(employeesofDept);
		System.out.println("------------");
		// 7. Find out the average salary and total salary of the whole class

		list.stream().collect(Collectors.summarizingDouble(Employee2::getSalary));
		list.stream().collect(Collectors.averagingDouble(Employee2::getSalary));

		// 8. Seperate the employees who are younger or equal to 25 years from those
		// employees who are older than 25 yrs?

		Map<Boolean, List<Employee2>> separateAge = list.stream()
				.collect(Collectors.partitioningBy(n -> n.getAge() < 25));
		System.out.println(separateAge);

	}
}
