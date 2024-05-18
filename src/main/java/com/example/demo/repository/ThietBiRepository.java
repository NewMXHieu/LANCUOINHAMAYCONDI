package com.example.demo.repository;

import com.example.demo.entity.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {

    List<ThietBi> findByTenTBContaining(String keyword);
}
