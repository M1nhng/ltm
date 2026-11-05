Mã câu hỏi: fTSiCNqc
Exam Server: 36.50.135.242
Dùng host này thay cho địa chỉ Exam Server trong đề bài

Nội dung
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau:

Đối tượng trao đổi là thể hiện của lớp UDP.Book được mô tả:
- Tên đầy đủ lớp: UDP.Book
- Các thuộc tính: id (String), title (String), author (String), isbn (String), publishDate (String)
- Hàm khởi tạo:
  public Book(String id, String title, String author, String isbn, String publishDate)
- Trường dữ liệu: private static final long serialVersionUID = 20251107L

Thực hiện:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode".
Ví dụ: ";B23DCCN005;eQkvAeId"

b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Book từ server. Trong đó, các thuộc tính id, title, author, isbn, và publishDate đã được thiết lập sẵn.

c. Thực hiện:
- Chuẩn hóa title: viết hoa chữ cái đầu của mỗi từ.
- Chuẩn hóa author theo định dạng "HỌ, Tên".
- Chuẩn hóa mã ISBN theo định dạng "978-3-16-148410-0"
- Chuyển đổi publishDate từ yyyy-mm-dd sang mm/yyyy.

d. Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Book đã được sửa đổi.

Đóng socket và kết thúc chương trình.

Ghi chú: lớp Book nằm ở UDP/Book.java (package UDP).
