package com.jdbc.util;

public class QueryUtil {
    public static String insertEmployeeQuery(){
  return  "Insert Into employee_info(name,address,salary) values (?,?,?)";
}
   public static String showAll(){
        return "Select * from employee_info";
    }

    public static String selectEmployeeById(int employeeId){
        return "Select * from employee_info where  id = " +employeeId;
    }

    public static String deleteEmployee(int employeeId){
        return "Delete from employee_info where id = "+employeeId;
    }
public static String updateEmployee(int employeeId){
        return "Update employee_info SET name = ?,address = ?,salary = ? where id = "+employeeId;
}
}
