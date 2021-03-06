package com.IJeans.Backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.IJeans.Backend.model.AutenticacaoModel;
import com.IJeans.Backend.service.AutenticacaoService;




@RestController
@RequestMapping(value = "/")
public class AutenticacaoController {
	
	@Autowired
	AutenticacaoService autService;
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<String> getAll(@RequestBody AutenticacaoModel model) {

		return ResponseEntity.ok().body(autService.doLogin(model.getLogin(), model.getSenha()));
	}
}
