package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.database.SinhVienDAO;
import com.example.projectfx.model.LichHoc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class LichHocController {
    @FXML
    private SplitMenuButton hocKySplit;

    @FXML
    private SplitMenuButton monHocSplit;

    @FXML
    private DatePicker ngayHocPicker;

    @FXML
    private SplitMenuButton caHocSplit;

    @FXML
    private SplitMenuButton phongHocSplit;

    @FXML
    private TextField soLuongSinhVienTextField;

    @FXML
    private Button quayLaiBtn;

    @FXML
    private Button lamMoiBtn;

    @FXML
    private Button luuLichHocBtn;

    @FXML
    private Button xoaLichHocBtn;

    @FXML
    public void initialize() {
        for (MenuItem item : hocKySplit.getItems()) {
            item.setOnAction(event -> hocKySplit.setText(item.getText()));
        }

        for (MenuItem item : monHocSplit.getItems()) {
            item.setOnAction(event -> monHocSplit.setText(item.getText()));
        }

        for (MenuItem item : phongHocSplit.getItems()) {
            item.setOnAction(event -> phongHocSplit.setText(item.getText()));
        }

        for (MenuItem item : caHocSplit.getItems()) {
            item.setOnAction(event -> caHocSplit.setText(item.getText()));
        }

        SinhVienDAO dao = new SinhVienDAO();
        List<LichHoc> lichHocList = dao.getAllLichHoc();

        soLuongSinhVienTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                soLuongSinhVienTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }


    @FXML
    public void hienThiThongTinLichHoc(LichHoc lichHoc) {
        hocKySplit.setText("");
        monHocSplit.setText("");
        caHocSplit.setText("");
        phongHocSplit.setText("");
        soLuongSinhVienTextField.clear();

        hocKySplit.setText(lichHoc.getHocKy());
        monHocSplit.setText(lichHoc.getMonHoc());
        caHocSplit.setText(lichHoc.getCaHoc());
        phongHocSplit.setText(lichHoc.getPhongHoc());
        soLuongSinhVienTextField.setText(String.valueOf(lichHoc.getSoLuongSinhVien()));

        String ngayValue = lichHoc.getNgayHoc(); // Đây là chuỗi từ LichHoc
        try {
            ngayHocPicker.setValue(LocalDate.parse(ngayValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (DateTimeParseException e) {
            // Nếu ngày không hợp lệ, bỏ qua hoặc đặt giá trị mặc định
            ngayHocPicker.setValue(null);
            System.out.println("Ngày sinh không hợp lệ: " + ngayValue);
        }
    }

    @FXML
    public void onQuayLai(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DSLichHoc.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLaiBtn.getScene().getWindow();

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

    @FXML
    public void onLamMoi(ActionEvent event) {
        hocKySplit.setText("");
        monHocSplit.setText("");
        caHocSplit.setText("");
        soLuongSinhVienTextField.clear();
        ngayHocPicker.setValue(null);
        phongHocSplit.setText("");
    }

    @FXML
    public void onLuuLichHoc(ActionEvent event) {
        String hocKy = hocKySplit.getText();
        String monHoc = monHocSplit.getText();

        LocalDate localDate = ngayHocPicker.getValue(); // Lấy giá trị từ DatePicker
        String ngayHoc = null; // Khởi tạo biến cho ngày học

        // Kiểm tra ngày học
        if (localDate != null) {
            // Chuyển đổi ngày sang định dạng yyyy-MM-dd
            ngayHoc = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            showAlert("Vui lòng chọn ngày học.");
            return;
        }

        String caHoc = caHocSplit.getText();
        String phongHoc = phongHocSplit.getText();
        Integer soLuongSinhVien = null;
        try {
            soLuongSinhVien = Integer.parseInt(soLuongSinhVienTextField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert("Lỗi: Không thể chuyển đổi số lượng sinh viên");
            return;
        }

        // Lưu vào cơ sở dữ liệu
        try (Connection connection = DataBaseConnection.getConnection()) {
            // Kiểm tra xem lịch học đã tồn tại chưa
            String checkQuery = "SELECT * FROM lichHoc WHERE monHoc = ? AND ngayHoc = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, monHoc);
            checkStmt.setString(2, ngayHoc);  // Thêm dòng này để kiểm tra học kỳ
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                // Nếu lịch học đã tồn tại, xóa lịch học cũ
                String deleteQuery = "DELETE FROM lichHoc WHERE monHoc = ? AND ngayHoc = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
                deleteStmt.setString(1, monHoc);
                deleteStmt.setString(2, ngayHoc);  // Thêm học kỳ vào lệnh DELETE
                deleteStmt.executeUpdate();
            }

            // Thêm lịch học mới
            String query = "INSERT INTO lichHoc (hocKy, monHoc, ngayHoc, caHoc, phongHoc, soLuongSinhVien) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, hocKy);
            stmt.setString(2, monHoc);
            stmt.setString(3, ngayHoc);
            stmt.setString(4, caHoc);
            stmt.setString(5, phongHoc);
            stmt.setInt(6, soLuongSinhVien);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlertINFO("Lưu lịch học thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi lưu thông tin vào cơ sở dữ liệu.");
        }

        hocKySplit.setText("");
        monHocSplit.setText("");
        caHocSplit.setText("");
        soLuongSinhVienTextField.clear();
        ngayHocPicker.setValue(null);
        phongHocSplit.setText("");
    }


    @FXML
    public void onXoaLichHoc(ActionEvent event) {
        String monHoc = monHocSplit.getText();
        if (monHoc.isEmpty()) {
            showAlert("Vui lòng chọn môn học để xóa.");
            return;
        }

        String caHoc = caHocSplit.getText();
        if (caHoc.isEmpty()) {
            showAlert("Vui lòng chọn ca học để xóa.");
            return;
        }

        deleteLichHoc(monHoc, caHoc);

        hocKySplit.setText("");
        monHocSplit.setText("");
        caHocSplit.setText("");
        soLuongSinhVienTextField.clear();
        ngayHocPicker.setValue(null);
        phongHocSplit.setText("");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertINFO(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void deleteLichHoc(String monHoc, String caHoc) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "DELETE FROM lichHoc WHERE monHoc = ? AND caHoc = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, monHoc);
            stmt.setString(2, caHoc);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Xóa lịch học thành công!");
                alert.showAndWait();
            } else {
                showAlert("Không tìm thấy lịch học để xóa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Có lỗi xảy ra trong quá trình xóa lịch học.");
        }
    }
}