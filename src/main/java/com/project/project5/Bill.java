/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.project5;

import java.util.Date;
import com.project.validation.Input;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Me
 */
public class Bill {

    private String billId;
    private Customer customer;
    private Date checkInDay;
    private Date checkoutDay;
    private long totalPay;
    private Scanner scan = new Scanner(System.in);

    public Bill() {
    }

    public Bill(String billId, Customer customer, Date checkInDay, Date checkoutDay, long totalPay) {
        this.billId = billId;
        this.customer = customer;
        this.checkInDay = checkInDay;
        this.checkoutDay = checkoutDay;
        this.totalPay = totalPay;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCheckInDay() {
        return checkInDay;
    }

    public void setCheckInDay(Date checkInDay) {
        this.checkInDay = checkInDay;
    }

    public Date getCheckoutDay() {
        return checkoutDay;
    }

    public void setCheckoutDay(Date checkoutDay) {
        this.checkoutDay = checkoutDay;
    }

    public long getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(long totalPay) {
        this.totalPay = totalPay;
    }

    @Override
    public String toString() {
        return "Bill{" + "Bill ID=" + billId + ", Customer=" + customer.getName() + ", CheckInDay=" + checkInDay + ", CheckoutDay=" + checkoutDay + ", Total Pay=" + totalPay + '}';
    }
    
    
    /**
     * Input Bill Info.
     * @param customers List of Customer
     * @return Status boolean
     */
    public boolean input(LinkedList<Customer> customers) {
        //Dau tien doc ma khach hang xem co ton tai khong
        String cusId = Input.inputAndCheckNotEmpty("Enter Customer Code: ", "Error, Empty String!", this.scan);
        //Su dung ham viet san o class Management
        Customer c = Management.findCustomerByCode(cusId, customers);
        //Neu khong ton tai thi fail va ket thuc, co thi nhan customer code nay
        if (!c.getCustomerId().equalsIgnoreCase(cusId)) {
            System.out.println("Customer ID not found!");
            return false;
        }
        //Su dung ham build san de nhan 1 gia tri ngay thang theo format dd/MM/yyyy lam ngay check in
        Date checkin = Input.inputAndCheckDate("Enter Check-in Date: ", "Invalid Date - Example 1/1/1999", "dd/MM/yyyy", this.scan);
        Date checkout;
        do {
            //Tuong tu voi ngay checkout
            checkout = Input.inputAndCheckDate("Enter Check-out Date: ", "Invalid Date - Example 1/1/1999", "dd/MM/yyyy", this.scan);
            //Phai dam bao ngay check in phai truoc ngay check out
            if(checkin.before(checkout)){
                break;
                //Neu khong thi bao loi va cho nhap lai
            }else{
                System.out.println("Error, Check-in after Check-out!");
            }
            
        } while (true);
        //Nhap tong tien
        long total = (long) Input.inputAndCheckPositiveNumber("Enter Total Pay: ", "Error, Negative Number!", this.scan, true);
        //Su dung ham tao ID de auto ID cho bill khong can nhap
        this.setBillId("B"+Management.generateId());
        //Set cac gia tri o tren vao obj
        this.setCustomer(c);
        this.setCheckInDay(checkin);
        this.setCheckoutDay(checkout);
        this.setTotalPay(total);
        return true;
    }
    
    /**
     * Edit Bill Info.
     * @param customers List of Customer
     * @return Status boolean
     */
    public boolean edit(LinkedList<Customer> customers) {
        //Copy o tren nhung khong auto ID vi edit phai giu lai ID cu.
        String cusId = Input.inputAndCheckNotEmpty("Enter Customer Code: ", "Error, Empty String!", this.scan);
        Customer c = Management.findCustomerByCode(cusId, customers);
        if (!c.getCustomerId().equalsIgnoreCase(cusId)) {
            System.out.println("Customer ID not found!");
            return false;
        }
        Date checkin = Input.inputAndCheckDate("Enter Check-in Date: ", "Invalid Date - Example 1/1/1999", "dd/MM/yyyy", this.scan);
        Date checkout;
        do {
            checkout = Input.inputAndCheckDate("Enter Check-out Date: ", "Invalid Date - Example 1/1/1999", "dd/MM/yyyy", this.scan);
            if(checkin.before(checkout)){
                break;
            }else{
                System.out.println("Error, Check-in after Check-out!");
            }
            
        } while (true);
        long total = (long) Input.inputAndCheckPositiveNumber("Enter Total Pay: ", "Error, Negative Number!", this.scan, true);
        this.setCustomer(c);
        this.setCheckInDay(checkin);
        this.setCheckoutDay(checkout);
        this.setTotalPay(total);
        return true;
    }
    

}
