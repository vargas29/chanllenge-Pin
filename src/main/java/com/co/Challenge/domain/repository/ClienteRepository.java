package com.co.Challenge.domain.repository;

import com.co.Challenge.domain.dtos.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente guardar(Cliente cliente);
    List<Cliente> obtenerTodos();
    Optional<Cliente> obtenerPorId(Long id);
}