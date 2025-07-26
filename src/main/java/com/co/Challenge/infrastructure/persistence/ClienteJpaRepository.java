package com.co.Challenge.infrastructure.persistence;


import com.co.Challenge.domain.entity.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClienteJpaRepository extends JpaRepository<ClienteJpaEntity, Long> {}