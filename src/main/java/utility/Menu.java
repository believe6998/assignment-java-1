package utility;

import controller.EmployeeController;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public void generateMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        EmployeeController studentController = new EmployeeController();
        System.out.println("-------------Menu----------------");
        System.out.println("1. Đăng ký.");
        System.out.println("2. Đăng nhập.");
        System.out.println("3. Thoát.");
        System.out.println("---------------------------------");
        while (true) {
            System.out.println("Nhập lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    studentController.register();
                    break;
                case "2":
                    studentController.login();
                    break;
                case "3":
                    System.out.println("Thoát");
                    break;
                default:
                    System.out.println("Lựa chọn sai, vui lòng nhập số trong khoảng từ 1 đến 3.");
                    break;
            }
            if (choice.equals("3")) {
                System.out.println("Hẹn gặp lại.");
                break;
            }
        }
    }
}
