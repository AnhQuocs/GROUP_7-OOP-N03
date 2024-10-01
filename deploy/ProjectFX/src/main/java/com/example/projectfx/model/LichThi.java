package com.example.projectfx.model;

import javafx.beans.property.SimpleStringProperty;

public class LichThi {
    private final SimpleStringProperty hocKy;
    private final SimpleStringProperty monThi;
    private final SimpleStringProperty ngayThi;
    private final SimpleStringProperty caThi;
    private final SimpleStringProperty phongThi;
    private final SimpleStringProperty thoiGianThi;
    private final SimpleStringProperty kyThi;
    private final SimpleStringProperty hinhThuc;

    public LichThi(String hocKy, String monThi, String ngayThi, String caThi, String phongThi, String thoiGianThi, String kyThi, String hinhThuc) {
        this.hocKy = new SimpleStringProperty(hocKy);
        this.monThi = new SimpleStringProperty(monThi);
        this.ngayThi = new SimpleStringProperty(ngayThi);
        this.caThi = new SimpleStringProperty(caThi);
        this.phongThi = new SimpleStringProperty(phongThi);
        this.thoiGianThi = new SimpleStringProperty(thoiGianThi);
        this.kyThi = new SimpleStringProperty(kyThi);
        this.hinhThuc = new SimpleStringProperty(hinhThuc);
    }

    //Getter and Setter
    public String getHocKy() {return hocKy.get();}
    public void setHocKy(String hocKy) {this.hocKy.set(hocKy);}

    public String getMonThi() {return monThi.get();}
    public void setMonThi(String value) {this.monThi.set(value);}

    public String getNgayThi() {return ngayThi.get();}
    public void setNgayThi(String value) {this.ngayThi.set(value);}

    public String getCaThi() {return caThi.get();}
    public void setCaThi(String value) {this.caThi.set(value);}

    public String getPhongThi() {return phongThi.get();}
    public void setPhongThi(String value) {this.phongThi.set(value);}

    public String getThoiGianThi() {return thoiGianThi.get();}
    public void setThoiGianThi(String value) {this.thoiGianThi.set(value);}

    public String getKyThi() {return kyThi.get();}
    public void setKyThi(String value) {this.kyThi.set(value);}

    public String getHinhThuc() {return hinhThuc.get();}
    public void setHinhThuc(String value) {this.hinhThuc.set(value);}
}
