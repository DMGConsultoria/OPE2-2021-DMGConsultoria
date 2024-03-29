import { Injectable } from '@angular/core';
import { environment } from './enviroment/environment';


@Injectable({
  providedIn: 'root'
})
export class EnvService {

  urlAPI : string;
  production : boolean;
  storageKeysConfig;

  constructor() { 
    this.urlAPI = environment.urlAPI;
    this.production = environment.production;
    this.storageKeysConfig = environment.storageKeysConfig;
  }
}
