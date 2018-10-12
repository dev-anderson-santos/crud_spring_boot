package com.bazar.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazar.sistema.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
