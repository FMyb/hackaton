package com.example.hackaton.repository;

import com.example.hackaton.model.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yaroslav Ilin
 */
@Repository
public interface MethodRepository extends JpaRepository<Method, Long> {
	Method findMethodByFullName(String fullName);
}
