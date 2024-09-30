package com.example.projectfx.database;

import com.example.projectfx.method.DiemHocPhan;
import com.example.projectfx.method.SinhVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> sinhVienList = new ArrayList<>();
        String query = "SELECT * FROM sinhvien"; // Thay đổi tên bảng theo CSDL của bạn

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                SinhVien sv = new SinhVien(
                        resultSet.getString("ho_va_dem"),
                        resultSet.getString("ten"),
                        resultSet.getString("ma_sinh_vien"),
                        resultSet.getString("email"),
                        resultSet.getString("so_dien_thoai"),
                        resultSet.getString("date_sinh"),
                        resultSet.getString("gTinh"),
                        resultSet.getString("noi_sinh"),
                        resultSet.getString("khoa_dao_tao"),
                        resultSet.getString("nganh_hoc"),
                        resultSet.getString("lop_hoc")
                );
                sinhVienList.add(sv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sinhVienList;
    }

    public List<DiemHocPhan> getAllDiemHocPhan() {
        List<DiemHocPhan> diemHocPhanList = new ArrayList<>();
        String query = "SELECT * FROM diemHP";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DiemHocPhan diemHocPhan = new DiemHocPhan(
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
                diemHocPhanList.add(diemHocPhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diemHocPhanList;
    }
}
