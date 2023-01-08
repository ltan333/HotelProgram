/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.project5;

import com.project.validation.Input;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Me
 */
public class Management {

    //Khai bao bien nay de auto generate ID khong bi trung nhau
    private static AtomicLong idCounter = new AtomicLong();
    //List nhan vien, luu tru tat ca nhan vien trong chuong trinh
    private LinkedList<Employee> employees = new LinkedList<>();
    //List khach hang, luu tru tat ca nhan vien trong chuong trinh
    private LinkedList<Customer> customers = new LinkedList<>();
    //List hoa don, luu tru tat ca nhan vien trong chuong trinh
    private LinkedList<Bill> bills = new LinkedList<>();
    private Scanner scan = new Scanner(System.in);

    /**
     * Create New Employee.
     *
     * @param empList List of Employee
     */
    public void createEmp(LinkedList<Employee> empList) {
        System.out.println("==============> CREATE NEW EMPLOYEE <==============");
        Employee emp = new Employee();
        try {
            //Su dung ham input cua obj de nhap thong tin va nhan ve status
            boolean status = emp.input(empList);
            //Neu staut = false la nhap thong tin that bai, bao loi va ket thuc
            if (!status) {
                return;
            }
            //Neu true thi bao thang cong va them vao list luu tru
            empList.add(emp);
            System.out.println("Employee Created");
        } catch (Exception e) {
            System.out.println("Sometimes Went Wrong!");
        }
    }

    /**
     * Create New Customer.
     *
     * @param customerList List of Customer
     */
    public void createCustomer(LinkedList<Customer> customerList) {
        //Tuong tu ham tao nhan vien
        System.out.println("==============> CREATE NEW CUSTOMER <==============");
        Customer cus = new Customer();
        try {
            boolean status = cus.input(customerList);
            if (!status) {
                return;
            }
            customerList.add(cus);
            System.out.println("Customer Created");
        } catch (Exception e) {
            System.out.println("Sometimes Went Wrong!");
        }
    }

    /**
     * Create New Bill.
     *
     * @param customerList List of Customer
     */
    public void createBill(LinkedList<Customer> customerList) {
        //Tuong tu ham tao nhan vien
        System.out.println("==============> CREATE NEW BILL <==============");
        Bill bill = new Bill();
        try {
            boolean status = bill.input(customerList);
            if (!status) {
                return;
            }
            bills.add(bill);
            System.out.println("Bill Created");
        } catch (Exception e) {
            System.out.println("Sometimes Went Wrong!");
        }
    }

    /**
     * Edit A Employee.
     *
     * @param empList List of Employee
     */
    public void editEmp(LinkedList<Employee> empList) {
        System.out.println("==============> EDIT A EMPLOYEE <==============");
        //Nhap ma nhan vien de xac dinh can edit nhan vien nao
        String empID = Input.inputAndCheckNotEmpty("Enter Employee Code: ", "Error, Empty String!", scan);
        //Tim xem nhan vien do co ton tai khong
        for (int i = 0; i < empList.size(); i++) {
            //Neu co thi cho edit lai thong tin tru ma nhan vien
            if (empList.get(i).getEmployeeId().equalsIgnoreCase(empID)) {
                boolean status = empList.get(i).edit(empList);
                //Kiem tra thong tin edit dung dinh dang khong
                if (!status) {
                    //Neu khon bao loi, ket thuc
                    return;
                }
                //Neu OK
                System.out.println("Infomation Updated");
                return;
            }
        }
    }

    /**
     * Edit A Bill.
     *
     * @param cusList List of Customer
     * @param billList List of Bill
     */
    public void editBill(LinkedList<Customer> cusList, LinkedList<Bill> billList) {
        //Tuong tu ham edit khac
        System.out.println("==============> EDIT A BILL <==============");
        String billID = Input.inputAndCheckNotEmpty("Enter Bill ID: ", "Error, Empty String!", scan);
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getBillId().equalsIgnoreCase(billID)) {
                boolean status = billList.get(i).edit(cusList);
                if (!status) {
                    return;
                }
                System.out.println("Infomation Updated");
                return;
            }
        }
    }

    /**
     * Edit A Customer.
     *
     * @param cusList List of Customer
     */
    public void editCustomer(LinkedList<Customer> cusList) {
        //Tuong tu ham edit khac
        System.out.println("==============> EDIT A CUSTOMER <==============");
        String cusID = Input.inputAndCheckNotEmpty("Enter Customer Code: ", "Error, Empty String!", scan);
        for (int i = 0; i < cusList.size(); i++) {
            if (cusList.get(i).getCustomerId().equalsIgnoreCase(cusID)) {
                boolean status = cusList.get(i).edit(cusList);
                if (!status) {
                    return;
                }
                System.out.println("Infomation Updated");
                return;
            }
        }
    }

    /**
     * Delete A Employee.
     *
     * @param empList List of Employee
     */
    public void delEmp(LinkedList<Employee> empList) {
        System.out.println("==============> DELETE A EMPLOYEE <==============");
        //Nhap ma nhan vien de xac dinh nhan vien muon xoa
        String empID = Input.inputAndCheckNotEmpty("Enter Employee Code: ", "Error, Empty String!", scan);
        //Tim nhan vien do
        for (int i = 0; i < empList.size(); i++) {
            //Neu co ton tai thi in thong tin cho xem
            if (empList.get(i).getEmployeeId().equalsIgnoreCase(empID)) {
                //Neu co thi in ra thong tin cho nguoi ta doc
                System.out.println("Employee Code: " + empList.get(i).getEmployeeId());
                System.out.println("ID Number: " + empList.get(i).getId());
                System.out.println("Name: " + empList.get(i).getName());
                System.out.println("Address: " + empList.get(i).getAddress());
                System.out.println("Salary: " + empList.get(i).getSalary());
                //Hoi co chac chan xoa nhan vien khong -> Dung mau nhap lieu viet san
                if (Input.inputYesOrNo("Are you sure to delete? (y/n): ", "Only type yes or no please!", scan)) {
                    //Neu yes thi xoa
                    empList.remove(empList.get(i));
                    //Thanh cong
                    System.out.println("Delete Success");
                    return;
                }
                //Neu No thi thoi
                System.out.println("");
                return;
            }
        }

    }

    /**
     * Delete A Employee.
     *
     * @param empList List of Employee
     */
    public void delCustomer(LinkedList<Customer> cusList) {
        //Tuong tu ham delete khac
        System.out.println("==============> DELETE A CUSTOMER <==============");
        String cusID = Input.inputAndCheckNotEmpty("Enter Customer Code: ", "Error, Empty String!", scan);
        for (int i = 0; i < cusList.size(); i++) {
            if (cusList.get(i).getCustomerId().equalsIgnoreCase(cusID)) {
                //Neu co thi in ra thong tin cho nguoi ta doc
                System.out.println("Customer Code: " + cusList.get(i).getCustomerId());
                System.out.println("ID Number: " + cusList.get(i).getId());
                System.out.println("Name: " + cusList.get(i).getName());
                System.out.println("Address: " + cusList.get(i).getAddress());
                System.out.println("Phone Number: " + cusList.get(i).getPhoneNumber());
                //Hoi co chac chan xoa khong -> Dung mau nhap lieu viet san
                if (Input.inputYesOrNo("Are you sure to delete? (y/n): ", "Only type yes or no please!", scan)) {
                    //Neu yes thi xoa
                    cusList.remove(cusList.get(i));
                    //Thanh cong
                    System.out.println("Delete Success");
                    return;
                }
                //Neu No thi thoi
                System.out.println("");
                return;
            }
        }
    }

    /**
     * Delete A Employee.
     *
     * @param empList List of Employee
     */
    public void delBill(LinkedList<Bill> billList) {
        //Tuong tu ham delete khac
        System.out.println("==============> DELETE A BILL <==============");
        String billID = Input.inputAndCheckNotEmpty("Enter Bill ID: ", "Error, Empty String!", scan);
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getBillId().equalsIgnoreCase(billID)) {
                //Neu co thi in ra thong tin cho nguoi ta doc
                System.out.println("Bill ID: " + billList.get(i).getBillId());
                System.out.println("Customer Name: " + billList.get(i).getCustomer().getName());
                System.out.println("Check-in: " + new SimpleDateFormat("dd/MM/yyyy").format(billList.get(i).getCheckInDay()));
                System.out.println("Check-out: " + new SimpleDateFormat("dd/MM/yyyy").format(billList.get(i).getCheckoutDay()));
                System.out.println("Total Pay: " + billList.get(i).getTotalPay());
                //Hoi co chac chan xoa khong -> Dung mau nhap lieu viet san
                if (Input.inputYesOrNo("Are you sure to delete? (y/n): ", "Only type yes or no please!", scan)) {
                    //Neu yes thi xoa
                    billList.remove(billList.get(i));
                    //Thanh cong
                    System.out.println("Delete Success");
                    return;
                }
                //Neu No thi thoi
                System.out.println("");
                return;
            }
        }
    }

    /**
     * Show all employee.
     *
     * @param empList List of Employee
     */
    public static void showAllEmp(LinkedList<Employee> empList) {
        System.out.println("==============> SHOW ALL EMPLOYEE <==============");
        //Quet het list va in ra thong tin bang ham toString cua obj
        for (Employee e : empList) {
            System.out.println(e.toString());
        }
    }

    /**
     * Show all customer.
     *
     * @param cusList List of Customer
     */
    public static void showAllCustomer(LinkedList<Customer> cusList) {
        System.out.println("==============> SHOW ALL CUSTOMER <==============");
        //Quet het list va in ra thong tin bang ham toString cua obj

        for (Customer e : cusList) {
            System.out.println(e.toString());
        }
    }

    /**
     * Show all bill.
     *
     * @param billList List of Bill
     */
    public static void showAllBill(LinkedList<Bill> billList) {
        System.out.println("==============> SHOW ALL BILL <==============");
        //Quet het list va in ra thong tin bang ham toString cua obj

        for (Bill e : billList) {
            System.out.println(e.toString());
        }
    }
    
    /**
     * Auto Generate Unique ID.
     * @return Unique ID
     */
    public static String generateId() {
        //Random 1 so chua 4 chu so
        String randomID = ThreadLocalRandom.current().nextInt(1000, 9998 + 1) + "";
        //Ghep 4 so vua random voi 1 so duy nhat cua bien idCounter da tao o tren -> Khong bao gio bi trung
        return randomID + String.valueOf(idCounter.getAndIncrement());
    }

    /**
     * Find Customer By Customer Code
     * @param cusId Customer Code
     * @param cusList List of Customer
     * @return Customer Found or Empty Customer
     */
    public static Customer findCustomerByCode(String cusId, LinkedList<Customer> cusList) {
        for (Customer c : cusList) {
            if (c.getCustomerId().equalsIgnoreCase(cusId)) {
                return c;
            }
        }
        return new Customer("", "", "", "", "", "");
    }

    /**
     * Find Employee By Employee Code
     * @param empId Employee Code
     * @param empList List ofEmployee
     * @return Employee Found or Empty Employee
     */
    public static Employee findEmpByEmpCode(String empId, LinkedList<Employee> empList) {
        for (Employee c : empList) {
            if (c.getEmployeeId().equalsIgnoreCase(empId)) {
                return c;
            }
        }
        return new Employee("", 0, "", "", "");
    }

    /**
     * Find Bill By Bill Id.
     * @param billId Bill Id
     * @param billList List of Bill
     * @return Bill Found or Empty Bill
     */
    public static Bill findBillID(String billId, LinkedList<Bill> billList) {
        for (Bill c : billList) {
            if (c.getBillId().equalsIgnoreCase(billId)) {
                return c;
            }
        }
        return new Bill("", new Customer(), new Date(), new Date(), 0);
    }

    /**
     * Search Function.
     * @param option 1 employee, 2 customer, 3 bill, other error
     * @param empList List of Employee
     * @param cusList List of Customer
     * @param billList List of Bill
     */
    public void search(int option, LinkedList<Employee> empList, LinkedList<Customer> cusList, LinkedList<Bill> billList) {
        System.out.println("==============> SEARCHING <==============");
        //Bien de check co tim duoc ket qua khong.
        boolean isFound = false;
        //Nhap key de tim kiem
        System.out.print("Enter Key Word: ");
        String code = scan.nextLine();
        //option la tim cai gi, 1 employee, 2 customer, 3 bill
        switch (option) {
            case 1 -> {
                //Tim trong employee list
                for (Employee e : empList) {
                    //Neu tim thay thi in thong tin ra
                    if (e.getEmployeeId().toLowerCase().contains(code.toLowerCase())) {
                        System.out.println("-------------------------------------------");
                        System.out.println("Employee Code: " + e.getEmployeeId());
                        System.out.println("ID Number: " + e.getId());
                        System.out.println("Name: " + e.getName());
                        System.out.println("Address: " + e.getAddress());
                        System.out.println("Salary: " + e.getSalary());
                        //Da thay ket qua thi sua bien check lai thanh true
                        isFound = true;
                    }
                }
            }
            case 2 -> {
                //Tim trong Customer list
                for (Customer e : cusList) {
                    //Neu tim thay thi in thong tin ra
                    if (e.getCustomerId().toLowerCase().contains(code.toLowerCase())) {
                        System.out.println("-------------------------------------------");
                        System.out.println("Customer Code: " + e.getCustomerId());
                        System.out.println("ID Number: " + e.getId());
                        System.out.println("Name: " + e.getName());
                        System.out.println("Address: " + e.getAddress());
                        System.out.println("Phone Number: " + e.getPhoneNumber());
                        System.out.println("Characteristics: " + e.getCharacteristics());
                        //Da thay ket qua thi sua bien check lai thanh true
                        isFound = true;
                    }
                }
            }
            case 3 -> {
                //Tim trong bill list
                for (Bill e : billList) {
                    //Neu tim thay thi in thong tin ra
                    if (e.getBillId().toLowerCase().contains(code.toLowerCase())) {
                        System.out.println("-------------------------------------------");
                        System.out.println("Bill ID: " + e.getBillId());
                        System.out.println("Customer Name: " + e.getCustomer().getName());
                        System.out.println("Check-in: " + new SimpleDateFormat("dd/MM/yyyy").format(e.getCheckInDay()));
                        System.out.println("Check-out: " + new SimpleDateFormat("dd/MM/yyyy").format(e.getCheckoutDay()));
                        System.out.println("Total Pay: " + e.getTotalPay());
                        //Da thay ket qua thi sua bien check lai thanh true
                        isFound = true;
                    }
                }
            }
            //option khon ton tai
            default ->
                System.out.println("Invalid Option!");
        }
        //Neu khong co ket qua thi bien check van false, thi in ra khong tim thay
        if (!isFound) {
            System.out.println("Not Found!");
        }
    }

    /**
     * Show Menu and RUN.
     */
    public void mainMenu(Management m) {
        try {
            Scanner scan = new Scanner(System.in);
            int choose;
            do {
                System.out.print("==============> HOTEL MANAGEMENT SYSTEM <=============="
                        + "\n1. Create New Employee"
                        + "\n2. Edit Employee By Code"
                        + "\n3. Delete Employee By Code"
                        + "\n4. Show All Employee"
                        + "\n5. Employee Search"
                        + "\n6. Create New Customer"
                        + "\n7. Edit Customer By Code"
                        + "\n8. Delete Customer By Code"
                        + "\n9. Show All Customer"
                        + "\n10. Customer Search"
                        + "\n11. Create New Bill"
                        + "\n12. Edit Bill By ID"
                        + "\n13. Delete Bill By ID"
                        + "\n14. Show All Bill"
                        + "\n15. Bill Search"
                        + "\n16. Exit"
                        + "\n   Please choose: ");
                choose = scan.nextInt();
                scan.nextLine();
                switch (choose) {
                    case 1 ->
                        m.createEmp(m.employees);
                    case 2 ->
                        m.editEmp(m.employees);
                    case 3 ->
                        m.delEmp(m.employees);
                    case 4 ->
                        Management.showAllEmp(m.employees);
                    case 5 ->
                        m.search(1, m.employees, m.customers, m.bills);
                    case 6 ->
                        m.createCustomer(m.customers);
                    case 7 ->
                        m.editCustomer(m.customers);
                    case 8 ->
                        m.delCustomer(m.customers);
                    case 9 ->
                        Management.showAllCustomer(m.customers);
                    case 10 ->
                        m.search(2, m.employees, m.customers, m.bills);
                    case 11 ->
                        m.createBill(m.customers);
                    case 12 ->
                        m.editBill(m.customers, m.bills);
                    case 13 ->
                        m.delBill(m.bills);
                    case 14 ->
                        Management.showAllBill(m.bills);
                    case 15 ->
                        m.search(3, m.employees, m.customers, m.bills);
                    case 16 ->
                        System.out.println("---Powered by Me!---");
                    default -> //Nhập ko đúng case thì hiện cái này.
                        System.out.println("Invalid option!");
                }
                //Nhập đúng case thì lập lại.
            } while (choose > 0 && choose < 16);
        } catch (InputMismatchException e) {
            //Nhập chữ hay &%*^ thì cho nhập lại luôn.
            System.out.println("Invalid option!");
            mainMenu(m);
        }
    }

}
