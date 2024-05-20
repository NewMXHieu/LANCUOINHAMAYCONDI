package com.example.demo.utilities;

import com.example.demo.entity.xuly;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface XulyService {
	List<xuly> findAll();
	List<xuly> searchMaTV(String maTV);
}