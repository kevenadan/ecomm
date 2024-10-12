package com.ecomm.ecomm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.ecomm.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{
	List<Produto> findTop4ByOrderByIdAsc();
}
