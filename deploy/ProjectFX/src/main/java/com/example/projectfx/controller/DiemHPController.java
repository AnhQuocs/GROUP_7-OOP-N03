package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.model.DiemHocPhan;
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

public class DiemHPController {
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
    private SplitMenuButton chonHocKy;

    @FXML
    private SplitMenuButton chonMonHoc;

    @FXML
    private SplitMenuButton heSoCC1Split;

    @FXML
    private SplitMenuButton heSoCC2Split;

    @FXML
    private SplitMenuButton heSoBaiTapSplit;

    @FXML
    private SplitMenuButton heSoThucHanhSplit;

    @FXML
    private SplitMenuButton heSoGiuaKySplit;

    @FXML
    private SplitMenuButton heSoCuoiKySplit;

    @FXML
    private TextField maSinhVien;

    @FXML
    private TextField cc1Field;

    @FXML
    private TextField cc2Field;

    @FXML
    private TextField baiTapField;

    @FXML
    private TextField thucHanhField;

    @FXML
    private TextField giuaKyField;

    @FXML
    private TextField cuoiKyField;

    @FXML
    private Button search;

    @FXML
    private Button luuDiem;

    @FXML
    private Button quayLai;

    private ObservableList<SinhVien> data;

    @FXML
    public void initialize() {
        SinhVienDAO dao = new SinhVienDAO();
        List<DiemHocPhan> diemHocPhanList = dao.getAllDiemHocPhan();

        for (MenuItem item : chonHocKy.getItems()) {
            item.setOnAction(event -> chonHocKy.setText(item.getText()));
        }

        for (MenuItem item : chonMonHoc.getItems()) {
            item.setOnAction(event -> chonMonHoc.setText(item.getText()));
        }

        for (MenuItem item : heSoCC1Split.getItems()) {
            item.setOnAction(event -> heSoCC1Split.setText(item.getText()));
        }

        for (MenuItem item : heSoCC2Split.getItems()) {
            item.setOnAction(event -> heSoCC2Split.setText(item.getText()));
        }

        for (MenuItem item : heSoBaiTapSplit.getItems()) {
            item.setOnAction(event -> heSoBaiTapSplit.setText(item.getText()));
        }

        for (MenuItem item : heSoThucHanhSplit.getItems()) {
            item.setOnAction(event -> heSoThucHanhSplit.setText(item.getText()));
        }

        for (MenuItem item : heSoGiuaKySplit.getItems()) {
            item.setOnAction(event -> heSoGiuaKySplit.setText(item.getText()));
        }

        for (MenuItem item : heSoCuoiKySplit.getItems()) {
            item.setOnAction(event -> heSoCuoiKySplit.setText(item.getText()));
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

        maSinhVien.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                maSinhVien.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        setupDecimalField(cc1Field);
        setupDecimalField(cc2Field);
        setupDecimalField(baiTapField);
        setupDecimalField(thucHanhField);
        setupDecimalField(giuaKyField);
        setupDecimalField(cuoiKyField);
    }

    @FXML
    public void onLuuDiem(ActionEvent event) {
        String monHoc = chonMonHoc.getText();
        String hocKy = chonHocKy.getText();
        String maSV = maSinhVien.getText();
        String cc1 = cc1Field.getText();
        String cc2 = cc2Field.getText();
        String baiTap = baiTapField.getText();
        String thucHanh = thucHanhField.getText();
        String giuaKy = giuaKyField.getText();
        String cuoiKy = cuoiKyField.getText();

        // Kiểm tra các trường nhập liệu
        if (cc1.isEmpty()) {
            showAlertERROR("Trường 'CC1' không được để trống.");
            return;
        }
        if (cc2.isEmpty()) {
            showAlertERROR("Trường 'CC2' không được để trống.");
            return;
        }
        if (giuaKy.isEmpty()) {
            showAlertERROR("Trường 'Giữa kỳ' không được để trống.");
            return;
        }
        if (cuoiKy.isEmpty()) {
            showAlertERROR("Trường 'Cuối kỳ' không được để trống.");
            return;
        }

        if (baiTap.isEmpty()) {
            baiTap = "0";
        }

        if (thucHanh.isEmpty()) {
            thucHanh = "0";
        }

        // Khai báo các biến hệ số
        double heSoCC1 = 0;
        double heSoCC2 = 0;
        double heSoBaiTap = 0; // Khai báo ở đây
        double heSoThucHanh = 0; // Khai báo ở đây
        double heSoGiuaKy = 0;
        double heSoCuoiKy = 0;

        // Lấy giá trị từ SplitMenuButton và chuyển đổi sang double
        try {
            heSoCC1 = Double.parseDouble(heSoCC1Split.getText().replace("%", "").trim());
            heSoCC2 = Double.parseDouble(heSoCC2Split.getText().replace("%", "").trim());

            String selectedValueBT = heSoBaiTapSplit.getText();
            if (selectedValueBT == null || selectedValueBT.trim().isEmpty()) {
                heSoBaiTap = 0.0;
            } else {
                heSoBaiTap = Double.parseDouble(selectedValueBT.replace("%", "").trim());
            }

            String selectedValueTH = heSoThucHanhSplit.getText();
            if (selectedValueTH == null || selectedValueTH.trim().isEmpty()) {
                heSoThucHanh = 0.0;
            } else {
                heSoThucHanh = Double.parseDouble(selectedValueTH.replace("%", "").trim());
            }

            heSoGiuaKy = Double.parseDouble(heSoGiuaKySplit.getText().replace("%", "").trim());
            heSoCuoiKy = Double.parseDouble(heSoCuoiKySplit.getText().replace("%", "").trim());

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlertERROR("Giá trị hệ số không hợp lệ.");
            return; // Ngừng thực hiện nếu có lỗi
        }

        double tongHeSo = heSoCC1 + heSoCC2 + heSoBaiTap + heSoThucHanh + heSoGiuaKy + heSoCuoiKy;

        if (tongHeSo != 100) {
            showAlertERROR("Hệ số các điểm phải bằng 100%.");
            return; // Ngừng thực hiện nếu tổng hệ số không đúng
        }

        // Kiểm tra các điểm phải lớn hơn hoặc bằng 0 và nhỏ hơn 10
        float cc1Value, cc2Value, baiTapValue, thucHanhValue, giuaKyValue, cuoiKyValue;

        try {
            cc1Value = Float.parseFloat(cc1);
            cc2Value = Float.parseFloat(cc2);
            baiTapValue = baiTap.isEmpty() ? 0 : Float.parseFloat(baiTap);
            thucHanhValue = thucHanh.isEmpty() ? 0 : Float.parseFloat(thucHanh);
            giuaKyValue = Float.parseFloat(giuaKy);
            cuoiKyValue = Float.parseFloat(cuoiKy);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlertERROR("Giá trị điểm không hợp lệ.");
            return; // Ngừng thực hiện nếu có lỗi
        }

        // Kiểm tra giá trị điểm
        if (cc1Value < 0 || cc1Value > 10) {
            showAlertERROR("Điểm 'CC1' phải lớn hơn hoặc bằng 0 và nhỏ hơn 10.");
            return;
        }
        if (cc2Value < 0 || cc2Value > 10) {
            showAlertERROR("Điểm 'CC2' phải lớn hơn hoặc bằng 0 và nhỏ hơn 10.");
            return;
        }
        if (baiTapValue < 0 || baiTapValue > 10) {
            showAlertERROR("Điểm 'Bài tập' phải lớn hơn hoặc bằng 0 và nhỏ hơn 10.");
            return;
        }
        if (thucHanhValue < 0 || thucHanhValue > 10) {
            showAlertERROR("Điểm 'Thực hành' phải lớn hơn hoặc bằng 0 và nhỏ hơn 10.");
            return;
        }
        if (giuaKyValue < 0 || giuaKyValue > 10) {
            showAlertERROR("Điểm 'Giữa kỳ' phải lớn hơn hoặc bằng 0 và nhỏ hơn 10.");
            return;
        }
        if (cuoiKyValue < 0 || cuoiKyValue > 10) {
            showAlertERROR("Điểm 'Cuối kỳ' phải lớn hơn hoặc bằng 0 và nhỏ hơn 10.");
            return;
        }

        // Lưu hoặc cập nhật điểm vào cơ sở dữ liệu
        try (Connection connection = DataBaseConnection.getConnection()) {
            // Kiểm tra xem bản ghi có tồn tại trong bảng diemHP hay không (dựa trên mã sinh viên và môn học)
            String checkQuery = "SELECT * FROM diemHP WHERE ma_sinh_vien = ? AND tenHocPhan = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, maSV);
            checkStmt.setString(2, monHoc);
            ResultSet resultSet = checkStmt.executeQuery();

            // Lấy thông tin sinh viên từ bảng sinhvien
            String hoVaDem = "", ten = "";
            String sinhVienQuery = "SELECT ho_va_dem, ten FROM sinhvien WHERE ma_sinh_vien = ?";
            try (PreparedStatement sinhVienStmt = connection.prepareStatement(sinhVienQuery)) {
                sinhVienStmt.setString(1, maSV);
                ResultSet sinhVienResultSet = sinhVienStmt.executeQuery();
                if (sinhVienResultSet.next()) {
                    hoVaDem = sinhVienResultSet.getString("ho_va_dem");
                    ten = sinhVienResultSet.getString("ten");
                } else {
                    showAlertERROR("Mã sinh viên không tồn tại.");
                    return;
                }
            }

            float diemHe10 =(float) (cc1Value*heSoCC1/100 + cc2Value*heSoCC2/100 + baiTapValue*heSoBaiTap/100 + thucHanhValue*heSoThucHanh/100 + giuaKyValue*heSoGiuaKy/100 + cuoiKyValue*heSoCuoiKy/100);
            float diemHe4 =(float) (convertToHe4(diemHe10));

            if (resultSet.next()) {
                // Nếu đã tồn tại, cập nhật điểm
                String updateQuery = "UPDATE diemHP SET ho_va_dem = ?, ten = ?, cc1 = ?, cc2 = ?, baiTap = ?, thucHanh = ?, giuaKy = ?, diemHe10 = ?, diemHe4 = ?, cuoiKy = ? WHERE ma_sinh_vien = ? AND tenHocPhan = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                updateStmt.setString(1, hoVaDem); // Lưu họ và đệm
                updateStmt.setString(2, ten); // Lưu tên
                updateStmt.setString(3, cc1);
                updateStmt.setString(4, cc2);
                updateStmt.setString(5, baiTap);
                updateStmt.setString(6, thucHanh);
                updateStmt.setString(7, giuaKy);
                updateStmt.setFloat(8, diemHe10);
                updateStmt.setFloat(9, diemHe4);
                updateStmt.setString(10, cuoiKy);
                updateStmt.setString(11, maSV);
                updateStmt.setString(12, monHoc);
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    tableView.getItems().clear();
                    // Xóa dữ liệu cũ trên giao diện
                    chonHocKy.setText("");
                    chonMonHoc.setText("");
                    heSoCC1Split.setText("");
                    heSoCC2Split.setText("");
                    heSoBaiTapSplit.setText("");
                    heSoThucHanhSplit.setText("");
                    heSoGiuaKySplit.setText("");
                    heSoCuoiKySplit.setText("");
                    cc1Field.clear();
                    cc2Field.clear();
                    baiTapField.clear();
                    thucHanhField.clear();
                    giuaKyField.clear();
                    cuoiKyField.clear();
                    maSinhVien.setText("");
                    showAlertINFOR("Cập nhật điểm sinh viên thành công.");
                }
            } else {
                // Nếu không tồn tại, thêm mới điểm
                String insertQuery = "INSERT INTO diemHP (ho_va_dem, ten, ma_sinh_vien, tenHocPhan, cc1, cc2, baiTap, thucHanh, giuaKy, cuoiKy, diemHe10, diemHe4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
                insertStmt.setString(1, hoVaDem); // Lưu họ và đệm
                insertStmt.setString(2, ten); // Lưu tên
                insertStmt.setString(3, maSV);
                insertStmt.setString(4, monHoc);
                insertStmt.setString(5, cc1);
                insertStmt.setString(6, cc2);
                insertStmt.setString(7, baiTap);
                insertStmt.setString(8, thucHanh);
                insertStmt.setString(9, giuaKy);
                insertStmt.setString(10, cuoiKy);
                insertStmt.setFloat(11, diemHe10);
                insertStmt.setFloat(12, diemHe4);
                int rowsInserted = insertStmt.executeUpdate();
                if (rowsInserted > 0) {
                    tableView.getItems().clear();
                    // Xsóa dữ liệu cũ trên giao diện
                    chonHocKy.setText("");
                    chonMonHoc.setText("");
                    heSoCC1Split.setText("");
                    heSoCC2Split.setText("");
                    heSoBaiTapSplit.setText("");
                    heSoThucHanhSplit.setText("");
                    heSoGiuaKySplit.setText("");
                    heSoCuoiKySplit.setText("");
                    cc1Field.clear();
                    cc2Field.clear();
                    baiTapField.clear();
                    thucHanhField.clear();
                    giuaKyField.clear();
                    cuoiKyField.clear();
                    maSinhVien.setText("");
                    showAlertINFOR("Lưu điểm sinh viên thành công.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlertERROR("Đã xảy ra lỗi khi lưu thông tin vào cơ sở dữ liệu!");
        }
    }

    private void showAlertERROR(String message) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlertINFOR(String message) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSearch(ActionEvent event) {
        String maSV = maSinhVien.getText().trim();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/LuaChonDiem.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLai.getScene().getWindow();

            // Tạo scene mới
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Lựa chọn");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlertERROR("Đã xảy ra lỗi khi quay lại.");
        }
    }

    private void setupDecimalField(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") && !newValue.matches("\\d*\\.\\d*")) {
                field.setText(newValue.replaceAll("[^\\d.]", ""));
            }
            // Chỉ cho phép một dấu chấm
            if (newValue.indexOf('.') != newValue.lastIndexOf('.')) {
                field.setText(oldValue); // Khôi phục giá trị trước đó nếu nhập dấu chấm thứ hai
            }
        });
    }

    public double convertToHe4(double diemHe10) {
        if (diemHe10 >= 8.5) {
            return 4.0;
        } else if (diemHe10 >= 7.0) {
            return 3.5;
        } else if (diemHe10 >= 6.5) {
            return 3.0;
        } else if (diemHe10 >= 5.0) {
            return 2.0;
        } else if (diemHe10 >= 4.0) {
            return 1.0;
        } else {
            return 0.0;
        }
    }


}
