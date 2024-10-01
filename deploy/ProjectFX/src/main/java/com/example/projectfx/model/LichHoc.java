package com.example.projectfx.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LichHoc {
    private final SimpleStringProperty hocKy;
    private final SimpleStringProperty monHoc;
    private final SimpleStringProperty ngayHoc;
    private final SimpleStringProperty caHoc;
    private final SimpleStringProperty phongHoc;
    private final SimpleIntegerProperty soLuongSinhVien;

    public LichHoc(String hocKy, String monHoc, String ngayHoc, String caHoc, String phongHoc, int soLuongSinhVien) {
        this.hocKy = new SimpleStringProperty(hocKy);
        this.monHoc = new SimpleStringProperty(monHoc);
        this.ngayHoc = new SimpleStringProperty(ngayHoc);
        this.caHoc = new SimpleStringProperty(caHoc);
        this.phongHoc = new SimpleStringProperty(phongHoc);
        this.soLuongSinhVien = new SimpleIntegerProperty(soLuongSinhVien);
    }

    //Getter and Setter
    public String getHocKy() {return hocKy.get();}
    public void setHocKy(String value) {this.hocKy.set(value);}

    public String getMonHoc() {return monHoc.get();}
    public void setMonHoc(String value) {this.monHoc.set(value); }

    public String getNgayHoc() {return ngayHoc.get();}
    public void setNgayHoc(String value) {this.ngayHoc.set(value); }

    public String getCaHoc() {return caHoc.get();}
    public void setCaHoc(String value) {this.caHoc.set(value); }

    public String getPhongHoc() {return phongHoc.get();}
    public void setPhongHoc(String value) {this.phongHoc.set(value); }

    public int getSoLuongSinhVien() {return soLuongSinhVien.get();}
    public void setSoLuongSinhVien(int value) {this.soLuongSinhVien.set(value); }
}
