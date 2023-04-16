package com.example.hackaton.repository;

import com.example.hackaton.model.Method;
import org.springframework.data.jpa.repository.JpaRepository;

interface NetworkAccuracyRepository extends JpaRepository<Method, Long> {}
