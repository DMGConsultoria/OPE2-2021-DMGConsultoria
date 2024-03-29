package com.IJeans.Backend.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IJeans.Backend.model.ProdutoModel;
import com.IJeans.Backend.service.ProdutosService;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin(origins = "*")
public class ProdutosController {

	@Autowired
	private ProdutosService produtoService;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll(){
		List<ProdutoModel> produtos = produtoService.findAll();
		if (produtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(produtoService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoModel> findByID(@PathVariable("id") String id){
		try {
			return ResponseEntity.ok().body(produtoService.findById(id).get());
		} catch (FileNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/nome/{nomeBusca}")
	public ResponseEntity<Optional<List<ProdutoModel>>> findByNome(@PathVariable("nomeBusca") String nomeBusca){
		Optional<List<ProdutoModel>> nome = produtoService.findByNomeContaining(nomeBusca);
		if(!nome.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(produtoService.findByNomeContaining(nomeBusca));
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoModel> deletar(@PathVariable("id") String id){
		try {
			
		Optional<ProdutoModel> optional = produtoService.findById(id);
		if (optional.isPresent()) {
			try {
				produtoService.deletar(optional.get());
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
			}catch(FileNotFoundException e) {
				return ResponseEntity.notFound().build();
			}
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModel> atualizar(@Valid @RequestBody ProdutoModel produto){
		Optional<ProdutoModel> optional;
		try {
			optional = produtoService.findById(produto.getId());
		
		if (optional.isPresent()) {
			produtoService.atualizar(produto);
			return ResponseEntity.ok().body(produto);
		}
		return ResponseEntity.notFound().build();
		} catch (FileNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
