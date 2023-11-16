package com.yay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yay.entities.Produtos;
import com.yay.repository.ProdutosRepository;

@Service
public class ProdutosService {
	private final ProdutosRepository ProdutosRepository;
	

	@Autowired
	public ProdutosService(ProdutosRepository ProdutosRepository) {
		this.ProdutosRepository = ProdutosRepository;
	}

	public List<Produtos> getAllProdutoss() {
		return ProdutosRepository.findAll();
	}

	public Produtos getProdutosById(Long id) {
		Optional<Produtos> Produtos = ProdutosRepository.findById(id);
		return Produtos.orElse(null);
	}

	public Produtos saveProdutos(Produtos Produtos) {
		return ProdutosRepository.save(Produtos);
	}

	public Produtos changeProdutos(Long id, Produtos changeU) {
		Optional<Produtos> existeProdutos = ProdutosRepository.findById(id);
		if (existeProdutos.isPresent()) {
			changeU.setId(id);
			return ProdutosRepository.save(changeU);
		}
		return null;
	}

	public boolean deleteProdutos(Long id) {
		Optional<Produtos> existeProdutos= ProdutosRepository.findById(id);
		if (existeProdutos.isPresent()) {
			ProdutosRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
