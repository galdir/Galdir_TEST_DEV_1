import { Component, OnInit } from '@angular/core';
import { CompanyService } from  '../company.service';


@Component({
  selector: 'app-companies-list',
  templateUrl: './companies-list.component.html',
  styleUrls: ['./companies-list.component.scss']
})
export class CompaniesListComponent implements OnInit {

  

  /* companies = [
    { name: 'Nitryx', segment: 'AI'},
    { name: 'Gerdau', segment: 'Metalurgy'}
  ]

  constructor() { }

  ngOnInit(): void {
  
  } */

  companies: Array<any>;

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
    this.list()
  }

  list(){
    this.companyService.listar().subscribe(data => this.companies = data);

  }

}
