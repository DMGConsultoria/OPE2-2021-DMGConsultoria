
import { Injectable, Output, Input, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ProdutoModel } from 'src/app/shared/model/produto.model';
import { MarcaModel } from 'src/app/shared/model/marca.model';
import { TamanhoModel } from 'src/app/shared/model/tamanho.model';
import { TipoProduto } from 'src/app/shared/model/tipo.model';



@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
 

  constructor(private http: HttpClient) {}

  findAll(){
    return this.http.get<Array<ProdutoModel>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/produtos");
  }
  findById(id){
    return this.http.get<ProdutoModel>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/produtos/"+id);
  }
  cadastrarProduto(body){
    return this.http.post("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/estoque/novoProduto",body);
  }
  findByTipoProduto(tipo){
    return this.http.get<Array<TamanhoModel>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/tamanhos/"+tipo)
  }
  findAllMarcas(){
    return this.http.get<Array<MarcaModel>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/marcas/")
  }
  findAllTamanhos(){
    return this.http.get<Array<TamanhoModel>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/tamanhos")
  }
  findAllTipoProduto(){
    return this.http.get<Array<TipoProduto>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/tipoProdutos")
  }

  updateProduto(body){
    return this.http.put<TipoProduto>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/produtos",body)
  }
  removeProduto(id){
    return this.http.delete<TipoProduto>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/produtos/"+id)
  }

  findByNome(nome){
    return this.http.get<Array<ProdutoModel>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/produtos/nome/" + nome);
  }

}
