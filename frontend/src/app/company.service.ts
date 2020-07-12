import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  companiesUrl = 'http://localhost:8080/companyStocksSTD'

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<any[]>(`${this.companiesUrl}`)

  }
}
