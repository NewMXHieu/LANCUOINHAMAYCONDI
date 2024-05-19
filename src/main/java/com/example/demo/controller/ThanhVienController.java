// UserController.java (Controller)
package com.example.demo.controller;

import com.example.demo.entity.ThanhVien;
import com.example.demo.repository.ThanhVienRepository;
import com.example.demo.utilities.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ThanhVienController {
    @Autowired
    private ThanhVienService thanhVienService;

    @GetMapping("/search")
    public String SearchThanhVien(Model model, @Param("keyword") String keyword) {
        List<ThanhVien> list= thanhVienService.searchList(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listThanhVien", list);

        return "thanhvien/view_all_thanhvien";
    }
    @GetMapping("/user")
    public String getThanhVien(Model model) {
        // Lấy thông tin của thành viên có mã là 1
        ThanhVien thanhVien = thanhVienService.getByMaTV(1120150137);

        // Kiểm tra xem thành viên có tồn tại hay không
        if (thanhVien != null) {
            // Đưa thông tin của thành viên vào model để hiển thị trên giao diện
            model.addAttribute("thanhVien", thanhVien);
            return "user"; // Trả về view để hiển thị thông tin của thành viên
        } else {
            // Nếu không tìm thấy thành viên, điều hướng tới trang lỗi hoặc trang khác tùy bạn
            return "error";
        }
    }
}
