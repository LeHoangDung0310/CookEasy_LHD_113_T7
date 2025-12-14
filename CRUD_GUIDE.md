# CookEasy - CRUD Nguyên Liệu với Firebase

## ✅ Đã triển khai

### Firebase Realtime Database

- Kết nối Firebase Realtime Database
- Không cần Authentication - dữ liệu dùng chung
- Permissions Internet đã được thêm

### CRUD Operations

#### 1️⃣ CREATE - Thêm nguyên liệu

- **File**: `ThemNguyenLieuActivity.kt`
- **Chức năng**: Thêm nguyên liệu mới vào Firebase
- **Dữ liệu**: Tên, danh mục, số lượng, đơn vị, ngày hết hạn, trạng thái

#### 2️⃣ READ - Xem danh sách

- **File**: `DanhSachNguyenLieuActivity.kt`
- **Chức năng**: Hiển thị tất cả nguyên liệu từ Firebase
- **Real-time**: Tự động cập nhật khi có thay đổi

#### 3️⃣ UPDATE - Chỉnh sửa

- **File**: `ChiTietNguyenLieuActivity.kt`
- **Chức năng**: Cập nhật thông tin nguyên liệu
- **Dialog**: `dialog_chinh_sua_nguyen_lieu.xml`

#### 4️⃣ DELETE - Xóa

- **File**: `ChiTietNguyenLieuActivity.kt`
- **Chức năng**: Xóa nguyên liệu khỏi Firebase
- **Có xác nhận**: Dialog trước khi xóa

### ⚠️ Authentication

- **Đăng nhập/Đăng ký**: Giữ nguyên đơn giản như cũ (không dùng Firebase Auth)
- Chỉ chuyển màn hình, không kiểm tra thật

## 🗂️ Cấu trúc dữ liệu Firebase

```
nguyen_lieu/
  ├── {id1}/
  │   ├── id: "xxx"
  │   ├── ten: "Cà chua"
  │   ├── danhMuc: "Rau củ"
  │   ├── soLuong: "2 kg"
  │   ├── donVi: "kg"
  │   ├── ngayHetHan: "25/12/2024"
  │   └── trangThai: "Còn hạn"
  └── {id2}/
      └── ...
```

## 🧪 Hướng dẫn test

### Bước 1: Sync Gradle

```bash
./gradlew build
```

### Bước 2: Cấu hình Firebase Console

1. Vào [Firebase Console](https://console.firebase.google.com/)
2. Chọn project "cookeasy-bfd0f"
3. Vào **Realtime Database**
4. Nhấn "Create Database"
5. Chọn location (asia-southeast1)
6. Chọn "Start in **test mode**" (cho phép đọc/ghi tự do)

### Bước 3: Test App

#### Test READ (Xem danh sách)

1. Mở app → Đăng nhập (nhấn nút thôi)
2. Trang chủ → "Danh sách nguyên liệu"
3. Danh sách trống (chưa có dữ liệu)

#### Test CREATE (Thêm mới)

1. Nhấn nút FAB (+) màu cam
2. Điền form:
   - Tên: Cà chua
   - Danh mục: Rau củ
   - Số lượng: 2
   - Đơn vị: kg
   - Ngày hết hạn: 31/12/2024
3. Nhấn "Lưu nguyên liệu"
4. ✅ Xem Firebase Console → Data đã được thêm

#### Test READ (Xem lại)

1. Quay lại danh sách
2. ✅ Item "Cà chua" hiển thị

#### Test UPDATE (Sửa)

1. Click vào "Cà chua"
2. Nhấn icon Edit (bút chì)
3. Sửa số lượng: 2 → 3
4. Nhấn "Lưu"
5. ✅ Kiểm tra Firebase Console → Dữ liệu đã thay đổi

#### Test DELETE (Xóa)

1. Trong chi tiết "Cà chua"
2. Nhấn icon Delete (thùng rác)
3. Xác nhận "Xóa"
4. ✅ Kiểm tra Firebase Console → Dữ liệu đã bị xóa

## 📝 Files đã thay đổi

✅ **Modified:**

- `build.gradle.kts` - Thêm Firebase Database (không có Auth)
- `NguyenLieu.kt` - Model tương thích Firebase (không có userId)
- `FirebaseHelper.kt` - CRUD operations (không cần auth)
- `DanhSachNguyenLieuActivity.kt` - Load từ Firebase
- `ThemNguyenLieuActivity.kt` - Thêm vào Firebase
- `ChiTietNguyenLieuActivity.kt` - Xem/Sửa/Xóa
- `NguyenLieuAdapter.kt` - Hiển thị dữ liệu
- `AndroidManifest.xml` - Internet permissions

✅ **Created:**

- `FirebaseHelper.kt` - CRUD helper
- `dialog_chinh_sua_nguyen_lieu.xml` - Edit dialog

✅ **Unchanged (giữ nguyên):**

- `DangNhapActivity.kt` - Đơn giản như cũ
- `DangKyActivity.kt` - Đơn giản như cũ

## ⚠️ Firebase Rules

**Test Mode (hiện tại):**

```json
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
```

**Khuyến nghị sau khi test xong:**

```json
{
  "rules": {
    "nguyen_lieu": {
      ".read": true,
      ".write": true,
      "$nguyenLieuId": {
        ".validate": "newData.hasChildren(['id', 'ten', 'danhMuc', 'soLuong', 'donVi', 'trangThai'])"
      }
    }
  }
}
```

## 🎯 Kết luận

✅ **Firebase Database đã kết nối**
✅ **CRUD đầy đủ cho Nguyên Liệu**
✅ **Real-time updates**
✅ **Authentication giữ đơn giản như cũ**
✅ **Không có lỗi code**

**Sẵn sàng test ngay! 🚀**
