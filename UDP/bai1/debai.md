Mã câu hỏi: WrnSuNZ1
Exam Server: 36.50.135.242
Dùng host này thay cho địa chỉ Exam Server trong đề bài

Nội dung
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode".
Ví dụ: ";B15DCCN001;73457A17"

b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n;A1,A2,...An", với
- requestId là chuỗi ngẫu nhiên duy nhất
- n là một số ngẫu nhiên nhỏ hơn 100.
- A1, A2 ... Am (m <= n) là các giá trị ngẫu nhiên nhỏ hơn hoặc bằng n và có thể trùng nhau.
Ví dụ: requestId;10;2,3,5,6,5

c. Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng "requestId;B1,B2,...,Bm"
Ví dụ: requestId;1,4,7,8,9,10

d. Đóng socket và kết thúc chương trình.
