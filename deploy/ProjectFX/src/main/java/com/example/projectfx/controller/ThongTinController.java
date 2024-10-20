package com.example.projectfx.controller;

import com.example.projectfx.database.DataBaseConnection;
import com.example.projectfx.database.SinhVienDAO;
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

import com.example.projectfx.model.SinhVien;

public class ThongTinController {

    // Gán các thành phần từ FXML
    @FXML
    private TextField hoVaDemField;

    @FXML
    private TextField tenField;

    @FXML
    private TextField maSinhVienField;

    @FXML
    private TextField emailFIeld;

    @FXML
    private TextField soDienThoaiField;

    @FXML
    private DatePicker dateSinhPicker;

    @FXML
    private RadioButton namRadioButton;

    @FXML
    private RadioButton nuRadioButton;

    @FXML
    private SplitMenuButton locationMenu;

    @FXML
    private SplitMenuButton nienKhoaButton;

    @FXML
    private SplitMenuButton khoaDaoTaoButton;

    @FXML
    private SplitMenuButton nganhHocButton;

    @FXML
    private SplitMenuButton lopHocButton;

    @FXML
    private Button quayLaiDSButton;

    @FXML
    private Button luuButton;

    @FXML
    private Button lamMoiButton;

    @FXML
    private Button xoaButton;

    private ToggleGroup genderGroup;

    public static SinhVien nhapThongTin(String hoVaDem, String ten, String maSinhVien, String email, String soDienThoai, String noiSinh, String khoaDT, String nganhHoc, String lopHoc, String dateSinh, String gTinh) {
        return new SinhVien(hoVaDem, ten, maSinhVien, gTinh, dateSinh, noiSinh, email, soDienThoai, khoaDT, nganhHoc, lopHoc);
    }

    // Hàm khởi tạo controller
    @FXML
    public void initialize() {
        soDienThoaiField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                soDienThoaiField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        maSinhVienField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                maSinhVienField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        genderGroup = new ToggleGroup();
        namRadioButton.setToggleGroup(genderGroup);
        nuRadioButton.setToggleGroup(genderGroup);

        for (MenuItem item : locationMenu.getItems()) {
            item.setOnAction(event -> locationMenu.setText(item.getText()));
        }

        for (MenuItem item : nienKhoaButton.getItems()) {
            item.setOnAction(event -> nienKhoaButton.setText(item.getText()));
        }

        for (MenuItem item : khoaDaoTaoButton.getItems()) {
            item.setOnAction(event -> khoaDaoTaoButton.setText(item.getText()));
        }

        for (MenuItem item : nganhHocButton.getItems()) {
            item.setOnAction(event -> nganhHocButton.setText(item.getText()));
        }

        for (MenuItem item : lopHocButton.getItems()) {
            item.setOnAction(event -> lopHocButton.setText(item.getText()));
        }

        // Lấy danh sách sinh viên từ cơ sở dữ liệu
        SinhVienDAO dao = new SinhVienDAO();
        List<SinhVien> sinhVienList = dao.getAllSinhVien();

    }

    @FXML
    public void onLuuButtonClick(ActionEvent event) {
        String hoVaDem = hoVaDemField.getText();
        String ten = tenField.getText();
        String maSinhVien = maSinhVienField.getText();
        String email = emailFIeld.getText();
        String soDienThoai = soDienThoaiField.getText();
        String noiSinh = locationMenu.getText();
        String nienKhoa = nienKhoaButton.getText();
        String khoaDT = khoaDaoTaoButton.getText();
        String nganhHoc = nganhHocButton.getText();
        String lopHoc = lopHocButton.getText();

        LocalDate localDate = dateSinhPicker.getValue(); // Lấy giá trị từ DatePicker
        String dateSinh = null; // Khởi tạo biến cho ngày sinh

        // Kiểm tra ngày sinh
        if (localDate != null) {
            // Chuyển đổi ngày sang định dạng yyyy-MM-dd
            dateSinh = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            showAlert("Vui lòng chọn ngày sinh.");
            return;
        }

        String gTinh = namRadioButton.isSelected() ? "Nam" : "Nữ";

        // Kiểm tra thông tin
        if (hoVaDem.isEmpty()) {
            showAlert("Trường 'Họ và đệm' không được để trống.");
            return;
        }
        if (ten.isEmpty()) {
            showAlert("Trường 'Tên' không được để trống.");
            return;
        }
        if (maSinhVien.isEmpty()) {
            showAlert("Trường 'Mã sinh viên' không được để trống.");
            return;
        }
        if (email.isEmpty()) {
            showAlert("Trường 'Email' không được để trống.");
            return;
        }

        // Kiểm tra email có đuôi "@st.phenikaa-uni.edu.vn"
        if (!email.endsWith("@st.phenikaa-uni.edu.vn")) {
            showAlert("Email phải có đuôi '@st.phenikaa-uni.edu.vn'.");
            return;
        }

        if (soDienThoai.isEmpty()) {
            showAlert("Trường 'Số điện thoại' không được để trống.");
            return;
        }
        if (noiSinh.isEmpty()) {
            showAlert("Vui lòng chọn nơi sinh.");
            return;
        }
        if (nienKhoa.isEmpty()) {
            showAlert("Vui lòng chọn niên khóa.");
            return;
        }
        if (khoaDT.isEmpty()) {
            showAlert("Vui lòng chọn khóa đào tạo.");
            return;
        }
        if (nganhHoc.isEmpty()) {
            showAlert("Vui lòng chọn ngành học.");
            return;
        }
        if (lopHoc.isEmpty()) {
            showAlert("Vui lòng chọn lớp học.");
            return;
        }

        SinhVien sinh_vien = new SinhVien(hoVaDem, ten, maSinhVien, email, soDienThoai, noiSinh, dateSinh, gTinh, khoaDT, lopHoc, nganhHoc);
        sinh_vien.setHoVaDem(hoVaDem);
        sinh_vien.setTen(ten);
        sinh_vien.setMaSinhVien(maSinhVien);
        sinh_vien.setEmail(email);
        sinh_vien.setSoDienThoai(soDienThoai);
        sinh_vien.setNoiSinh(noiSinh);
        sinh_vien.setDateSinh(dateSinh);
        sinh_vien.setGTinh(gTinh);
        sinh_vien.setKhoaDT(khoaDT);
        sinh_vien.setLopHoc(lopHoc);
        sinh_vien.setNganhHoc(nganhHoc);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Lưu thông tin sinh viên thành công!");
        alert.showAndWait();

        // Xóa dữ liệu trong các trường
        hoVaDemField.clear();
        tenField.clear();
        maSinhVienField.clear();
        dateSinhPicker.setValue(null);
        namRadioButton.setSelected(false);
        nuRadioButton.setSelected(false);
        locationMenu.setText("");
        nienKhoaButton.setText("");
        khoaDaoTaoButton.setText("");
        nganhHocButton.setText("");
        lopHocButton.setText("");
        emailFIeld.clear();
        soDienThoaiField.setText("");

        // Lưu vào cơ sở dữ liệu
        try (Connection connection = DataBaseConnection.getConnection()) {
            String checkQuery = "SELECT * FROM sinhvien WHERE ma_sinh_vien = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, maSinhVien);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                // Sinh viên đã tồn tại -> Xóa sinh viên cũ
                String deleteQuery = "DELETE FROM sinhvien WHERE ma_sinh_vien = ?";
                PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery);
                deleteStmt.setString(1, maSinhVien);
                deleteStmt.executeUpdate();
            }

            String query = "INSERT INTO sinhvien (ho_va_dem, ten, ma_sinh_vien, email, so_dien_thoai, date_sinh, gTinh, noi_sinh, khoa_dao_tao, nganh_hoc, lop_hoc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, hoVaDem);
            stmt.setString(2, ten);
            stmt.setString(3, maSinhVien);
            stmt.setString(4, email);
            stmt.setString(5, soDienThoai);
            stmt.setString(6, dateSinh);
            stmt.setString(7, gTinh);
            stmt.setString(8, noiSinh);
            stmt.setString(9, khoaDT);
            stmt.setString(10, nganhHoc);
            stmt.setString(11, lopHoc);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Lưu thông tin sinh viên thành công.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi lưu thông tin vào cơ sở dữ liệu.");
        }
    }

    // Phương thức hiển thị alert cho các lỗi
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Xử lý sự kiện khi nút "Làm mới" được nhấn
    @FXML
    public void onLamMoiButtonClick() {
        hoVaDemField.clear();
        tenField.clear();
        maSinhVienField.clear();
        dateSinhPicker.setValue(null);
        namRadioButton.setSelected(false);
        nuRadioButton.setSelected(false);
        locationMenu.setText("");
        nienKhoaButton.setText("");
        khoaDaoTaoButton.setText("");
        nganhHocButton.setText("");
        lopHocButton.setText("");
        soDienThoaiField.setText("");
        emailFIeld.clear();
    }

    private void clearFields() {
        hoVaDemField.clear();
        tenField.clear();
        maSinhVienField.clear();
        dateSinhPicker.setValue(null);
        namRadioButton.setSelected(false);
        nuRadioButton.setSelected(false);
        locationMenu.setText("");
        nienKhoaButton.setText("");
        khoaDaoTaoButton.setText("");
        nganhHocButton.setText("");
        lopHocButton.setText("");
        soDienThoaiField.clear();
        emailFIeld.clear(); // Sử dụng tên đã sửa
    }


    // Xử lý sự kiện khi nút "Xóa" được nhấn
    @FXML
    public void onXoaButtonClick() {

        String maSinhVien = maSinhVienField.getText();
        if (maSinhVien.isEmpty()) {
            showAlert("Vui lòng nhập mã sinh viên để xóa.");
            return;
        }

        deleteSinhVien(maSinhVien);

        // Xóa dữ liệu trong các trường
        clearFields(); // Gọi phương thức clearFields() đã tạo trước đó
    }

    @FXML
    public void quayLaiDS(ActionEvent event) {
        try {
            // Tải file FXML cho scene DanhSach
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fxml/DanhSach.fxml"));
            Parent danhSachView = loader.load();

            // Lấy Stage hiện tại từ sự kiện
            Stage stage = (Stage) quayLaiDSButton.getScene().getWindow();

            // Tạo scene mới cho DanhSach
            Scene scene = new Scene(danhSachView);

            stage.setTitle("Thông tin sinh viên");

            // Đặt scene mới cho Stage và hiển thị
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Đã xảy ra lỗi khi quay lại danh sách.");
        }
    }

    @FXML
    public void hienThiThongTinSinhVien(SinhVien sinhVien) {
        hoVaDemField.clear();
        tenField.clear();
        maSinhVienField.clear();
        emailFIeld.clear();
        soDienThoaiField.clear();
        dateSinhPicker.setValue(null);
        namRadioButton.setSelected(false);
        nuRadioButton.setSelected(false);
        locationMenu.setText("");
        khoaDaoTaoButton.setText("");
        nganhHocButton.setText("");
        lopHocButton.setText("");

        hoVaDemField.setText(sinhVien.getHoVaDem());
        tenField.setText(sinhVien.getTen());
        maSinhVienField.setText(sinhVien.getMaSinhVien());
        emailFIeld.setText(sinhVien.getEmail());
        soDienThoaiField.setText(sinhVien.getSoDienThoai());

        String dateValue = sinhVien.getDateSinh(); // Đây là chuỗi từ SinhVien
        try {
            dateSinhPicker.setValue(LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (DateTimeParseException e) {
            // Nếu ngày không hợp lệ, bỏ qua hoặc đặt giá trị mặc định
            dateSinhPicker.setValue(null);
            System.out.println("Ngày sinh không hợp lệ: " + dateValue);
        }

        // Xác định giới tính
        if (sinhVien.getGTinh().equals("Nam")) {
            namRadioButton.setSelected(true);
        } else {
            nuRadioButton.setSelected(true);
        }

        // Hiển thị các giá trị còn lại
        locationMenu.setText(sinhVien.getNoiSinh());
        khoaDaoTaoButton.setText(sinhVien.getKhoaDT());
        nganhHocButton.setText(sinhVien.getNganhHoc());
        lopHocButton.setText(sinhVien.getLopHoc());
    }

    private void deleteSinhVien(String maSinhVien) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "DELETE FROM SinhVien WHERE ma_sinh_vien = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, maSinhVien);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Xóa sinh viên thành công!");
                alert.showAndWait();
            } else {
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Có lỗi xảy ra trong quá trình xóa sinh viên.");
        }
    }


}

