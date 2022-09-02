package com.jdbc.main;

import com.jdbc.model.Employee;
import com.jdbc.service.DatabaseService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws SQLException {
        DatabaseService databaseService = new DatabaseService();
        try (Scanner sc = new Scanner(System.in);) {
            boolean isRunning = true;
            while (isRunning) {

                System.out.println("Enter Choice");
                System.out.println("1. Insert Record");
                System.out.println("2. Select All");
                System.out.println("3. Select Employee by id");
                System.out.println("4. Delete Employee");
                System.out.println("5. Update Employee");
                System.out.println("6. Exit");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Enter name, address, salary");
                        // Employee emp=    new Employee(sc.nextLine(),sc.nextLine(),Double.parseDouble(sc.nextLine()));
                        //  databaseService.insertEmployees(emp);
                        databaseService.insertEmployees(new Employee(sc.nextLine(), sc.nextLine()
                                , Double.parseDouble(sc.nextLine())));
                        break;
                    case 2:
                        //System.out.println(choice);
                        databaseService.getAllEmployee();
                        break;
                    case 3:
                        System.out.println("Enter Id of an Employee");
                        databaseService.getEmployeeById(Integer.parseInt(sc.nextLine()));
                        break;

                    case 4:
                        System.out.println("Enter Id of an Employee");
                        databaseService.deleteEmployee(Integer.parseInt(sc.nextLine()));
                    case 5:
                        System.out.println("Enter Id of an Employee");
                        int updateId = Integer.parseInt(sc.nextLine());
                        //databaseService.getEmployeeById(updateId);
                        boolean isFound = databaseService.getEmployeeById(updateId);

                        if (isFound) {
                            System.out.println("Enter name,address, Salary");
                            Employee employee = new Employee(updateId, sc.nextLine(),
                                    sc.nextLine(), Double.parseDouble(sc.nextLine()));
                            databaseService.updateEmployee(employee);
                        }
                        break;
                    case 6:
                        System.out.println("ThankYou, Visit Again");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Incorrect Choice");
                        break;


                }


            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong");

        } finally {
            databaseService.closeConnection();
        }

    }

}
