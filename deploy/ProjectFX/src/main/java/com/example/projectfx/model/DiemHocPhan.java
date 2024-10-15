package com.example.projectfx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;

public class DiemHocPhan {
    private final SimpleStringProperty hoVaDem;
    private final SimpleStringProperty ten;
    private final SimpleStringProperty maSinhVien;
    private final SimpleStringProperty tenHocPhan;

    private final SimpleFloatProperty cc1;
    private final SimpleFloatProperty cc2;
    private final SimpleFloatProperty baiTap;
    private final SimpleFloatProperty thucHanh;
    private final SimpleFloatProperty giuaKy;
    private final SimpleFloatProperty cuoiKy;
    private final SimpleFloatProperty diemHe10;
    private final SimpleFloatProperty diemHe4;

    public DiemHocPhan(String ho_va_dem, String ten, String ma_sinh_vien, String tenHocPhan,
                       float cc1, float cc2, float baiTap, float thucHanh,
                       float giuaKy, float cuoiKy, float diemHe10, float diemHe4) {
        this.hoVaDem = new SimpleStringProperty(ho_va_dem);
        this.ten = new SimpleStringProperty(ten);
        this.maSinhVien = new SimpleStringProperty(ma_sinh_vien);
        this.tenHocPhan = new SimpleStringProperty(tenHocPhan);
        this.cc1 = new SimpleFloatProperty(cc1);
        this.cc2 = new SimpleFloatProperty(cc2);
        this.baiTap = new SimpleFloatProperty(baiTap);
        this.thucHanh = new SimpleFloatProperty(thucHanh);
        this.giuaKy = new SimpleFloatProperty(giuaKy);
        this.cuoiKy = new SimpleFloatProperty(cuoiKy);
        this.diemHe10 = new SimpleFloatProperty(diemHe10);
        this.diemHe4 = new SimpleFloatProperty(diemHe4);
    }

    // Getter and Setter
    public String getHoVaDem() { return hoVaDem.get(); }
    public void setHoVaDem(String value) { hoVaDem.set(value); }

    public String getTen() { return ten.get(); }
    public void setTen(String value) { ten.set(value); }

    public String getMaSinhVien() { return maSinhVien.get(); }
    public void setMaSinhVien(String value) { maSinhVien.set(value); }

    public String getTenHocPhan() { return tenHocPhan.get(); }
    public void setTenHocPhan(String value) { tenHocPhan.set(value); }

    public float getCc1() { return cc1.get(); }
    public void setCc1(float cc1) { this.cc1.set(cc1); }

    public float getCc2() { return cc2.get(); }
    public void setCc2(float cc2) { this.cc2.set(cc2); }

    public float getBaiTap() { return baiTap.get(); }
    public void setBaiTap(float baiTap) { this.baiTap.set(baiTap); }

    public float getThucHanh() { return thucHanh.get(); }
    public void setThucHanh(float thucHanh) { this.thucHanh.set(thucHanh); }

    public float getGiuaKy() { return giuaKy.get(); }
    public void setGiuaKy(float giuaKy) { this.giuaKy.set(giuaKy); }

    public float getCuoiKy() { return cuoiKy.get(); }
    public void setCuoiKy(float cuoiKy) { this.cuoiKy.set(cuoiKy); }

    public float getDiemHe10() { return diemHe10.get(); }
    public void setDiemHe10(float diemHe10) { this.diemHe10.set(diemHe10); }

    public float getDiemHe4() { return diemHe4.get(); }
    public void setDiemHe4(float diemHe4) { this.diemHe4.set(diemHe4); }
}