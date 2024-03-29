package com.IJeans.Backend.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.IJeans.Backend.controller.dto.EstoqueDto;
import com.IJeans.Backend.controller.dto.ProdutoDto;

@Entity
@Table(name = "movimentacao")
public class MovimentacaoDeEstoqueModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "valor_unitario")
	@NotNull
	private double valor_unitario;
	
	@Column(name = "quantidade")
	@NotNull 
	private int quantidade;
	
	@Column(name = "tipo_transacao")
	@NotNull

	private boolean status;

	@OneToOne
	@JoinColumn(name = "id_lote")
	private LoteModel lote;
	
	@Column(name = "DATA")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataTransacao = LocalDate.now();
	
	public MovimentacaoDeEstoqueModel() {
	}
	
	public MovimentacaoDeEstoqueModel(EstoqueDto estoque, LoteModel lote) {
		this.valor_unitario = estoque.getPreco();
		this.quantidade = estoque.getQuantidade();
		this.status = estoque.isStatus();
		this.lote = lote;
		this.dataTransacao= LocalDate.now();
	}

	public MovimentacaoDeEstoqueModel(String id, double valor_unitario, int quantidade, boolean status) {
		this.id = id;
		this.valor_unitario = valor_unitario;
		this.quantidade = quantidade;
		this.status = status;
	}

	public MovimentacaoDeEstoqueModel(ProdutoDto produto,LoteModel lote) {
		this.valor_unitario = produto.getPrecoCompra();
		this.quantidade = produto.getProduto().getQuantidade_estoque();
		this.status = produto.isStatus();
		this.lote = lote;
		this.dataTransacao= LocalDate.now();
	}

	public LoteModel getLote() {
		return lote;
	}

	public void setLote(LoteModel lote) {
		this.lote = lote;
	}

	public LocalDate getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(LocalDate dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentacaoDeEstoqueModel other = (MovimentacaoDeEstoqueModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
