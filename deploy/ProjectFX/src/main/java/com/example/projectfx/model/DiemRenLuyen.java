package com.example.projectfx.model;

import javafx.beans.property.SimpleStringProperty;

public class DiemRenLuyen {
    private final SimpleStringProperty hoVaDem;
    private final SimpleStringProperty ten;
    private final SimpleStringProperty maSinhVien;
    private final SimpleStringProperty khoaDaoTao;
    private final SimpleStringProperty lopHoc;
    private final SimpleStringProperty diemRenLuyen;

    public DiemRenLuyen(String hoVaDem, String ten, String maSinhVien, String khoaDaoTao, String lopHoc, String diemRenLuyen) {
        this.hoVaDem = new SimpleStringProperty(hoVaDem);
        this.ten = new SimpleStringProperty(ten);
        this.maSinhVien = new SimpleStringProperty(maSinhVien);
        this.khoaDaoTao = new SimpleStringProperty(khoaDaoTao);
        this.lopHoc = new SimpleStringProperty(lopHoc);
        this.diemRenLuyen = new SimpleStringProperty(diemRenLuyen);
    }

    // Getter and Setter
    public String getHoVaDem() {return hoVaDem.get();}
    public void setHoVaDem(String value) {this.hoVaDem.set(value);}

    public String getTen() {return ten.get();}
    public void setTen(String value) {this.ten.set(value);}

    public String getMaSinhVien() {return maSinhVien.get();}
    public void setMaSinhVien(String value) {this.maSinhVien.set(value);}

    public String getKhoaDaoTao() {return khoaDaoTao.get();}
    public void setKhoaDaoTao(String value) {this.khoaDaoTao.set(value);}

    public String getLopHoc() {return lopHoc.get();}
    public void setLopHoc(String value) {this.lopHoc.set(value);}

    public String getDiemRenLuyen() {return diemRenLuyen.get();}
    public void setDiemRenLuyen(String value) {diemRenLuyen.set(value);}
}
