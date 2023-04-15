package com.example.hackaton.repository;

import com.example.hackaton.model.Method;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yaroslav Ilin
 */
public interface MethodRepository extends JpaRepository<Method, Long> {
}
