
import { Injectable, Output, Input, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FuncionarioModel } from 'src/app/shared/model/funcionario.model';



@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {
 

  constructor(private http: HttpClient) {}

  findAll(){
    return this.http.get<Array<FuncionarioModel>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/funcionarios");
  }
  cadastraFuncionario(funcionario){
    return this.http.post<FuncionarioModel>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/funcionarios",funcionario);
  }
  delete(funcionario){
    return this.http.delete<FuncionarioModel>(`http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/funcionarios/${funcionario.id}`);
  }
  alteraFuncionario(funcionario){
    return this.http.put<FuncionarioModel>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/funcionarios",funcionario);
  }

  findByNome(nome){
    return this.http.get<Array<FuncionarioModel>>("http://ec2-18-230-6-253.sa-east-1.compute.amazonaws.com:5000/funcionarios/nome/" + nome);
  }
}
