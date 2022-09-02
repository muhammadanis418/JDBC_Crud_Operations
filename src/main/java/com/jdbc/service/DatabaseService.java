package com.jdbc.service;

import com.jdbc.model.Employee;
import com.jdbc.util.DatabaseUtil;
import com.jdbc.util.QueryUtil;

import java.sql.*;

public class DatabaseService {

    DatabaseUtil databaseUtil = null;

    private Connection connection;

    public DatabaseService() throws SQLException {
        this.databaseUtil = new DatabaseUtil();
        this.connection = databaseUtil.getConnection();
    }

    public void insertEmployees(Employee employee) throws SQLException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertEmployeeQuery());
            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getEmployeeAddress());
            preparedStatement.setDouble(3, employee.getEmployeeSalary());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Record Inserted Successfully");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    } //--End of insertEmployee Method

    public void getAllEmployee() throws SQLException {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.showAll()); {
            while (resultSet.next()) {

                printEmployee(new Employee(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getDouble("salary")));
            }

        }

    } //---End of getEmployeeMethod

    private void printEmployee(Employee employee) {
        System.out.println("Employee Id: " + employee.getEmployeeId());
        System.out.println("Employee Name:" + employee.getEmployeeName());
        System.out.println("Employee Address:" + employee.getEmployeeAddress());
        System.out.println("Employee Salary:" + employee.getEmployeeSalary());
    } //---End of printEmployeeMethod

    public boolean getEmployeeById(int id) {

        boolean isFound = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueryUtil.selectEmployeeById(id));
            if (resultSet.next()) {
                isFound = true;
                printEmployee(new Employee(resultSet.getInt("id")
                        , resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getDouble("salary")));
            } else {
                System.out.println("Record not found for ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isFound;
    }  //--End of GetEmployeeByIDMethod

    public void deleteEmployee(int id) throws SQLException {
             Statement statement = connection.createStatement(); {
            int rows = statement.executeUpdate(QueryUtil.deleteEmployee(id));
            if (rows > 0) {
                System.out.println("Record Deleted Successfully");
            } else {
                System.out.println("Something went Wrong");
            }
        }
    }//---End of deleteEmployeeMethod

    public void updateEmployee(Employee employee) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(QueryUtil.updateEmployee(employee.getEmployeeId()));
            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getEmployeeAddress());
            preparedStatement.setDouble(3, employee.getEmployeeSalary());
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Record updated Successfully");
            } else {
                System.out.println("Failed to update Record");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
