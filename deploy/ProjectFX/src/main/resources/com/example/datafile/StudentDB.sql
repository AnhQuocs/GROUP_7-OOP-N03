drop database if exists studentdb;

CREATE DATABASE StudentDB;

USE StudentDB;

CREATE TABLE sinhvien (
    ho_va_dem VARCHAR(100),
    ten VARCHAR(50),
    ma_sinh_vien VARCHAR(20) unique primary key,
    email VARCHAR(100),
    so_dien_thoai VARCHAR(15),
    date_sinh date,
    gTinh VARCHAR(10),
    noi_sinh VARCHAR(50),
    khoa_dao_tao VARCHAR(50),
    nganh_hoc VARCHAR(50),
    lop_hoc VARCHAR(50)
);

drop table sinhvien;

SELECT * FROM sinhvien;

create table diemHP 
(
	ho_va_dem varchar(100),
    ten varchar(50),
	ma_sinh_vien VARCHAR(20),
	tenHocPhan varchar(30) not null,
	cc1 decimal(5, 2) not null,
    cc2 decimal(5, 2) not null,
    baiTap decimal(5, 2) not null,
    thucHanh decimal(5, 2) not null,
    giuaKy decimal(5, 2) not null,
    cuoiKy decimal(5, 2) not null,
    diemHe10 decimal(5, 2) not null,
    diemHe4 decimal(5, 2) not null,
    primary key (ma_sinh_vien, tenHocPhan),
    foreign KEY (ma_sinh_vien) references sinhvien(ma_sinh_vien)
);

select * from diemHP;

drop table diemHP;

create table diemRL
(
    hoVaDem varchar(50) not null,
    ten varchar(20) not null,
	ma_sinh_vien varchar(9) not null,
    khoaDaoTao varchar(10) not null,
    lopHoc varchar(30) not null,
    diemRenLuyen varchar(3) not null,
	primary key (ma_sinh_vien, khoaDaoTao),
    foreign key (ma_sinh_vien) references sinhvien(ma_sinh_vien)
);

select * from diemRL;

drop table diemRL;

create table lichHoc
(	
	hocKy varchar(50) not null,
	monHoc varchar(100) not null,
	ngayHoc date not null,
    caHoc varchar(50) not null,
    phongHoc varchar(50) not null,
    PRIMARY KEY (monHoc, ngayHoc),
    soLuongSinhVien integer not null
);

drop table lichHoc;

create table lichThi
(
	hocKy varchar(50) not null,
	monThi varchar(100) not null,
    ngayThi date not null,
    caThi varchar(20) not null,
    phongThi varchar(30) not null,
    thoiGianThi varchar(10) not null,
    kyThi varchar(50) not null,
    hinhThuc varchar(50) not null,
    foreign key(monThi) references lichHoc(monHoc)
);

drop table lichThi;

CREATE TABLE SVLH (
    ma_sinh_vien VARCHAR(9) NOT NULL,
    mon_hoc VARCHAR(100) NOT NULL,
    PRIMARY KEY (ma_sinh_vien, mon_hoc), 
    FOREIGN KEY (ma_sinh_vien) REFERENCES sinhvien(ma_sinh_vien),
    FOREIGN KEY (mon_hoc) REFERENCES lichHoc(monHoc)
);

SELECT * FROM lichHoc;

SELECT * FROM lichThi;