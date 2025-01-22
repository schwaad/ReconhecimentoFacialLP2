package com.reconhecimento_facial_lp2.springboot.repository;

import com.reconhecimento_facial_lp2.springboot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

}
