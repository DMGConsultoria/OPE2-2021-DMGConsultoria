import { Component } from "@angular/core";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ProdutoService } from "src/app/home/produto/service/service-produto.service";
import { AlertModalService } from "src/app/shared/services/alert-modal.service";
import { EstoqueService } from "../../service/estoque.service";



@Component({
  selector: 'app-saida',
  templateUrl: './saida.component.html',
  styleUrls: ['./saida.component.css']
})


export class SaidaComponent {

  cadastraProdutoForm: FormGroup;
  

  constructor(private formBuilder: FormBuilder,private alertService:AlertModalService, private service: ProdutoService, private router: Router,  private route: ActivatedRoute,private serviceEstoque: EstoqueService) {
  }

  produtoNome;
  produtoQuantidade;
  produtoMarca;
  produtoTipo;
  produtoTamanho;
  produtoValor;

  produto;
  id;

  ngOnInit() {

    this.route.params.subscribe(params => this.id = params['id']);

    
    if(this.id != null || this.id != undefined || this.id != ""){
       this.service.findById(this.id).subscribe(data => {
        this.produtoNome=data['nome'];
        this.produtoQuantidade;
        this.produtoMarca=data['marca']['nome'];
        this.produtoTipo=data['tipo_produto']['nome'];
        this.produtoTamanho=data['tamanho']['id'];
        this.produtoValor;
        this.produto=data
        
      })
    }
   

    this.cadastraProdutoForm = this.formBuilder.group({
      valor: ['', [Validators.required, Validators.pattern('^-?[0-9\.]+$')]],
      quantidade: ['', [Validators.required, Validators.pattern('^-?[0-9\]+$')],],
    });
  }

 
  onSubmit() {
    // Verifica ao enviar se os dados informados são validos
    if (
      this.cadastraProdutoForm.get('valor').valid &&
      this.cadastraProdutoForm.get('quantidade').valid
    ) {
      if( this.cadastraProdutoForm.get('quantidade').value >0 && this.cadastraProdutoForm.get('valor').value >0){
        let movimentacao = {
          quantidade: this.cadastraProdutoForm.get('quantidade').value,
          status: false,
          preco: this.cadastraProdutoForm.get('valor').value,
          produto: this.produto,
          fornecedor: null,
        }
        
       this.serviceEstoque.cadastrarEstoqueJaExistente(movimentacao).subscribe(data =>{
         if(data == null){
           this.alertService.showSucess('Registro de saida de produto realizada com sucesso!')
         }
      },err =>{
        this.alertService.showAlertDanger(err.error.message)
      })
  }else{
    this.alertService.showAlertDanger('Campos invalidos')
  }
}
  }
  voltarHome() {
    this.router.navigateByUrl("home")
  }

  isErrorCampo(nomeCampo) {
    return (!this.cadastraProdutoForm.get(nomeCampo).valid && this.cadastraProdutoForm.get(nomeCampo).touched);
  }
}


