Mã câu hỏi: etPpdXEG
Exam Server: 36.50.135.242
Dùng host này thay cho địa chỉ Exam Server trong đề bài

Nội dung
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208 theo cơ chế hai pha.

Yêu cầu
a. Gửi datagram đầu tiên chứa chuỗi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;EE29C059".

b. Nhận phản hồi từ server theo định dạng "requestId;data". Ví dụ: 7Brz6QQA;REFUND ticket Payment Payment customer Payment customer ticket REFUND customer shipping shipping Payment.

c. Chuyển chuỗi về chữ thường, loại bỏ dấu câu, gom nhiều khoảng trắng thành một khoảng trắng và thay khoảng trắng bằng dấu gạch ngang.

d. Gửi datagram nộp kết quả theo định dạng "requestId;answer". Ví dụ: 7Brz6QQA;refund-ticket-payment-payment-customer-payment-customer-ticket-refund-customer-shipping-shipping-payment

e. Đóng kết nối hoặc kết thúc client sau khi nộp kết quả.
