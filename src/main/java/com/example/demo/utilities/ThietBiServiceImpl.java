package com.example.demo.utilities;

import com.example.demo.entity.ThanhVien;
import com.example.demo.entity.ThietBi;
import com.example.demo.entity.ThongTinSD;
import com.example.demo.repository.TTSDRepository;
import com.example.demo.repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThietBiServiceImpl implements ThietBiService{

    @Autowired
    private ThietBiRepository ThietBiRepository;
    @Autowired
    private TTSDRepository tTSDRepository;
    @Override
    public Page<ThietBi> listAll(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return ThietBiRepository.findAll(pageable);
    }

    @Override
    public List<ThietBi> getAllSearch(String keyword) {
        return ThietBiRepository.findByTenTBContaining(keyword);
    }

    @Override
    public List<ThietBi> getAllThietBi() {
        return ThietBiRepository.findAll();
    }
    @Override
    public ThietBi saveThietBi(ThietBi thietBi) {
        return ThietBiRepository.save(thietBi);
    }

    @Override
    public void deleteThietBiById(int id) {
        ThietBiRepository.deleteById(id);
    }

    public ThongTinSD datThietBi(int maTB, ThanhVien tv, LocalDateTime tgMuon, LocalDateTime tgTra) throws Exception {
        List<ThongTinSD> overlappingBookings = tTSDRepository.findOverlappingBookings(maTB, tgMuon, tgTra);
        if (!overlappingBookings.isEmpty()) {
            throw new Exception("Thiết bị này đã được đặt trong khoảng thời gian bạn chọn.");
        }

        ThietBi thietBi = ThietBiRepository.findById(maTB).orElse(null);
        if (thietBi != null) {
            ThongTinSD thongTinSD = new ThongTinSD();
            thongTinSD.setThietBi(thietBi);
            thongTinSD.setTgMuon(tgMuon);
            thongTinSD.setTgTra(tgTra);

            thongTinSD.setThanhVien(tv);
            // Set other necessary fields
            return tTSDRepository.save(thongTinSD);
        }
        return null;
    }

}
