package com.example.projectfx.database;

import com.example.projectfx.model.*;

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
                diemHocPhanList.add(diemHocPhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diemHocPhanList;
    }

    public List<DiemRenLuyen> getAllDiemRenLuyen() {
        List<DiemRenLuyen> renLuyenList = new ArrayList<>();
        String query = "SELECT * FROM diemRL";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DiemRenLuyen diemRL = new DiemRenLuyen(
                        resultSet.getString("hoVaDem"),
                        resultSet.getString("ten"),
                        resultSet.getString("ma_sinh_vien"),
                        resultSet.getString("khoaDaoTao"),
                        resultSet.getString("lopHoc"),
                        resultSet.getString("diemRenLuyen")
                );
                renLuyenList.add(diemRL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return renLuyenList;
    }

    public List<LichHoc> getAllLichHoc() {
        List<LichHoc> lichHocList = new ArrayList<>();
        String query = "SELECT * FROM lichHoc";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                LichHoc lichHoc = new LichHoc(
                        resultSet.getString("hocKy"),
                        resultSet.getString("monHoc"),
                        resultSet.getString("ngayHoc"),
                        resultSet.getString("caHoc"),
                        resultSet.getString("ngayHoc"),
                        resultSet.getInt("soLuongSinhVien")
                );
                lichHocList.add(lichHoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lichHocList;
    }

    public List<LichThi> getAllLichThi() {
        List<LichThi> lichThiList = new ArrayList<>();
        String query = "SELECT * FROM lichThi";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                LichThi lichThi = new LichThi(
                        resultSet.getString("hocKy"),
                        resultSet.getString("monThi"),
                        resultSet.getString("ngayThi"),
                        resultSet.getString("caThi"),
                        resultSet.getString("ngayThi"),
                        resultSet.getString("thoiGianThi"),
                        resultSet.getString("kyThi"),
                        resultSet.getString("hinhThuc")
                );
                lichThiList.add(lichThi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lichThiList;
    }
}
