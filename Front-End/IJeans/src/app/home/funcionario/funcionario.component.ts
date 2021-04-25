import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FuncionarioModel } from 'src/app/shared/model/funcionario.model';
import { FuncionarioService } from './service/funcionario.service';

@Component({
  selector: 'app-funcionario',
  templateUrl: './funcionario.component.html',
  styleUrls: ['./funcionario.component.css']
})



export class FuncionarioComponent implements OnInit {

EditRowId: any = '';
funcionarioForm: FormGroup;

  constructor(private service: FuncionarioService, private router:Router,private formBuilder: FormBuilder) { }

  
  funcionarios:Array<FuncionarioModel>;

  ngOnInit(): void {
    this.service.findAll().subscribe(data => {
      this.funcionarios=data
    });

    this.funcionarioForm = this.formBuilder.group({
      nomeFuncionario: ['', [Validators.required]],
      loginFuncionario: ['', [Validators.required]],
      emailFuncionario:['',[Validators.required]]

    });

  }

  CadastrarFuncionario(){
    this.router.navigateByUrl('cadastrofuncionario')
  }
  delete(funcionario:FuncionarioModel){
    this.service.delete(funcionario).subscribe(data => {
      console.log(data)
      this.ngOnInit();
    })

  }

  editIncorrect() {
    this.EditRowId = -2
    this.funcionarioForm.get('nomeFuncionario').setValue("");
    this.funcionarioForm.get('loginFuncionario').setValue("");
    this.funcionarioForm.get('emailFuncionario').setValue("");
  }

  editCorrect(funcionario: FuncionarioModel) {
    funcionario.nome = this.funcionarioForm.get('nomeFuncionario').value
    funcionario.login = this.funcionarioForm.get('loginFuncionario').value
    funcionario.email = this.funcionarioForm.get('emailFuncionario').value

    this.service.alteraFuncionario(funcionario).subscribe(data => {
      console.log(data)
      this.EditRowId = -2
      this.funcionarioForm.get('nomeFuncionario').setValue("");
      this.funcionarioForm.get('loginFuncionario').setValue("");
      this.funcionarioForm.get('emailFuncionario').setValue("");
      
    })
  }

  edit(funcionario:FuncionarioModel){
    this.EditRowId = funcionario.id
  }
}
