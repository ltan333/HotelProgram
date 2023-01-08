/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.project5;

import com.project.validation.Input;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Me
 */
public class Customer extends Person {

    private String customerId;
    private String phoneNumber;
    private String characteristics;
    private Scanner scan = new Scanner(System.in);

    public Customer() {
    }

    public Customer(String customerId, String phoneNumber, String characteristics, String id, String name, String address) {
        super(id, name, address);
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.characteristics = characteristics;

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
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
        return "Customer{" + "Customer Code=" + customerId + ", Phone Number=" + phoneNumber + ", Characteristics=" + characteristics  + '}';
    }

    
    /**
     * Input Customer Info.
     * @param cusList List of Customer
     * @return Status boolean
     */
    public boolean input(LinkedList<Customer> cusList) {
        //Nhap CMND/CCCD
        String localID = Input.inputAboutPatern("Enter ID Number: ", "Error, Only VN CMND/CCCD ","\\d{9,13}", this.scan);
        //Check CMND/CCCD co bi trung khong
        Customer c = Management.findCustomerByCode(localID, cusList);
        //Neu trung bao loi va ket thuc, khong thi di tiep
        if (c.getId().equalsIgnoreCase(localID)) {
            System.out.println("Error, ID exist!");
            return false;
        }
        //Nhap toan bo thong tin con lai bang cac ham build san o class Input
        String localName = Input.inputAndCheckNotEmpty("Enter Name: ", "Error, Empty String!", this.scan);
        String localAddr = Input.inputAndCheckNotEmpty("Enter Address: ", "Error, Empty String!", this.scan);
        String localCharact = Input.inputAndCheckNotEmpty("Enter Characteristics: ", "Error, Empty String!", this.scan);
        String localPhone = Input.inputAboutPatern("Enter Phone Number: ", "Invalid Phone Number, Only 10 Number!","\\d{10}", this.scan);
        //Nhap thanh cong het thi set vao obj
        this.setId(localID);
        this.setName(localName);
        this.setAddress(localAddr);
        //Customer code se duoc auto them nen khong can nhap
        this.setCustomerId("C" + Management.generateId());
        this.setPhoneNumber(localPhone);
        this.setCharacteristics(localCharact);
        //Tra ve thanh cong
        return true;
    }

    /**
     * Edit Customer Info.
     * @param cusList List of Customer
     * @return Status boolean
     */
    public boolean edit(LinkedList<Customer> cusList) {
        //Tuong tu nhung khong auto Code vi eidt phai giu lai Code cu
        String localID = Input.inputAboutPatern("Enter ID Number: ", "Error, Only VN CMND/CCCD ","\\d{9,13}", this.scan);
        Customer c = Management.findCustomerByCode(localID, cusList);
        if (c.getId().equalsIgnoreCase(localID)) {
            System.out.println("Error, ID exist!");
            return false;
        }
        String localName = Input.inputAndCheckNotEmpty("Enter Name: ", "Error, Empty String!", this.scan);
        String localAddr = Input.inputAndCheckNotEmpty("Enter Address: ", "Error, Empty String!", this.scan);
        String localCharact = Input.inputAndCheckNotEmpty("Enter Characteristics: ", "Error, Empty String!", this.scan);
        String localPhone = Input.inputAboutPatern("Enter Phone Number: ", "Invalid Phone Number, Only 10 Number!","\\d{10}", this.scan);

        this.setId(localID);
        this.setName(localName);
        this.setAddress(localAddr);
        this.setPhoneNumber(localPhone);
        this.setCharacteristics(localCharact);
        return true;
    }
}
