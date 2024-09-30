package com.example.projectfx.model;

import javafx.beans.property.SimpleStringProperty;

public class SinhVien {
    private final SimpleStringProperty hoVaDem;
    private final SimpleStringProperty ten;
    private final SimpleStringProperty maSinhVien;
    private final SimpleStringProperty gTinh;
    private final SimpleStringProperty email;
    private final SimpleStringProperty soDienThoai;
    private final SimpleStringProperty noiSinh;
    private final SimpleStringProperty khoaDT;
    private final SimpleStringProperty nganhHoc;
    private final SimpleStringProperty lopHoc;
    private final SimpleStringProperty dateSinh;

    // Constructor có tham số
    public SinhVien(String hoVaDem, String ten, String maSinhVien, String gTinh, String dateSinh,
                    String noiSinh, String email, String soDienThoai, String khoaDT,
                    String nganhHoc, String lopHoc) {
        this.hoVaDem = new SimpleStringProperty(hoVaDem);
        this.ten = new SimpleStringProperty(ten);
        this.maSinhVien = new SimpleStringProperty(maSinhVien);
        this.gTinh = new SimpleStringProperty(gTinh);
        this.dateSinh = new SimpleStringProperty(dateSinh);
        this.noiSinh = new SimpleStringProperty(noiSinh);
        this.email = new SimpleStringProperty(email);
        this.soDienThoai = new SimpleStringProperty(soDienThoai);
        this.khoaDT = new SimpleStringProperty(khoaDT);
        this.nganhHoc = new SimpleStringProperty(nganhHoc);
        this.lopHoc = new SimpleStringProperty(lopHoc);
    }

    // Constructor mặc định
    public SinhVien(String monHoc, String ngayHoc, String caHoc, String phongHoc, Integer soLuongSinhVien) {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    // Getters and setters
    public String getHoVaDem() { return hoVaDem.get(); }
    public void setHoVaDem(String value) { this.hoVaDem.set(value); }

    public String getTen() { return ten.get(); }
    public void setTen(String value) { this.ten.set(value); }

    public String getMaSinhVien() { return maSinhVien.get(); }
    public void setMaSinhVien(String value) { this.maSinhVien.set(value); }

    public String getEmail() { return email.get(); }
    public void setEmail(String value) { this.email.set(value); }

    public String getSoDienThoai() { return soDienThoai.get(); }
    public void setSoDienThoai(String value) { this.soDienThoai.set(value); }

    public String getNoiSinh() { return noiSinh.get(); }
    public void setNoiSinh(String value) { this.noiSinh.set(value); }

    public String getKhoaDT() { return khoaDT.get(); }
    public void setKhoaDT(String value) { this.khoaDT.set(value); }

    public String getNganhHoc() { return nganhHoc.get(); }
    public void setNganhHoc(String value) { this.nganhHoc.set(value); }

    public String getLopHoc() { return lopHoc.get(); }
    public void setLopHoc(String value) { this.lopHoc.set(value); }

    public String getDateSinh() { return dateSinh.get(); }
    public void setDateSinh(String value) { this.dateSinh.set(value); }

    public String getGTinh() { return gTinh.get(); }
    public void setGTinh(String value) { this.gTinh.set(value); }
}
