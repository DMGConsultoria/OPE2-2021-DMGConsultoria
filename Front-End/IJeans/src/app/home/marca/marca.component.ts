import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { MarcaModel } from "src/app/shared/model/marca.model";
import { AlertModalService } from "src/app/shared/services/alert-modal.service";
import { MarcaService } from "./service/marca-service";

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})



export class MarcaComponent implements OnInit {

  marcas: Array<MarcaModel>
  marcasAtuais: Array<MarcaModel>

  EditRowId: any = '';
  marcaForm: FormGroup;

  constructor(private service: MarcaService, private router: Router, private formBuilder: FormBuilder, private alertService: AlertModalService) { }

  ngOnInit(): void {
    this.service.findAll().subscribe(data => {
      this.marcas = data;
      this.marcasAtuais = data;
    })

    this.marcaForm = this.formBuilder.group({
      nomeMarca: ['', [Validators.required]]
    });
  }

  cadastrarMarca(){
    this.router.navigateByUrl('cadastromarca')
  }

  delete(marca: MarcaModel) {
    this.service.delete(marca.id).subscribe(data => {
      this.alertService.showSucess("Marca Excluida com sucesso")
      this.ngOnInit();
    }),err => {
      this.alertService.showAlertDanger(err.error.message)
    }
  }

  editIncorrect() {
    this.EditRowId = -2
    this.marcaForm.get('nomeMarca').setValue("");
  }

  editCorrect(marca: MarcaModel) {
    marca.nome = this.marcaForm.get('nomeMarca').value

    this.service.alterar(marca).subscribe(data => {
      this.EditRowId = -2
      this.marcaForm.get('nomeMarca').setValue("");
      this.alertService.showSucess('Alterado com sucesso')
      this.ngOnInit()
    }, err => {
      this.alertService.showAlertDanger(err.error.message)
    })
  }

  edit(marca: MarcaModel) {
    this.EditRowId = marca.id
  }

  buscarMarca(nome) {
    if (nome === "")
      this.marcasAtuais = this.marcas
    else {
      this.service.findByNome(nome).subscribe(data => {
        this.marcasAtuais = data
      })
    }
  }
  
}
