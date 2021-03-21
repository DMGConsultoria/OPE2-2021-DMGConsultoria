package com.IJeans.Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IJeans.Backend.model.ProdutoModel;
import com.IJeans.Backend.service.ProdutosService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosService produtoService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoModel>> getAll(){
		return ResponseEntity.ok().body(produtoService.findAll());
	}
	
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<String> cadastrar(@RequestBody ProdutoModel produto){
		try {
			produtoService.cadastrar(produto);
			return ResponseEntity.ok().body("Produto Cadastrado");
		}catch (Exception e){
			return ResponseEntity.ok().body("Erro ao Cadastrar Produto" + e.getMessage());
		}
	}
	
	
}