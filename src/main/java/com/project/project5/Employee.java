/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.project5;

import com.project.validation.Input;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ann
 */
public class Employee extends Person {
    private String employeeId;
    private long salary;
    private Scanner scan = new Scanner(System.in);

    public Employee() {
    }

    public Employee(String employeeId, long salary, String id, String name, String address) {
        super(id, name, address);
        this.employeeId = employeeId;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public String toString() {
        return "Employee{" + "Employee Code=" + employeeId + ", Salary=" + salary + '}';
    }
    
    /**
     * Input Employee Info.
     * @param empList List of Employee
     * @return Status boolean
     */
    public boolean input(LinkedList<Employee> empList){
        //Tuong tu class Customer
        String localID = Input.inputAboutPatern("Enter ID Number: ", "Error, Empty String!","\\d{9,13}", this.scan);
        Employee c = Management.findEmpByEmpCode(localID, empList);
        if (c.getId().equalsIgnoreCase(localID)) {
            System.out.println("Error, ID exist!");
            return false;
        }
        String localName = Input.inputAndCheckNotEmpty("Enter Name: ", "Error, Empty String!", this.scan);
        String localAddr = Input.inputAndCheckNotEmpty("Enter Address: ", "Error, Empty String!", this.scan);
        //String localPhone = Input.inputAndCheckPhoneVN("Enter Phone Number: ", "Invalid Phone Number, Only VN Phone Number!", this.scan);
        long localSalary = (long)Input.inputAndCheckPositiveNumber("Enter Salary: ", "Error, Only Positive Number", this.scan, false);
        
        this.setId(localID);
        this.setName(localName);
        this.setAddress(localAddr);
        this.setEmployeeId("E"+Management.generateId());
        this.setSalary(localSalary);
        return true;
    }
    
    /**
     * Edit Employee Info.
     * @param empList List of Employee
     * @return Status boolean
     */
    public boolean edit(LinkedList<Employee> empList){
        //Tuong tu class Customer
        String localID = Input.inputAboutPatern("Enter ID Number: ", "Error, Empty String!","\\d{9,13}", this.scan);
        Employee c = Management.findEmpByEmpCode(localID, empList);
        if (c.getId().equalsIgnoreCase(localID)) {
            System.out.println("Error, ID exist!");
            return false;
        }
        String localName = Input.inputAndCheckNotEmpty("Enter Name: ", "Error, Empty String!", this.scan);
        String localAddr = Input.inputAndCheckNotEmpty("Enter Address: ", "Error, Empty String!", this.scan);
        //String localPhone = Input.inputAndCheckPhoneVN("Enter Phone Number: ", "Invalid Phone Number, Only VN Phone Number!", this.scan);
        long localSalary = (long)Input.inputAndCheckPositiveNumber("Enter Salary: ", "Error, Only Positive Number", this.scan, false);
        
        this.setId(localID);
        this.setName(localName);
        this.setAddress(localAddr);
        this.setSalary(localSalary);
        return true;
    }
    
}
