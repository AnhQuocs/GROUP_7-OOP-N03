package com.example.projectfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private String ACCOUNT;
    private String PASSWORD;


    // Phương thức để đọc tài khoản và mật khẩu từ file
    private void loadCredentialsFromFile() throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/com/example/datafile/login.txt");

        if (inputStream == null) {
            showAlert("Không tìm thấy file login.txt");
        }
        else
        {
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(", ");
                if (data.length == 2) {
                    ACCOUNT = data[0];
                    PASSWORD = data[1];
                }
            }
            scanner.close();
        }
    }

    @FXML
    private void handleLogin() throws IOException {
        loadCredentialsFromFile();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (ACCOUNT.equals(username) && PASSWORD.equals(password)) {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đăng nhập thành công!");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/Main.fxml"));
                Parent root = loader.load();

                // Lấy Stage hiện tại từ nút đăng nhập
                Stage stage = (Stage) loginButton.getScene().getWindow();

                // Tạo Scene mới và thiết lập cho Stage
                Scene scene = new Scene(root, 916, 600);
                stage.setScene(scene);
                stage.show();

                stage.setTitle("Quản lý sinh viên");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Sai tài khoản hoặc mật khẩu!");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
