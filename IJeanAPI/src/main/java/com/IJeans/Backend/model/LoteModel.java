package com.IJeans.Backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.IJeans.Backend.controller.dto.EstoqueDto;
import com.IJeans.Backend.controller.dto.ProdutoDto;


@Entity
@Table(name= "lote")
public class LoteModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "quantidade")
	private int quantidade;
	
	@Column(name = "preco_compra")
	private double precoCompra;
	
	@OneToOne
	@JoinColumn(name = "id_produto")
	private ProdutoModel produto;
	
	@OneToOne
	@JoinColumn(name = "id_fornecedor")
	private FornecedorModel fornecedor;

	public LoteModel() {}
	
	public LoteModel(EstoqueDto estoque) {
		this.quantidade = estoque.getQuantidade();
		this.precoCompra = estoque.getPreco();
		this.produto = estoque.getProduto();
		this.fornecedor = estoque.getFornecedor();
	}

	public LoteModel(int id, int quantidade, double precoCompra, ProdutoModel produto, FornecedorModel fornecedor) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.precoCompra = precoCompra;
		this.produto = produto;
		this.fornecedor = fornecedor;
	}
	
	public LoteModel(ProdutoDto produto2) {
		this.quantidade = produto2.getProduto().getQuantidade_estoque();
		this.precoCompra = produto2.getPrecoCompra();
		this.produto = produto2.getProduto();
		this.fornecedor = produto2.getFornecedor();
	}
		

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}

	public FornecedorModel getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorModel fornecedor) {
		this.fornecedor = fornecedor;
	}
	
}
