import { Component, OnInit } from '@angular/core';
import {DrugService} from '../shared/drug.service';
import {Drug} from '../shared/drug.model';

@Component({
  selector: 'app-get-all-drugs',
  templateUrl: './get-all-drugs.component.html',
  styleUrls: ['./get-all-drugs.component.css']
})
export class GetAllDrugsComponent implements OnInit {
  drugs: Array<Drug>;

  constructor(private service: DrugService) {
    this.drugs = [];

  }

  ngOnInit(): void {
    this.service.getAllDocuments().subscribe(
      responseDrugs => responseDrugs['drugs'].forEach(
        drug => this.drugs.push(new Drug(drug.id, drug.name, drug.recommendedAmount))
      )
    );
  }

}
