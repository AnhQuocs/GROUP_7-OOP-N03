package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
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
import java.sql.*;

public class DanhSachController {
    @FXML
    private TableView<SinhVien> tableView;

    @FXML
    private TableColumn<SinhVien, String> hoVaDemColumn;

    @FXML
    private TableColumn<SinhVien, String> tenColumn;

    @FXML
    private TableColumn<SinhVien, String> maSinhVienColumn;

    @FXML
    private TableColumn<SinhVien, String> gTinhColumn;

    @FXML
    private TableColumn<SinhVien, String> dateSinhColumn;

    @FXML
    private TableColumn<SinhVien, String> noiSinhColumn;

    @FXML
    private TableColumn<SinhVien, String> emailColumn;

    @FXML
    private TableColumn<SinhVien, String> soDienThoaiColumn;

    @FXML
    private TableColumn<SinhVien, String> khoaDTColumn;

    @FXML
    private TableColumn<SinhVien, String> nganhHocColumn;

    @FXML
    private TableColumn<SinhVien, String> lopHocColumn;

    @FXML
    private Button themSinhVienButton;

    private ObservableList<SinhVien> data;

    @FXML
    private Button suaXoaButton;

    @FXML
    private Button quayLaiDS;

    @FXML
    private TextField timKiemField;

    private SinhVien selectedSinhVien;

    @FXML
    public void initialize() {
        // Thiết lập các cột dữ liệu
        hoVaDemColumn.setCellValueFactory(new PropertyValueFactory<>("hoVaDem"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
        maSinhVienColumn.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        gTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gTinh"));
        dateSinhColumn.setCellValueFactory(new PropertyValueFactory<>("dateSinh"));
        noiSinhColumn.setCellValueFactory(new PropertyValueFactory<>("noiSinh"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        khoaDTColumn.setCellValueFactory(new PropertyValueFactory<>("khoaDT"));
        nganhHocColumn.setCellValueFactory(new PropertyValueFactory<>("nganhHoc"));
        lopHocColumn.setCellValueFactory(new PropertyValueFactory<>("lopHoc"));

        // Khởi tạo danh sách sinh viên
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        loadData();

        // Sự kiện thêm sinh viên
        themSinhVienButton.setOnAction(event -> moNhapThongTin());

        // Khởi tạo button
        suaXoaButton.setDisable(true);  // Disable button ban đầu

        // Thiết lập row factory cho tableView
        tableView.setRowFactory(tv -> {
            TableRow<SinhVien> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    selectedSinhVien = row.getItem();
                    suaXoaButton.setDisable(false);  // Kích hoạt button
                } else {
                    selectedSinhVien = null;
                    suaXoaButton.setDisable(true);  // Disable button nếu không có hàng nào được chọn
                }
            });
            return row;
        });
    }

    // Tải dữ liệu từ cơ sở dữ liệu
    private void loadData() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM sinhvien";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String hoVaDem = resultSet.getString("ho_va_dem");
                String ten = resultSet.getString("ten");
                String maSinhVien = resultSet.getString("ma_sinh_vien");
                String gTinh = resultSet.getString("gtinh");
                String nganhHoc = resultSet.getString("nganh_hoc");
                String khoaDT = resultSet.getString("khoa_dao_tao");
                String lopHoc = resultSet.getString("lop_hoc");
                String soDienThoai = resultSet.getString("so_dien_thoai");
                String dateSinh = resultSet.getString("date_sinh");
                String noiSinh = resultSet.getString("noi_sinh");
                String email = resultSet.getString("email");

                // Tạo đối tượng sinh viên và thêm vào danh sách
                SinhVien sinhVien = new SinhVien(hoVaDem, ten, maSinhVien, gTinh, dateSinh, noiSinh, email, soDienThoai, khoaDT, nganhHoc, lopHoc);
                data.add(sinhVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Mở scene để thêm sinh viên mới
    @FXML
    private void moNhapThongTin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/NhapThongTin.fxml"));
            Parent root = loader.load();

            // Tạo scene mới
            Scene scene = new Scene(root);

            Stage stage = (Stage) themSinhVienButton.getScene().getWindow();

            stage.setTitle("Nhập thông tin sinh viên");

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Mở scene để sửa thông tin sinh viên
    private void moSuaThongTin(SinhVien sinhVien) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/NhapThongTin.fxml"));
            Parent root = loader.load();

            // Lấy controller của NhapThongTin
            ThongTinController controller = loader.getController();
            controller.hienThiThongTinSinhVien(sinhVien);  // Gửi thông tin sinh viên

            // Chuyển sang scene NhapThongTin
            Stage stage = (Stage) tableView.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setTitle("Sửa thông tin sinh viên");

            // Áp dụng CSS nếu cần
            scene.getStylesheets().add(getClass().getResource("/com/example/css/style.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sự kiện khi button "Sửa/Xóa" được nhấn
    @FXML
    private void onSuaXoaButtonClick() {
        if (selectedSinhVien != null) {
            moSuaThongTin(selectedSinhVien);
        }
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

    @FXML
    public void onSearch(ActionEvent event) {
        String maSV = timKiemField.getText().trim();  // Lấy mã sinh viên từ timKiemField

        // Xóa dữ liệu cũ trong TableView
        data.clear();

        if (maSV.isEmpty()) {
            // Nếu mã sinh viên trống, hiển thị tất cả sinh viên
            loadData();
            return;
        }

        // Truy vấn sinh viên theo mã
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT * FROM sinhvien WHERE ma_sinh_vien = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, maSV);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                // Lấy thông tin sinh viên từ cơ sở dữ liệu
                String hoVaDem = resultSet.getString("ho_va_dem");
                String ten = resultSet.getString("ten");
                String maSinhVien = resultSet.getString("ma_sinh_vien");
                String gTinh = resultSet.getString("gtinh");
                String nganhHoc = resultSet.getString("nganh_hoc");
                String khoaDT = resultSet.getString("khoa_dao_tao");
                String lopHoc = resultSet.getString("lop_hoc");
                String soDienThoai = resultSet.getString("so_dien_thoai");
                String dateSinh = resultSet.getString("date_sinh");
                String noiSinh = resultSet.getString("noi_sinh");
                String email = resultSet.getString("email");

                // Tạo đối tượng sinh viên và thêm vào danh sách
                SinhVien sinhVien = new SinhVien(hoVaDem, ten, maSinhVien, gTinh, dateSinh, noiSinh, email, soDienThoai, khoaDT, nganhHoc, lopHoc);
                data.add(sinhVien);
            } else {
                // Nếu không tìm thấy sinh viên, hiển thị thông báo lỗi
                showAlertERROR("Mã sinh viên " + maSV + " không tồn tại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlertERROR("Đã xảy ra lỗi khi kiểm tra mã sinh viên!");
        }
    }



    private void showAlertERROR(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
