package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.model.DiemHocPhan;
import com.example.projectfx.model.DiemRenLuyen;
import com.example.projectfx.model.SinhVien;
import com.example.projectfx.database.SinhVienDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DiemRLController {
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
    private SplitMenuButton chonHocKySplit;

    @FXML
    private TextField maSinhVienField;

    @FXML
    private TextField nhapDiemRLField;

    @FXML
    private Button searchButton;

    @FXML
    private Button quayLaiButton;

    @FXML
    private Button luuDiemRLButton;

    @FXML
    private Button traCuuButton;

    private ObservableList<SinhVien> data;

    @FXML
    public void initialize() {
        SinhVienDAO dao = new SinhVienDAO();
        List<DiemRenLuyen> diemRenLuyenList = dao.getAllDiemRenLuyen();

        for (MenuItem item : chonHocKySplit.getItems()) {
            item.setOnAction(event -> chonHocKySplit.setText(item.getText()));
        }

        data = FXCollections.observableArrayList();
        tableView.setItems(data);

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

        maSinhVienField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                maSinhVienField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    public void onLuuDiem (ActionEvent event) {
        String hocKy = chonHocKySplit.getText();
        String maSV = maSinhVienField.getText();
        String diemRenLuyen = nhapDiemRLField.getText();

        if (hocKy.isEmpty()) {
            showAlertERROR("Vui lòng chọn học kỳ.");
            return;
        }

        if (maSV.isEmpty()) {
            showAlertERROR("Vui lòng nhập mã sinh viên.");
            return;
        }

        if (diemRenLuyen.isEmpty()) {
            showAlertERROR("Vui lòng nhập điểm rèn luyện.");
            return;
        }

        // Lưu hoặc cập nhật điểm vào cơ sở dữ liệu
        try (Connection connection = DataBaseConnection.getConnection()) {
            // Kiểm tra xem bản ghi có tồn tại trong bảng diemHP hay không (dựa trên mã sinh viên và môn học)
            String checkQuery = "SELECT * FROM diemRL WHERE ma_sinh_vien = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, maSV);
            ResultSet resultSet = checkStmt.executeQuery();

            // Lấy thông tin sinh viên từ bảng sinhvien
            String hoVaDem = "", ten = "", khoaDT = "", lopHoc = "";
            String sinhVienQuery = "SELECT ho_va_dem, ten, khoa_dao_tao, lop_hoc FROM sinhvien WHERE ma_sinh_vien = ?";
            try (PreparedStatement sinhVienStmt = connection.prepareStatement(sinhVienQuery)) {
                sinhVienStmt.setString(1, maSV);
                ResultSet sinhVienResultSet = sinhVienStmt.executeQuery();
                if (sinhVienResultSet.next()) {
                    hoVaDem = sinhVienResultSet.getString("ho_va_dem");
                    ten = sinhVienResultSet.getString("ten");
                    khoaDT = sinhVienResultSet.getString("khoa_dao_tao");
                    lopHoc = sinhVienResultSet.getString("lop_hoc");
                } else {
                    showAlertERROR("Mã sinh viên không tồn tại.");
                    return;
                }
            }

            if (resultSet.next()) {
                // Nếu đã tồn tại, cập nhật điểm
                String updateQuery = "UPDATE diemRL SET hoVaDem = ?, ten = ?, khoaDaoTao = ?, lopHoc = ?, diemRenLuyen = ? WHERE ma_sinh_vien = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                updateStmt.setString(1, hoVaDem); // Lưu họ và đệm
                updateStmt.setString(2, ten); // Lưu tên
                updateStmt.setString(3, khoaDT);
                updateStmt.setString(4, lopHoc);
                updateStmt.setString(5, diemRenLuyen);
                updateStmt.setString(6, maSV);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    tableView.getItems().clear();
                    // Xóa dữ liệu cũ trên giao diện
                    chonHocKySplit.setText("");
                    maSinhVienField.setText("");
                    nhapDiemRLField.setText("");
                    showAlertINFOR("Cập nhật điểm sinh viên thành công.");
                }
            } else {
                // Nếu không tồn tại, thêm mới điểm
                String insertQuery = "INSERT INTO diemRL (hoVaDem, ten, ma_sinh_vien, khoaDaoTao, lopHoc, diemRenLuyen) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
                insertStmt.setString(1, hoVaDem); // Lưu họ và đệm
                insertStmt.setString(2, ten); // Lưu tên
                insertStmt.setString(3, maSV);
                insertStmt.setString(4, khoaDT);
                insertStmt.setString(5, lopHoc);
                insertStmt.setString(6, diemRenLuyen);
                int rowsInserted = insertStmt.executeUpdate();
                if (rowsInserted > 0) {
                    tableView.getItems().clear();
                    // Xsóa dữ liệu cũ trên giao diện
                    chonHocKySplit.setText("");
                    maSinhVienField.setText("");
                    nhapDiemRLField.setText("");

                    showAlertINFOR("Lưu điểm sinh viên thành công.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlertERROR("Đã xảy ra lỗi khi lưu thông tin vào cơ sở dữ liệu!");
        }
    }

    @FXML
    public void onSearch(ActionEvent event) {
        String maSV = maSinhVienField.getText().trim();

        // Kiểm tra mã sinh viên trong cơ sở dữ liệu
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT * FROM sinhvien WHERE ma_sinh_vien = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, maSV);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
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

                SinhVien sinhVien = new SinhVien(hoVaDem, ten, maSinhVien, gTinh, dateSinh, noiSinh, email, soDienThoai, khoaDT, nganhHoc, lopHoc);
                data.add(sinhVien);
            } else {
                // Nếu không tìm thấy mã sinh viên, thông báo lỗi
                showAlertERROR("Mã sinh viên " + maSV + " không tồn tại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlertERROR("Đã xảy ra lỗi khi kiểm tra mã sinh viên!");
        }
    }

    @FXML
    public void onQuayLai(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/Main.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLaiButton.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlertERROR("Đã xảy ra lỗi khi quay lại.");
        }
    }

    @FXML
    public void onTraCuu(ActionEvent event) {
        try {
            // Tải file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/TraCuuDRL.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) traCuuButton.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlertERROR("Đã xảy ra lỗi khi chuyển.");
        }
    }

    private void showAlertERROR(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertINFOR(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
