package com.ecomm.ecomm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.ecomm.model.Produto;
import com.ecomm.ecomm.repository.ProdutoRepository;
import com.ecomm.ecomm.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> listarProdutos() {
		return produtoRepository.findTop4ByOrderByIdAsc();
	}

}
