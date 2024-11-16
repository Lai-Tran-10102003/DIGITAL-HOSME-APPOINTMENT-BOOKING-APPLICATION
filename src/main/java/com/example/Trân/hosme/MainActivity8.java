package com.example.Trân.hosme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.Activity8Binding;

public class MainActivity8 extends AppCompatActivity {
    Activity8Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadTerms();
        addEvents();
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void loadTerms() {
        String terms =
                "&nbsp;&nbsp;&nbsp;<b> QUY ĐỊNH CHUNG </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Chào mừng bạn đến với <b>phần mềm HOSME - Đặt lịch khám bệnh</b> (gọi chung là <b>\"Phần mềm\"</b>). Vui lòng đọc kỹ các Quy Định Sử Dụng dưới đây trước khi tiếp tục truy cập hoặc sử dụng các dịch vụ của Phần mềm, để bạn biết được các quyền lợi và nghĩa vụ hợp pháp của mình liên quan đến các vấn đề về việc đăng ký khám bệnh, thanh toán, nhận phiếu khám bệnh, tạo hồ sơ bệnh nhân, quản lý và sử dụng thông tin trong hồ sơ bệnh nhân, quản lý phiếu khám bệnh, hoàn tiền, hủy phiếu khám bệnh, sửa thông tin phiếu khám bệnh, cách giải quyết các sự cố.... trong quá trình sử dụng Phần mềm. <br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b> Các định nghĩa:</b><br><br> " +
                        "&nbsp;&nbsp;&nbsp;<b><i>• Chúng tôi: </b></i>theo ngữ cảnh có thể là công ty và đơn vị có liên kết trên Phần mềm (Bệnh viện Đại học Y dược, TP.HCM)<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b><i>• Dịch vụ: </b></i>là một hoặc một phần hoặc tất cả các tính năng và tiện ích được triển khai trên Phần mềm.<br><br>   " +
                        "&nbsp;&nbsp;&nbsp;<b><i>• Bạn: </b></i>theo ngữ cảnh có thể là người dùng, hoặc bệnh nhân, hoặc người được bệnh nhân đã ủy quyền bằng văn bản, lời nói hoặc một hình thức khác, để truy cập Phần mềm hoặc đăng ký sử dụng các Dịch Vụ trên Phần mềm giúp cho bệnh nhân.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;<b> ĐĂNG NHẬP </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Bạn phải đăng nhập mới có thể sử dụng đầy đủ các Dịch vụ của Phần mềm.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;• Phần mềm định danh tài khoản của bạn bằng số điện thoại di động bạn đang sử dụng.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b> THÔNG TIN BỆNH NHÂN </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Bạn phải cung cấp thông tin bệnh nhân trước khi thực hiện đặt hẹn khám bệnh:  <br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Nếu đã từng khám tại bệnh viện: Bạn phải chọn \"đã từng khám\" > nhập mã số bệnh nhân (hay còn gọi là số hồ sơ) > bấm Xác nhận.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Nếu Bạn chưa từng khám tại bệnh viện: Bạn phải chọn \"chưa từng khám\" > điền đầy đủ, chính xác các thông tin > bấm Xác nhận.<br><br> " +
                        "&nbsp;&nbsp;&nbsp; Các thông tin về giấy tờ tùy thân như chứng minh nhân dân, thẻ căn cước....: bạn phải nhập để chúng tôi xác định đúng bệnh nhân trước khi vào phòng khám. Nếu Bạn không có thông tin về các giấy tờ trên, vui lòng liên hệ với chúng tôi để được hỗ trợ.<br><br>" +
                        "&nbsp;&nbsp;&nbsp; Địa chỉ email và số điện thoại di động: Bạn nên nhập đầy đủ và chính xác để Phần mềm gửi thông báo, hoặc thông tin phiếu khám bệnh cho bạn qua hình thức email và tin nhắn sms.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp; Bệnh viện sẽ từ chối khám chữa bệnh và không hoàn tiền nếu Thông tin bệnh nhân khi đăng ký không đúng với thực tế, vì vậy vui lòng kiểm tra thật kỹ các thông tin trước khi bấm tiếp tục.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b> TÍNH NĂNG \"NHẬP VÀ TÌM LẠI MÃ SỐ BỆNH NHÂN\" </b><br><br>  " +
                        "&nbsp;&nbsp;&nbsp;• Chúng tôi cho phép bạn có thể nhập và tra cứu hoặc tìm lại mã số bệnh nhân (hay còn gọi “số hồ sơ”) của mình, để xem được các thông tin tương ứng với số hồ sơ vừa nhập hoặc vừa tra cứu được.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Tính năng này chỉ giới hạn ở một mục đích duy nhất là: để bạn xem, kiểm tra và xác định được chính xác hồ sơ bệnh nhân của mình khi đăng ký khám bệnh hoặc xem lịch hẹn tái khám của bản thân.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;<b> THỜI GIAN ĐĂNG KÝ </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Thời gian đăng ký trước ngày khám từ 01 đến 30 ngày.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Nếu muốn chọn khám vào ngày hôm sau, Bạn phải thực hiện việc đăng ký và hoàn tất thanh toán trước 17h30 của ngày hôm nay, sau 17h30 Bạn không thể đăng ký khám vào ngày hôm sau được nữa.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;<b> TIỀN VÀ PHÍ ĐĂNG KÝ KHÁM </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Khi đăng ký khám trên Phần mềm, bạn sẽ phải trả tiền khám bệnh: là tiền bạn trả cho việc sử dụng dịch vụ khám chữa bệnh của bệnh viện. Giá khám bệnh sẽ thay đổi tùy theo chuyên khoa và do bệnh viện quy định.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp; Bạn vui lòng kiểm tra Tổng tiền thanh toán trước khi thực hiện việc thanh toán. Khi bạn thực hiện việc thanh toán, đồng nghĩa với với việc bạn hoàn toàn đồng ý, tự nguyện và không có bất kỳ khiếu nại hoặc khiếu kiện nào về bất kỳ một khoản tiền hoặc phí nào trong quá trình sử dụng phần mềm về sau.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b> PHƯƠNG THỨC THANH TOÁN </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Bạn có thể thanh toán các khoản tiền và phí (nếu có) trong quá trình sử dụng phần mềm bằng hình thức thanh toán trực tuyến thông qua:<br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Thẻ ATM nội địa đã kích hoạt tính năng thanh toán trực tuyến.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;• Ví điện tử mà phần mềm cho phép hỗ trợ thanh toán.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b> CÁCH THỨC NHẬN PHIẾU KHÁM BỆNH </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Sau khi đăng ký và thanh toán thành công, Bạn sẽ nhận được Phiếu Khám Bệnh Điện Tử trên Phần mềm: Bạn có thể vào mục Phiếu khám > và tìm thấy phiếu khám bệnh của bạn trên Phần mềm.<br><br>" +
                        "&nbsp;&nbsp;&nbsp; Khi đến ngày khám:<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;• Người bệnh không BHYT sẽ đến trực tiếp phòng khám trước giờ hẹn 15 - 30 phút để khám bệnh.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;• Người bệnh BHYT vui lòng có mặt tại các quầy 12,13,14 Khu A trước giờ hẹn 15 - 30 phút để xác nhận BHYT trước khi vào phòng khám.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;<b> GIÁ TRỊ SỬ DỤNG PHIẾU KHÁM BỆNH </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Phiếu khám bệnh có giá trị sử dụng từ 6h30 đến 17h30 của ngày khám in trên phiếu.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;<b> THAY ĐỔI THÔNG TIN KHÁM TRÊN PHIẾU KHÁM BỆNH </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Sau khi đăng ký khám bệnh thành công, thông tin khám trên phiếu khám bệnh sẽ không thể thay đổi.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;<b><i>• Trừ trường hợp:</b></i> Lịch khám của bác sĩ trên phiếu khám bệnh thay đổi vào ngày khám đã đăng ký. Khi đó, chúng tôi sẽ trực tiếp liên hệ với bạn để trao đổi.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b> HỦY PHIẾU KHÁM BỆNH VÀ QUY ĐỊNH HOÀN TIỀN </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Bạn phải liên hệ trực tiếp đến chúng tôi trước ngày hẹn trên phiếu khám ít nhất là 2 ngày, ngay sau đó chúng tôi sẽ ghi nhận và thực hiện phí hoàn trả. Sau thời gian đó, chúng tôi không xử lý hoàn tiền cho bất cứ lý do nào.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<b> QUY ĐỊNH KHÁC </b><br><br>" +
                        "&nbsp;&nbsp;&nbsp; Chúng tôi có quyền chỉnh sửa các Quy Định Sử Dụng Phần Mềm này vào bất kỳ lúc nào mà không cần thông báo cho người dùng. Bạn cần phải định kỳ truy cập và đọc qua Quy Định Sử Dụng này để kịp thời cập nhật và nắm được các quy định của chúng tôi.<br><br>" +
                        "&nbsp;&nbsp;&nbsp; Quy Định Sử Dụng Phần Mềm này là một phần không tách rời với Điều Khoản Dịch Vụ và Chính Sách Bảo Mật của chúng tôi. Xin vui lòng đọc thêm các Điều Khoản Dịch Vụ và Chính Sách Bảo Mật để hiểu rõ các quy định, điều khoản, chính sách và hướng dẫn của chúng tôi trước khi bạn truy cập Phần mềm và/hoặc đăng ký và/hoặc sử dụng các Dịch Vụ trên Phần mềm của chúng tôi.<br><br>  " +
                        "&nbsp;&nbsp;&nbsp;TÔI ĐÃ ĐỌC QUY ĐỊNH SỬ DỤNG PHẦN MỀM NÀY VÀ ĐỒNG Ý VỚI TẤT CẢ CÁC QUY ĐỊNH CÓ TRONG NỘI DUNG BÊN TRÊN VÀ BẤT KỲ BẢN CHỈNH SỬA NÀO CỦA NỘI DUNG BÊN TRÊN SAU NÀY. BẰNG VIỆC TIẾP TỤC TRUY CẬP VÀ SỬ DỤNG PHẦN MỀM, TÔI HIỂU RẰNG TÔI ĐANG TẠO RA MỘT CHỮ KÝ ĐIỆN TỬ MÀ NÓ CÓ GIÁ TRỊ VÀ HIỆU LỰC TƯƠNG TỰ NHƯ CHỮ KÝ TÔI KÝ BẰNG TAY.<br><br>" +
                        "&nbsp;&nbsp;&nbsp;<i> Cập nhật gần nhất: 01/04/2024.</i><br><br>" ;

        // Sử dụng HTML để hiển thị đoạn văn trong TextView
        binding.termsTextView.setText(HtmlCompat.fromHtml(terms, HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

}
