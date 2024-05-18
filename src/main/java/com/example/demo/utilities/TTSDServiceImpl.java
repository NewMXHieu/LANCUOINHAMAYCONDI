package com.example.demo.utilities;

import com.example.demo.entity.ThietBi;
import com.example.demo.repository.TTSDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TTSDServiceImpl implements TTSDService{
    @Autowired
    private TTSDRepository TTSDRepository;

    @Override
    public Page<ThietBi> listAll(int PageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(PageNum - 1, pageSize);

        return TTSDRepository.getAll(pageable);
    }
}