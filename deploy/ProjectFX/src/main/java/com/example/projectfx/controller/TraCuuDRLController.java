package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.model.DiemHocPhan;
import com.example.projectfx.model.DiemRenLuyen;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TraCuuDRLController {
    @FXML
    private TableView<DiemRenLuyen> tableView;

    @FXML
    private TableColumn<DiemRenLuyen, String> hoVaDemColumn;

    @FXML
    private TableColumn<DiemRenLuyen, String> tenColumn;

    @FXML
    private TableColumn<DiemRenLuyen, String> maSinhVienColumn;

    @FXML
    private TableColumn<DiemRenLuyen, String> khoaDaoTaoColumn;

    @FXML
    private TableColumn<DiemRenLuyen, String> lopHocColumn;

    @FXML
    private TableColumn<DiemRenLuyen, String> diemRenLuyenColumn;

    @FXML
    private SplitMenuButton  chonHocKySplit;

    @FXML
    private TextField maSinhVienField;

    @FXML
    private Button quayLaiButton;

    @FXML
    private Button traCuuButton;

    private ObservableList<DiemRenLuyen> diemList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        hoVaDemColumn.setCellValueFactory(new PropertyValueFactory<>("hoVaDem"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
        maSinhVienColumn.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        khoaDaoTaoColumn.setCellValueFactory(new PropertyValueFactory<>("khoaDaoTao"));
        lopHocColumn.setCellValueFactory(new PropertyValueFactory<>("lopHoc"));
        diemRenLuyenColumn.setCellValueFactory(new PropertyValueFactory<>("diemRenLuyen"));

        for (MenuItem item : chonHocKySplit.getItems()) {
            item.setOnAction(event -> chonHocKySplit.setText(item.getText()));
        }
    }

    @FXML
    public void handleTraCuuButtonAction() {
        String maSinhVien = maSinhVienField.getText().trim();
        if (!maSinhVien.isEmpty()) {
            loadData(maSinhVien);
        }
    }

    private void loadData(String maSinhVien) {
        String query = "SELECT * FROM diemRL WHERE ma_sinh_vien = ?";
        diemList.clear();
        boolean hasData = false;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, maSinhVien);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                hasData = true;
                DiemRenLuyen diemRenLuyen = new DiemRenLuyen(
                        resultSet.getString("hoVaDem"),
                        resultSet.getString("ten"),
                        resultSet.getString("ma_sinh_vien"),
                        resultSet.getString("khoaDaoTao"),
                        resultSet.getString("lopHoc"),
                        resultSet.getString("diemRenLuyen")
                );
                diemList.add(diemRenLuyen);
            }

            if (hasData) {
                tableView.setItems(diemList);
            } else {
                showAlert("Mã sinh viên không tồn tại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Có lỗi xảy ra khi truy vấn dữ liệu!");
        }
    }

    @FXML
    public void onQuayLai(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DiemRL.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLaiButton.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Điểm rèn luyện");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi quay lại.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
