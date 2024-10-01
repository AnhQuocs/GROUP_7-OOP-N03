package com.example.projectfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button ThongTinSinhVien;

    @FXML
    private Button LichHocThi;

    @FXML
    private Button DiemHP;

    @FXML
    private Button DiemRL;

    @FXML
    private ComboBox HocKy;

    @FXML
    private void onThongTinSinhVien(ActionEvent event)
    {
        try {
            // Tải file FXML cho scene DanhSach
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DanhSach.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) ThongTinSinhVien.getScene().getWindow();

            // Tạo scene mới cho DanhSach
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();

            stage.setTitle("Thông tin sinh viên");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi chuyển.");
        }
    }

    @FXML
    private void onDiemHP(ActionEvent event)
    {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LuaChonDiem.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) DiemHP.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();

            stage.setTitle("Lựa chọn");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi chuyển.");
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
    private void onLichHocThi(ActionEvent event)
    {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DSLichHoc.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) DiemHP.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();

            stage.setTitle("Lịch học / Lịch thi");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi chuyển.");
        }
    }

    @FXML
    private void onDiemRL(ActionEvent event)
    {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DiemRL.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) DiemHP.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();

            stage.setTitle("Điểm Rèn Luyện");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi chuyển.");
        }
    }
}
