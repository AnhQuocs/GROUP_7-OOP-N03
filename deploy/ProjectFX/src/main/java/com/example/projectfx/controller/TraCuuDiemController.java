package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.model.DiemHocPhan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class TraCuuDiemController {

    @FXML
    private TextField maSinhVienTextField;
    @FXML
    private Button traCuuButton;

    @FXML
    private Button quaylai;

    @FXML
    private SplitMenuButton chonHocKy;

    @FXML
    private TableView<DiemHocPhan> diemTableView;

    @FXML
    private TableColumn<DiemHocPhan, String> hoVaDemColumn;
    @FXML
    private TableColumn<DiemHocPhan, String> tenColumn;
    @FXML
    private TableColumn<DiemHocPhan, String> maSinhVienColumn;
    @FXML
    private TableColumn<DiemHocPhan, String> monHocColumn;
    @FXML
    private TableColumn<DiemHocPhan, Double> cc1Column;
    @FXML
    private TableColumn<DiemHocPhan, Double> cc2Column;
    @FXML
    private TableColumn<DiemHocPhan, Double> baiTapColumn;
    @FXML
    private TableColumn<DiemHocPhan, Double> thucHanhColumn;
    @FXML
    private TableColumn<DiemHocPhan, Double> giuaKyColumn;
    @FXML
    private TableColumn<DiemHocPhan, Double> cuoiKyColumn;
    @FXML
    private TableColumn<DiemHocPhan, Double> diemHe10Column;
    @FXML
    private TableColumn<DiemHocPhan, Double> diemHe4Column;

    private ObservableList<DiemHocPhan> diemList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        hoVaDemColumn.setCellValueFactory(new PropertyValueFactory<>("hoVaDem"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
        maSinhVienColumn.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        monHocColumn.setCellValueFactory(new PropertyValueFactory<>("tenHocPhan"));
        cc1Column.setCellValueFactory(new PropertyValueFactory<>("cc1"));
        cc2Column.setCellValueFactory(new PropertyValueFactory<>("cc2"));
        baiTapColumn.setCellValueFactory(new PropertyValueFactory<>("baiTap"));
        thucHanhColumn.setCellValueFactory(new PropertyValueFactory<>("thucHanh"));
        giuaKyColumn.setCellValueFactory(new PropertyValueFactory<>("giuaKy"));
        cuoiKyColumn.setCellValueFactory(new PropertyValueFactory<>("cuoiKy"));
        diemHe10Column.setCellValueFactory(new PropertyValueFactory<>("diemHe10"));
        diemHe4Column.setCellValueFactory(new PropertyValueFactory<>("diemHe4"));

        for (MenuItem item : chonHocKy.getItems()) {
            item.setOnAction(event -> chonHocKy.setText(item.getText()));
        }
    }

    @FXML
    public void handleTraCuuButtonAction() {
        String maSinhVien = maSinhVienTextField.getText().trim();
        if (!maSinhVien.isEmpty()) {
            loadData(maSinhVien);
        }
    }

    private void loadData(String maSinhVien) {
        String query = "SELECT * FROM diemHP WHERE ma_sinh_vien = ?";
        diemList.clear();
        boolean hasData = false;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, maSinhVien);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                hasData = true;
                DiemHocPhan diemHocPhan = new DiemHocPhan(
                        resultSet.getString("ho_va_dem"),
                        resultSet.getString("ten"),
                        resultSet.getString("ma_sinh_vien"),
                        resultSet.getString("tenHocPhan"),
                        resultSet.getFloat("cc1"),
                        resultSet.getFloat("cc2"),
                        resultSet.getFloat("baiTap"),
                        resultSet.getFloat("thucHanh"),
                        resultSet.getFloat("giuaKy"),
                        resultSet.getFloat("cuoiKy"),
                        resultSet.getFloat("diemHe10"),
                        resultSet.getFloat("diemHe4")
                );
                diemList.add(diemHocPhan);
            }

            if (hasData) {
                diemTableView.setItems(diemList);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LuaChonDiem.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quaylai.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Lựa chọn");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi quay lại danh sách.");
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
