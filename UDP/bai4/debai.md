Mã câu hỏi: nz8pCjkm
Exam Server: 36.50.135.242
Dùng host này thay cho địa chỉ Exam Server trong đề bài

Nội dung
Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode".
Ví dụ: ";B15DCCN001;5B35BCC1"

b. Nhận thông điệp từ server theo định dạng "requestId;data"
- requestId là một chuỗi ngẫu nhiên duy nhất
- data là chuỗi dữ liệu cần xử lý

c. Xử lý chuẩn hóa chuỗi đã nhận thành theo nguyên tắc
- Ký tự đầu tiên của từng từ trong chuỗi là in hoa
- Các ký tự còn lại của chuỗi là in thường
Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng "requestId;data"

d. Đóng socket và kết thúc chương trình
