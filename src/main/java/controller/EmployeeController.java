package controller;

import entity.Employee;
import model.EmployeeModel;

import java.util.Scanner;

public class EmployeeController {
    private EmployeeModel employeeModel = new EmployeeModel();

    public void register() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Vui lòng nhập các thông tin sau: ");
            System.out.println("Nhập tên: ");
            String name = scanner.nextLine();
            System.out.println("Nhập địa chỉ: ");
            String address = scanner.nextLine();
            System.out.println("Nhập email: ");
            String email = scanner.nextLine();
            System.out.println("Nhập tài khoản: ");
            String username = scanner.nextLine();
            System.out.println("Nhập mật khẩu: ");
            String password = scanner.nextLine();
            if (!employeeModel.checkExistAccount(username)) {
                Employee employee = new Employee(name, address, email, username, password);
                System.out.println("-----------------------------------Xác nhận tài khoản-----------------------------------");
                System.out.printf("%15s | %15s | %15s | %15s | %15s\n", "Tên", "Địa chỉ", "Email", "Tài khoản", "Mật khẩu");
                System.out.printf("%15s | %15s | %15s | %15s | %15s\n",
                        employee.getName(), employee.getAddress(), employee.getEmail(), employee.getUsername(), employee.getPassword());
                System.out.println("----------------------------------------------------------------------------------------");
                System.out.println("Bạn có chắc chắn muốn đăng ký tài khoản với những thông tin trên không ? (Y/N)");
                while (true) {
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "Y":
                        case "y":
                            employeeModel.register(employee);
                            System.out.println("Đăng ký thành công!\n");
                            break;
                        case "N":
                        case "n":
                            return;
                        default:
                            System.out.println("Lựa chọn của bạn k hợp lệ. Vui lòng nhâp Y hoặc N.");
                            break;
                    }
                    if (choice.equals("y") || choice.equals("Y")) {
                        return;
                    }
                }
            }
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tài khoản: ");
        String username = scanner.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        if (employeeModel.login(username, password) != null) {
            Employee employee = employeeModel.login(username, password);
            System.out.println("-----------------------------------Thông tin tài khoản-----------------------------------");
            System.out.printf("%15s | %15s | %15s | %15s | %15s | %30s | %30s | %15s\n", "Tên", "Địa chỉ", "Email", "Tài khoản", "Mật khẩu", "Ngày tạo", "Ngày cập nhật", "Trạng thái");
            System.out.printf("%15s | %15s | %15s | %15s | %15s | %30s | %30s | %15s\n\n",
                    employee.getName(), employee.getAddress(), employee.getEmail(), employee.getUsername(), employee.getPassword(), employee.getCreatedAt(), employee.getUpdatedAt(), employee.getStatus());
        } else {
            System.out.println("Sai thông tin tài khoản");
        }
    }
}
