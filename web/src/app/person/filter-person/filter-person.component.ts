import { Component, OnInit } from '@angular/core';
import {PersonService} from '../shared/person.service';
import {Person} from '../shared/person.model';
import {FormBuilder, FormControl} from '@angular/forms';

@Component({
  selector: 'app-filter-person',
  templateUrl: './filter-person.component.html',
  styleUrls: ['./filter-person.component.css']
})
export class FilterPersonComponent implements OnInit {

  filteredPersons: Array<Person>;
  filterForm = this.formBuilder.group({
    weight: new FormControl(' ')
  });

  constructor(
    private service: PersonService,
    private formBuilder: FormBuilder
  ) {
    this.filteredPersons = [];
  }

  ngOnInit(): void {
  }

  getFilteredPersons(): void{
    this.filteredPersons = [];
    this.service.getFilteredDocumentsByWeight(this.filterForm.controls.weight.value).subscribe(
      responsePersons => responsePersons['persons'].forEach(person =>
        this.filteredPersons.push(new Person(person.id, person.fullName, person.weight, person.dateOfBirth, person.bloodType))
      )
    );
  }
}
