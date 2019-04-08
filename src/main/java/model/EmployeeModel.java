package model;

import entity.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeModel {
    private Connection connection;

    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection =
                    DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/human_resource?user=root&password=");
        }
    }

    public boolean register(Employee emp) {
        try {
            initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into employees (username, name, address, email, password) values (?, ?, ? ,?, ?)");
            preparedStatement.setString(1, emp.getUsername());
            preparedStatement.setString(2, emp.getName());
            preparedStatement.setString(3, emp.getAddress());
            preparedStatement.setString(4, emp.getEmail());
            preparedStatement.setString(5, emp.getPassword());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return false;
    }

    public boolean checkExistAccount(String account) {
        boolean valid = true;
        try {
            initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employees where username = ?");
            preparedStatement.setString(1, account);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                valid = false;
            } else {
                System.out.println("Tài khoản đã tồn tại. Vui lòng đăng ký lại");
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return valid;
    }

    public Employee login(String account, String password) {
        try {
            ArrayList<Employee> list = new ArrayList<>();
            initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employees where username = ?");
            preparedStatement.setString(1, account);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String rsAccount = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String email = resultSet.getString(4);
                String rsPassword = resultSet.getString(5);
                Timestamp createdAt = resultSet.getTimestamp(6);
                Timestamp updatedAt = resultSet.getTimestamp(7);
                int status = resultSet.getInt(8);
                Employee employee = new Employee(rsAccount, name, address, email, rsPassword, createdAt, updatedAt, status);
                if (password.equals(rsPassword)){
                    return employee;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return null;
    }
}
