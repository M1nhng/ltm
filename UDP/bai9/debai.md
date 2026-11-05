Mã câu hỏi: LqNsFeK4
Exam Server: 36.50.135.242
Dùng host này thay cho địa chỉ Exam Server trong đề bài

Nội dung
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:

a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode".
Ví dụ: ";B15DCCN009;F3E8B2D4"

b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;string", với:
- requestId là chuỗi ngẫu nhiên duy nhất.
- string là một chuỗi chứa các chuỗi con bị thay đổi vị trí.
Ví dụ: veM3xgA1g:4,IPFfgEanY:5,aWX1SzDwe:2,PHupvPc:3,PR3gH8ahN:6,UEEKHLIt:7,M6dpWTE:1

c. Xử lý chuỗi xáo trộn và gửi về chuỗi sau khi sắp xếp: "requestId;string". Ví dụ chuỗi đã được xử lý:
M6dpWTE,aWX1SzDwe,PHupvPc,veM3xgA1g,IPFfgEanY,PR3gH8ahN,UEEKHLIt

d. Đóng socket và kết thúc chương trình.
