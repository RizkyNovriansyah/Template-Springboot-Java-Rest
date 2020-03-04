package com.belajar.template.service;

import com.belajar.template.model.ModelAnggota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAnggota extends JpaRepository<ModelAnggota, Integer> {
    ModelAnggota findByUsername(String UserName);
}
