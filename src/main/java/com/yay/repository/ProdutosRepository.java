package com.yay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yay.entities.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos,Long> {

}
