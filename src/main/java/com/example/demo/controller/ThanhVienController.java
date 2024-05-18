// UserController.java (Controller)
package com.example.demo.controller;

import com.example.demo.entity.ThanhVien;
import com.example.demo.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ThanhVienController {
    @Autowired
    private ThanhVienRepository ThanhVienRepository;

    @GetMapping("/thanhvien/{maTV}")
    public String xemChiTietThanhVien(@PathVariable("maTV") int maTV, Model model) {
        // Gọi phương thức từ Repository hoặc Service để lấy thông tin của thành viên từ mã thành viên
        // Ví dụ:
        int maTVToFetch = 1120150137;
        ThanhVien thanhVien = ThanhVienRepository.getByMaTV(maTVToFetch);

        // Kiểm tra nếu không tìm thấy thành viên
        if (thanhVien == null) {
            // Xử lý khi không tìm thấy thành viên
            return "error"; // Trả về trang lỗi
        }

        // Truyền thông tin thành viên vào model để hiển thị trong View
        model.addAttribute("thanhVien", thanhVien);

        // Trả về tên của view template để hiển thị thông tin thành viên
        return "thanhvien"; // Trả về tên của view template
    }
    // Thêm một phương thức mới để xử lý trang lỗi
    @GetMapping("/error")
    public String handleError() {
        return "error"; // Trả về trang lỗi
    }

}
