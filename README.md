# DoAnTotNghiep-HAUI
Đồ án bảo vệ tốt nghiệp trường Đại học Công nghiệp Hà Nội - Vũ Văn Quyết

# Hướng dẫn chạy ứng dụng Spring Boot với Thymeleaf, MySQL và WebSocket

## Giới thiệu

Đây là ứng dụng WEBSITE BÁN SÁCH TIẾNG NHẬT CHO CỬA HÀNG SÁCH BOOKSTORE được xây dựng bằng **Spring Boot**, sử dụng **Thymeleaf** để hiển thị giao diện, **MySQL** làm cơ sở dữ liệu, và **WebSocket** để thực hiện giao tiếp thời gian thực.

## Yêu cầu hệ thống

Để chạy ứng dụng, bạn cần chuẩn bị:

- **Java Development Kit (JDK)** phiên bản 17 hoặc cao hơn.
- **Apache Maven** (để quản lý dự án).
- **MySQL Server** (để lưu trữ dữ liệu).
- Một công cụ quản lý cơ sở dữ liệu như **MySQL Workbench** hoặc **phpMyAdmin**.
- **IDE** như IntelliJ IDEA hoặc Eclipse để phát triển và chạy ứng dụng.

---

## Cài đặt và chạy ứng dụng

### 1. Cài đặt cơ sở dữ liệu MySQL
- Tạo một cơ sở dữ liệu mới, ví dụ: `spring_app`.
- Cập nhật thông tin cấu hình trong file `application.properties` hoặc `application.yml`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring_app
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### 2. Clone và cài đặt ứng dụng

1. **Clone mã nguồn từ repository**  
   Sử dụng lệnh sau để sao chép mã nguồn về máy của bạn:
   ```bash
   git clone <link-repository>
   cd <project-folder>

### 3. Chạy ứng dụng

1. **Chạy ứng dụng bằng Maven**  
   Sử dụng lệnh sau để khởi chạy ứng dụng:
   ```bash
   mvn spring-boot:run


## Liên hệ

Nếu bạn có bất kỳ câu hỏi hoặc cần hỗ trợ, vui lòng liên hệ với tôi:

- **Tác giả**: [Vũ Văn Quyết]  
- **Email**: [vuvanquyet2020@gmail.com](vuvanquyet2020@gmail.com)  
- **GitHub**: [https://github.com/ctvq](https://github.com/ctvq)  


