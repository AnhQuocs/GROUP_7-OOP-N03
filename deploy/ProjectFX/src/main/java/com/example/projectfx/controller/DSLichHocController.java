package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.model.LichHoc;
import com.example.projectfx.model.SinhVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DSLichHocController {
    @FXML
    private TableView<LichHoc> tableView;

    @FXML
    private TableColumn<LichHoc, String> hocKyColumn;

    @FXML
    private TableColumn<LichHoc, String> monHocColumn;

    @FXML
    private TableColumn<LichHoc, String> ngayHocColumn;

    @FXML
    private TableColumn<LichHoc, String> caHocColumn;

    @FXML
    private TableColumn<LichHoc, String> phongHocColumn;

    @FXML
    private TableColumn<LichHoc, Integer> soLuongSinhVienColumn;

    @FXML
    private Button quayLai;

    @FXML
    private Button themLich;

    @FXML
    private Button suaXoaLich;

    @FXML
    private Button lichThi;

    private ObservableList<LichHoc> data;

    private LichHoc selectedLichHoc;

    @FXML
    public void initialize() {
        hocKyColumn.setCellValueFactory(new PropertyValueFactory<>("hocKy"));
        monHocColumn.setCellValueFactory(new PropertyValueFactory<>("monHoc"));
        ngayHocColumn.setCellValueFactory(new PropertyValueFactory<>("ngayHoc"));
        caHocColumn.setCellValueFactory(new PropertyValueFactory<>("caHoc"));
        phongHocColumn.setCellValueFactory(new PropertyValueFactory<>("phongHoc"));
        soLuongSinhVienColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongSinhVien"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        loadData();

        themLich.setOnAction(event -> moThemLichHoc());

        suaXoaLich.setDisable(true);

        tableView.setRowFactory(tv -> {
            TableRow<LichHoc> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    selectedLichHoc = row.getItem();
                    suaXoaLich.setDisable(false);  // Kích hoạt button
                } else {
                    selectedLichHoc = null;
                    suaXoaLich.setDisable(true);
                }
            });
            return row;
        });

        suaXoaLich.setOnAction(event -> moSuaLichHoc(selectedLichHoc));

    }

    private void loadData() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM lichHoc";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String hocKy = resultSet.getString("hocKy");
                String monHoc = resultSet.getString("monHoc");
                String ngayHoc = resultSet.getString("ngayHoc");
                String caHoc = resultSet.getString("caHoc");
                String phongHoc = resultSet.getString("phongHoc");
                Integer soLuongSinhVien = resultSet.getInt("soLuongSinhVien");

                // Tạo đối tượng sinh viên và thêm vào danh sách
                LichHoc lichHoc = new LichHoc(hocKy, monHoc,ngayHoc, caHoc, phongHoc, soLuongSinhVien);
                data.add(lichHoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @FXML
    private void moThemLichHoc() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LichHoc.fxml"));
            Parent root = loader.load();

            // Tạo scene mới
            Scene scene = new Scene(root);

            Stage stage = (Stage) themLich.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moSuaLichHoc(LichHoc lichHoc) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LichHoc.fxml"));
            Parent root = loader.load();

            // Lấy controller của NhapThongTin
            LichHocController controller = loader.getController();
            controller.hienThiThongTinLichHoc(lichHoc);  // Gửi thông tin sinh viên

            // Chuyển sang scene NhapThongTin
            Stage stage = (Stage) tableView.getScene().getWindow();
            Scene scene = new Scene(root);

            // Áp dụng CSS nếu cần
            scene.getStylesheets().add(getClass().getResource("/com/example/css/style.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onQuayLai(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/Main.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLai.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi quay lại.");
        }
    }

    @FXML
    public void onLichThi(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DSLichThi.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) lichThi.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
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
}
