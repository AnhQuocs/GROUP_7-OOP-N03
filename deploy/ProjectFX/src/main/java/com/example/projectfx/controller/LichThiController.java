package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.database.SinhVienDAO;
import com.example.projectfx.model.LichThi;
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

public class LichThiController {
    @FXML
    private SplitMenuButton hocKySplit;

    @FXML
    private SplitMenuButton monThiSplit;

    @FXML
    private DatePicker ngayThiPicker;

    @FXML
    private SplitMenuButton caThiSplit;

    @FXML
    private SplitMenuButton phongThiSplit;

    @FXML
    private SplitMenuButton thoiGianThiSplit;

    @FXML
    private SplitMenuButton kyThiSplit;

    @FXML
    private SplitMenuButton hinhThucSplit;

    @FXML
    private Button quayLaiBtn;

    @FXML
    private Button lamMoiBtn;

    @FXML
    private Button luuLichThiBtn;

    @FXML
    private Button xoaLichThiBtn;

    @FXML
    public void initialize() {
        for (MenuItem item : hocKySplit.getItems()) {
            item.setOnAction(event -> hocKySplit.setText(item.getText()));
        }

        for (MenuItem item : monThiSplit.getItems()) {
            item.setOnAction(event -> monThiSplit.setText(item.getText()));
        }

        for (MenuItem item : phongThiSplit.getItems()) {
            item.setOnAction(event -> phongThiSplit.setText(item.getText()));
        }

        for (MenuItem item : caThiSplit.getItems()) {
            item.setOnAction(event -> caThiSplit.setText(item.getText()));
        }

        for (MenuItem item : thoiGianThiSplit.getItems()) {
            item.setOnAction(event -> thoiGianThiSplit.setText(item.getText()));
        }

        for (MenuItem item : kyThiSplit.getItems()) {
            item.setOnAction(event -> kyThiSplit.setText(item.getText()));
        }

        for (MenuItem item : hinhThucSplit.getItems()) {
            item.setOnAction(event -> hinhThucSplit.setText(item.getText()));
        }
    }

    @FXML
    public void hienThiThongTinLichThi (LichThi lichThi) {
        hocKySplit.setText("");
        monThiSplit.setText("");
        phongThiSplit.setText("");
        caThiSplit.setText("");
        thoiGianThiSplit.setText("");
        kyThiSplit.setText("");
        hinhThucSplit.setText("");

        hocKySplit.setText(lichThi.getHocKy());
        monThiSplit.setText(lichThi.getMonThi());
        phongThiSplit.setText(lichThi.getPhongThi());
        caThiSplit.setText(lichThi.getCaThi());
        thoiGianThiSplit.setText(lichThi.getThoiGianThi());
        kyThiSplit.setText(lichThi.getKyThi());
        hinhThucSplit.setText(lichThi.getHinhThuc());

        String ngayValue = lichThi.getNgayThi(); // Đây là chuỗi từ LichThi
        try {
            ngayThiPicker.setValue(LocalDate.parse(ngayValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (DateTimeParseException e) {
            // Nếu ngày không hợp lệ, bỏ qua hoặc đặt giá trị mặc định
            ngayThiPicker.setValue(null);
            System.out.println("Ngày sinh không hợp lệ: " + ngayValue);
        }
    }

    @FXML
    public void onQuayLai(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DSLichThi.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLaiBtn.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Lịch Thi");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi quay lại.");
        }

        SinhVienDAO dao = new SinhVienDAO();
        List<LichThi> lichHocList = dao.getAllLichThi();
    }

    @FXML
    public void onLamMoi(ActionEvent event) {
        hocKySplit.setText("");
        monThiSplit.setText("");
        phongThiSplit.setText("");
        caThiSplit.setText("");
        thoiGianThiSplit.setText("");
        ngayThiPicker.setValue(null);
        kyThiSplit.setText("");
        hinhThucSplit.setText("");
    }

    @FXML
    public void onLuuLichThi(ActionEvent event) {
        String hocKy = hocKySplit.getText();
        String monThi = monThiSplit.getText();

        LocalDate localDate = ngayThiPicker.getValue(); // Lấy giá trị từ DatePicker
        String ngayThi = null; // Khởi tạo biến cho ngày học

        // Kiểm tra ngày thi
        if (localDate != null) {
            // Chuyển đổi ngày sang định dạng yyyy-MM-dd
            ngayThi = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            showAlert("Vui lòng chọn ngày thi.");
            return;
        }

        String caThi = caThiSplit.getText();
        String phongThi = phongThiSplit.getText();
        String thoiGianThi = thoiGianThiSplit.getText();
        String kyThi = kyThiSplit.getText();
        String hinhThuc = hinhThucSplit.getText();

        // Lưu vào cơ sở dữ liệu
        try (Connection connection = DataBaseConnection.getConnection()) {
            // Kiểm tra xem lịch thi đã tồn tại chưa
            String checkQuery = "SELECT * FROM lichThi WHERE monThi = ? AND hocKy = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, monThi);
            checkStmt.setString(2, hocKy);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                // Nếu lịch thi đã tồn tại, xóa lịch thi cũ
                String deleteQuery = "DELETE FROM lichThi WHERE monThi = ? AND hocKy = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
                deleteStmt.setString(1, monThi);
                deleteStmt.setString(2, hocKy);
                deleteStmt.executeUpdate();
            }

            // Thêm lịch thi mới
            String query = "INSERT INTO lichTHi (hocKy, monThi, ngayThi, caThi, phongThi, thoiGianThi, kyThi, hinhThuc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, hocKy);
            stmt.setString(2, monThi);
            stmt.setString(3, ngayThi);
            stmt.setString(4, caThi);
            stmt.setString(5, phongThi);
            stmt.setString(6, thoiGianThi);
            stmt.setString(7, kyThi);
            stmt.setString(8, hinhThuc);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlertINFO("Lưu lịch thi thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi lưu thông tin vào cơ sở dữ liệu.");
        }

        caThiSplit.setText("");
        phongThiSplit.setText("");
        thoiGianThiSplit.setText("");
        kyThiSplit.setText("");
        hinhThucSplit.setText("");
        monThiSplit.setText("");
        hocKySplit.setText("");
        ngayThiPicker.setValue(null);
    }

    @FXML
    public void onXoaLichThi(ActionEvent event) {
        String monThi = monThiSplit.getText();
        if (monThi.isEmpty()) {
            showAlert("Vui lòng chọn môn học để xóa.");
            return;
        }

        String hocKy = hocKySplit.getText();
        if (hocKy.isEmpty()) {
            showAlert("Vui lòng chọn học kỳ để xóa.");
            return;
        }

        hocKySplit.setText("");
        monThiSplit.setText("");
        caThiSplit.setText("");
        phongThiSplit.setText("");
        ngayThiPicker.setValue(null);
        thoiGianThiSplit.setText("");
        kyThiSplit.setText("");
        hinhThucSplit.setText("");

        deleteLichThi(monThi, hocKy);
    }

    private void deleteLichThi(String monThi, String hocKy) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "DELETE FROM lichThi WHERE monThi = ? AND hocKy = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, monThi);
            stmt.setString(2, hocKy);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Xóa lịch thi thành công!");
                alert.showAndWait();
            } else {
                showAlert("Không tìm thấy lịch thi để xóa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Có lỗi xảy ra trong quá trình xóa lịch thi.");
        }
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
}
