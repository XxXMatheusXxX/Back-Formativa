package com.yay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yay.entities.Produtos;
import com.yay.services.ProdutosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Produtos", description = "API REST DE GERENCIAMENTO DO Produtos")
@RestController
@RequestMapping("/Produtos")

public class ProdutosController {

	private final ProdutosService ProdutosService;

	@Autowired
	public ProdutosController(ProdutosService ProdutosService) {
		this.ProdutosService = ProdutosService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza Produtos por ID")
	public ResponseEntity<Produtos> buscaProdutosControlId(@PathVariable Long id) {
		Produtos Produtos = ProdutosService.getProdutosById(id);
		if (Produtos != null) {
			return ResponseEntity.ok(Produtos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "apresenta todos os Produtoss")
	public ResponseEntity<List<Produtos>> buscaTodasLigacoesControl() {
		List<Produtos> Produtos = ProdutosService.getAllProdutoss();
		return ResponseEntity.ok(Produtos);
	}

	@PostMapping
	@Operation(summary = "cadastra os Produtoss")
	public ResponseEntity<Produtos> saveProdutosControl(@RequestBody @Valid Produtos Produtos) {
		Produtos saveProdutos = ProdutosService.saveProdutos(Produtos);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProdutos);
	}

	@PutMapping("/{id}")
	@Operation(summary = "altera os Produtoss")
	public ResponseEntity<Produtos> alteraProdutosControl(@PathVariable Long id, @RequestBody @Valid Produtos Produtos) {
		Produtos alteraProdutos = ProdutosService.changeProdutos(id, Produtos);

		if (alteraProdutos != null) {
			return ResponseEntity.ok(Produtos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "deleta os Produtoss")
	public ResponseEntity<String> deleteProdutosControl(@PathVariable Long id) {
		boolean delete = ProdutosService.deleteProdutos(id);
		if (delete) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
