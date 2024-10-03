package com.example.projectfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LuaChonDiemController {
    @FXML
    private Button NhapDiem;

    @FXML
    private Button TraCuuDiem;

    @FXML
    private Button quayLaiDS;

    @FXML
    private void onNhapDiem(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DiemHP.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) NhapDiem.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Nhập điểm");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi quay lại danh sách.");
        }
    }

    @FXML
    private void onTraCuuDiem(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/TraCuu.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) TraCuuDiem.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Tra cứu điểm");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void onQuayLaiDS(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/Main.fxml"));
            Parent danhSachView = loader.load();

            Stage stage = (Stage) quayLaiDS.getScene().getWindow();

            Scene scene = new Scene(danhSachView);

            stage.setTitle("Quản lý sinh viên");

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
