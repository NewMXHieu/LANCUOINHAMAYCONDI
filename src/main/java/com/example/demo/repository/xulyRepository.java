package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.xuly;

@Repository
public interface xulyRepository extends JpaRepository<xuly,Integer> {
    xuly findByMaXL(int MaXL);

    @Query("SELECT x FROM xuly x WHERE CAST(x.maTV AS string) LIKE CONCAT('%', :maTV, '%')")
    List<xuly> searchMaTV(String maTV);
}