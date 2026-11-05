Mã câu hỏi: p574Zs8t
Exam Server: 36.50.135.242
Dùng host này thay cho địa chỉ Exam Server trong đề bài

Nội dung
Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client tương tác với server kịch bản dưới đây:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode".
Ví dụ: ";B15DCCN001;EE29C059"

b. Nhận thông điệp từ server theo định dạng "requestId; data"
- requestId là một chuỗi ngẫu nhiên duy nhất
- data là chuỗi dữ liệu đầu vào cần xử lý
Ví dụ: requestId;Qnc8d5x78aldSGWWmaAAjyg3

c. Tìm kiếm ký tự xuất hiện nhiều nhất trong chuỗi và gửi lên server theo định dạng "requestId;ký tự xuất hiện nhiều nhất: các vị trí xuất hiện ký tự đó"
Ví dụ: requestId;8:4,9,

d. Đóng socket và kết thúc chương trình
