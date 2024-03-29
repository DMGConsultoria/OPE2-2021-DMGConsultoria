package com.IJeans.Backend.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IJeans.Backend.controller.dto.EstoqueDto;
import com.IJeans.Backend.controller.dto.ProdutoDto;
import com.IJeans.Backend.email.SendMail;
import com.IJeans.Backend.exception.ProdutoExistenteException;
import com.IJeans.Backend.job.GeraRelatorio;
import com.IJeans.Backend.model.MovimentacaoDeEstoqueModel;
import com.IJeans.Backend.model.ProdutoModel;
import com.IJeans.Backend.service.EstoqueService;
import com.IJeans.Backend.service.MovimentacaoDeEstoqueService;
import com.IJeans.Backend.service.ProdutosService;

@RestController
@RequestMapping(value = "/estoque")
@CrossOrigin(origins = "*")
public class MovimentacaoDeEstoqueController {

	@Autowired
	private MovimentacaoDeEstoqueService movimentacaoService;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private ProdutosService produtosService;
	
	@Autowired
	private GeraRelatorio geraRelatorio;
	
	@Autowired
	private SendMail emailService;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public ResponseEntity<List<MovimentacaoDeEstoqueModel>> getAll() {
		List<MovimentacaoDeEstoqueModel> transacoes = movimentacaoService.findAll();
		if (transacoes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(movimentacaoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id){
		return ResponseEntity.ok().body(movimentacaoService.findById(id));
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping
	public ResponseEntity<EstoqueDto> registrarTransacao(@Valid @RequestBody EstoqueDto transacao) throws Exception {
		if(transacao.isStatus()) {
			estoqueService.registrarTransacao(transacao);
		}else {
			estoqueService.registrarVenda(transacao);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/novoProduto")
	public  ResponseEntity<List<ProdutoModel>> cadastrarNovoProduto(@Valid @RequestBody ProdutoDto produto) throws Exception {

		produto.setStatus(true);
		 produtosService.cadastrarNovoProduto(produto);
		 return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/relatorio/{email}")
	public  ResponseEntity<String> relatorio(@PathVariable("email") String email)  {
		geraRelatorio.criaArquivo();
		emailService.sendMail(new File(geraRelatorio.CSV_PATH), email);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}