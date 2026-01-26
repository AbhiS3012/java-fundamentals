package com.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream {

	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
		empList.add(new Employee(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
		empList.add(new Employee(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));
		empList.add(new Employee(4, "Melanie", 32, 125, "F", "HR", "Chennai", 2013));

		empList.add(new Employee(5, "Padma", 22, 150, "F", "IT", "Noida", 2013));
		empList.add(new Employee(6, "Milad", 27, 140, "M", "IT", "Gurugram", 2017));
		empList.add(new Employee(7, "Uzma", 26, 130, "F", "IT", "Pune", 2016));
		empList.add(new Employee(8, "Ali", 23, 145, "M", "IT", "Trivandam", 2015));
		empList.add(new Employee(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));
		
		// No of employees in the organization.
		System.out.println(empList.stream().count());
		System.out.println(empList.stream().collect(Collectors.counting()));
		
		// Total Salary
		System.out.println(empList.stream().mapToDouble(Employee::getSalary).sum());
		System.out.println(empList.stream().collect(Collectors.summingDouble(Employee::getSalary)));
		
		// Average Salary
		System.out.println(empList.stream().mapToDouble(Employee::getSalary).average().getAsDouble());
		System.out.println(empList.stream().collect(Collectors.averagingDouble(Employee::getSalary)));

		Map<String, List<Employee>> empByCity = empList.stream().collect(Collectors.groupingBy(Employee::getCity));
		System.out.println(empByCity);
		
		Map<String, Long> noOfEmpsByGender = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(noOfEmpsByGender);
		
		// Find employee count in every department
		Map<String, Long> noOfEmpsByDept = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
		System.out.println(noOfEmpsByDept);
		
		Map<String, Map<String, Long>> noOfEmpInDept = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.groupingBy(Employee::getGender, Collectors.counting())));
		System.out.println(noOfEmpInDept);
		
		empList.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);
		
		empList.stream().filter(e -> e.getAge() > 28).forEach(System.out::println);
		
		int maxAge = empList.stream().mapToInt(Employee::getAge).max().getAsInt();
		System.out.println(maxAge);
		
		Employee oldestEmp = empList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining)).get();
		System.out.println(oldestEmp);
		
		Employee seniorEmp = empList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst().get();
		System.out.println(seniorEmp);
		
		Map<String, Double> avgAge = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println(avgAge);
		
		Map<String, Map<String, Double>> avgAgeByDeptName = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))));
		System.out.println(avgAgeByDeptName);
		
		Map<String, Optional<Employee>> seniorEmpl = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining))));
		System.out.println(seniorEmpl);
		
		Employee youngestFemaleEmp = empList.stream().filter(e -> e.getGender().equals("F")).min(Comparator.comparingInt(Employee::getAge)).get();
		System.out.println(youngestFemaleEmp);
		
		Map<String, Optional<Employee>> youngestEmp = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.maxBy(Comparator.comparing(Employee::getYearOfJoining))));
		System.out.println(youngestEmp);
		
		Map<Boolean, List<Employee>> partitionEmployeesByAge = empList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 30));
		System.out.println(partitionEmployeesByAge);
		
		// Find if there any employees from HR Department.
		System.out.println(empList.stream().filter(e -> e.getDeptName().equals("HR")).findAny().get());
		
		// Department names where the number of employees in the department is over 3 :: 
		empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > 3).forEach(System.out::println);
	
		// Sort employees live in Blore city and print names
		empList.stream().filter(e -> e.getCity().equals("Blore")).sorted(Comparator.comparing(Employee::getName)).forEach(e -> System.out.println(e.getName()));
		
		// Find the department which has the highest number of employees
		System.out.println(noOfEmpsByDept.entrySet().stream().max(Map.Entry.comparingByValue()).get());
		
		// Sorting a Stream by age and name fields.
		empList.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(Employee::getName)).forEach(System.out::println);
		
		// Print average and total salary of the organization.
		DoubleSummaryStatistics empSalary = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(empSalary.getSum());
		System.out.println(empSalary.getAverage());
		
		//26. Print Average salary of each department.
		empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary))).entrySet().forEach(System.out::println);
	}

}

class Employee {
	private int id;
	private String name;
	private int age;
	private double salary;
	private String gender;
	private String deptName;
	private String city;
	private int yearOfJoining;

	public Employee(int id, String name, int age, double salary, String gender, String deptName, String city,
			int yearOfJoining) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.gender = gender;
		this.deptName = deptName;
		this.city = city;
		this.yearOfJoining = yearOfJoining;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", salary=" + salary
				+ ", gender='" + gender + '\'' + ", deptName='" + deptName + '\'' + ", city='" + city + '\''
				+ ", yearOfJoining='" + yearOfJoining + '\'' + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getYearOfJoining() {
		return yearOfJoining;
	}

	public void setYearOfJoining(int yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}
}
