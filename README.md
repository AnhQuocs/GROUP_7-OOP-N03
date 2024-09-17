### **Ứng dụng quản lý sinh viên**

## **Mục lục**
1. [Giới thiệu](#giới-thiệu)
2. [Thành viên nhóm](#thành-viên-nhóm)
3. [Mục tiêu cần đạt của dự án](#mục-tiêu-cần-đạt-của-dự-án)
4. [Chức năng ứng dụng](#chức-năng-ứng-dụng)
   - [Quản lý sinh viên](#quản-lý-sinh-viên)
   - [Quản lý môn học](#quản-lý-môn-học)
   - [Đăng ký môn học](#đăng-ký-môn-học)
   - [Nhập điểm sinh viên](#nhập-điểm-sinh-viên)
5. [Xử lý dữ liệu](#xử-lý-dữ-liệu)
6. [Tùy chọn nâng cao](#tùy-chọn-nâng-cao)
7. [Hướng dẫn cài đặt và chạy chương trình](#hướng-dẫn-cài-đặt-và-chạy-chương-trình)

---

### **Giới thiệu**
Đây là một dự án xây dựng một ứng dụng quản lý sinh viên bằng ngôn ngữ Java với giao diện JavaFX. Ứng dụng sẽ hỗ trợ các chức năng cơ bản như quản lý sinh viên, môn học, đăng ký môn học, nhập điểm, và lưu trữ dữ liệu xuống file nhị phân.

---

### **Thành viên nhóm**
   - **Bùi Anh Quốc**: Phát triển ứng dụng toàn diện.
   - **Trần Anh Tú**

---

### **Mục tiêu cần đạt của dự án**

1. **Giao diện**: 
   - Ứng dụng sử dụng giao diện đồ họa JavaFX.
  
2. **Chức năng**:
   - Quản lý sinh viên: Thêm, sửa, xóa sinh viên; liệt kê sinh viên và lọc theo điểm trung bình.
   - Quản lý môn học: Thêm, sửa, xóa môn học.
   - Đăng ký môn học cho sinh viên.
   - Nhập điểm sinh viên.

3. **Dữ liệu**: 
   - Dữ liệu cần được lưu trữ trong các file nhị phân.
   - Dữ liệu sẽ được quản lý trong các Collection như `ArrayList`, `LinkedList`, `Map`, v.v.

4. **Tùy chọn nâng cao**:
   - Sinh viên có thể thêm các chức năng bổ sung để làm phong phú thêm ứng dụng.

---

### **Chức năng ứng dụng**

#### **Quản lý sinh viên**
- **Thêm sinh viên**: Người dùng có thể nhập thông tin sinh viên (Mã sinh viên, Họ tên, Ngày sinh, Điểm trung bình) và lưu vào hệ thống.
- **Sửa thông tin sinh viên**: Cho phép chỉnh sửa thông tin của sinh viên đã tồn tại.
- **Xóa sinh viên**: Xóa sinh viên ra khỏi danh sách.
- **Liệt kê sinh viên**: Hiển thị danh sách sinh viên hiện có trong hệ thống.
- **Lọc sinh viên theo điểm**: Lọc các sinh viên có điểm trung bình lớn hơn một giá trị n do người dùng chọn.

#### **Quản lý môn học**
- **Thêm môn học**: Người dùng có thể thêm thông tin về môn học (Mã môn học, Tên môn học, Số tín chỉ).
- **Sửa môn học**: Cho phép chỉnh sửa thông tin của môn học.
- **Xóa môn học**: Xóa môn học ra khỏi hệ thống.

#### **Đăng ký môn học**
- **Đăng ký môn học**: Sinh viên có thể chọn các môn học mà họ muốn đăng ký từ danh sách các môn học có sẵn.

#### **Nhập điểm sinh viên**
- **Nhập điểm**: Giảng viên hoặc người quản lý có thể nhập điểm cho các môn học mà sinh viên đã đăng ký.

---

### **Xử lý dữ liệu**

- Dữ liệu sẽ được lưu trữ trong các file nhị phân.
- Khi làm việc với dữ liệu trong bộ nhớ, dữ liệu cần được lưu trữ trong các cấu trúc dữ liệu như `ArrayList`, `LinkedList`, hoặc `Map`.

**Các lớp liên quan:**
- **SinhVien**: Lớp đại diện cho một sinh viên với các thuộc tính như Mã sinh viên, Họ tên, Ngày sinh, và Điểm trung bình.
- **MonHoc**: Lớp đại diện cho một môn học với các thuộc tính như Mã môn học, Tên môn học, và Số tín chỉ.
- **DangKyHoc**: Lớp quản lý đăng ký môn học của sinh viên.

---

### **Tùy chọn nâng cao**

- Có thể phát triển thêm các chức năng như:
  - Thống kê điểm trung bình của toàn bộ sinh viên.
  - Sắp xếp danh sách sinh viên theo điểm trung bình hoặc theo tên.
  - Xuất dữ liệu ra file CSV hoặc Excel.
  - Gửi email thông báo cho sinh viên khi có cập nhật về điểm số.

---

### **Hướng dẫn cài đặt và chạy chương trình**

1. **Cài đặt môi trường**:
   - Cài đặt JDK (Java Development Kit).
   - Cài đặt IDE hỗ trợ JavaFX như IntelliJ IDEA hoặc Eclipse.
   - Cài đặt thư viện JavaFX nếu chưa có sẵn.

2. **Chạy chương trình**:
   - Import project vào IDE và cấu hình JavaFX.
   - Chạy file `Main.java` để khởi động ứng dụng quản lý sinh viên.

---
