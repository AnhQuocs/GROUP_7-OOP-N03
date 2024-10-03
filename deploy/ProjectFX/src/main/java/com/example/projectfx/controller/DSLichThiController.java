package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.model.LichThi;
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

public class DSLichThiController {
    @FXML
    private TableView<LichThi> tableView;

    @FXML
    private TableColumn<LichThi, String> hocKyColumn;

    @FXML
    private TableColumn<LichThi, String> monThiColumn;

    @FXML
    private TableColumn<LichThi, String> ngayThiColumn;

    @FXML
    private TableColumn<LichThi, String> caThiColumn;

    @FXML
    private TableColumn<LichThi, String> phongThiColumn;

    @FXML
    private TableColumn<LichThi, String> thoiGianThiColumn;

    @FXML
    private TableColumn<LichThi, String> kyThiColumn;

    @FXML
    private TableColumn<LichThi, String> hinhThucColumn;

    @FXML
    private Button themLichThiButton;

    @FXML
    private Button suaXoaLichButton;

    @FXML
    private Button quayLaiButton;

    private ObservableList<LichThi> data;

    private LichThi selectedLichThi;

    @FXML
    public void initialize() {
        hocKyColumn.setCellValueFactory(new PropertyValueFactory<>("hocKy"));
        monThiColumn.setCellValueFactory(new PropertyValueFactory<>("monThi"));
        ngayThiColumn.setCellValueFactory(new PropertyValueFactory<>("ngayThi"));
        caThiColumn.setCellValueFactory(new PropertyValueFactory<>("caThi"));
        phongThiColumn.setCellValueFactory(new PropertyValueFactory<>("phongThi"));
        thoiGianThiColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGianThi"));
        kyThiColumn.setCellValueFactory(new PropertyValueFactory<>("kyThi"));
        hinhThucColumn.setCellValueFactory(new PropertyValueFactory<>("hinhThuc"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        loadData();

        themLichThiButton.setOnAction(event -> moThemLichThi());

        suaXoaLichButton.setDisable(true);

        tableView.setRowFactory(tv -> {
            TableRow<LichThi> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    selectedLichThi = row.getItem();
                    suaXoaLichButton.setDisable(false);  // Kích hoạt button
                } else {
                    selectedLichThi = null;
                    suaXoaLichButton.setDisable(true);
                }
            });
            return row;
        });

        suaXoaLichButton.setOnAction(event -> moSuaLichThi(selectedLichThi));
    }

    private void loadData() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM lichThi";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String hocKy = resultSet.getString("hocKy");
                String monThi = resultSet.getString("monThi");
                String ngayThi = resultSet.getString("ngayThi");
                String caThi = resultSet.getString("caThi");
                String phongThi = resultSet.getString("phongThi");
                String thoiGianThi = resultSet.getString("thoiGianThi");
                String kyThi = resultSet.getString("kyThi");
                String hinhThuc = resultSet.getString("hinhThuc");

                // Tạo đối tượng sinh viên và thêm vào danh sách
                LichThi lichThi = new LichThi(hocKy, monThi, ngayThi, caThi, phongThi, thoiGianThi, kyThi, hinhThuc);
                data.add(lichThi);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @FXML
    private void moThemLichThi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LichThi.fxml"));
            Parent root = loader.load();

            // Tạo scene mới
            Scene scene = new Scene(root);

            Stage stage = (Stage) themLichThiButton.getScene().getWindow();
            stage.setScene(scene);

            stage.setTitle("Thêm Lịch Thi");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moSuaLichThi(LichThi lichThi) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LichThi.fxml"));
            Parent root = loader.load();

            // Lấy controller
            LichThiController controller = loader.getController();
            controller.hienThiThongTinLichThi(lichThi);  // Gửi thông tin

            // Chuyển sang scene
            Stage stage = (Stage) tableView.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.setTitle("Sửa Lịch Thi");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onQuayLai(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DSLichHoc.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLaiButton.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Lịch Học");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi quay lại.");
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
