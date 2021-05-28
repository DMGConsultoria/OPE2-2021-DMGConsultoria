import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SaidaComponent } from "./saida.component";

const routes: Routes = [
    {path:'saida', component:SaidaComponent }
     ];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })


export class SaidaRoutingModule{}
