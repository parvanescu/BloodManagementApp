import { Component, OnInit } from '@angular/core';
import {PersonService} from '../shared/person.service';
import {Person} from '../shared/person.model';

@Component({
  selector: 'app-get-all-persons',
  templateUrl: './get-all-persons.component.html',
  styleUrls: ['./get-all-persons.component.css']
})
export class GetAllPersonsComponent implements OnInit {
  persons: Array<Person>;
  constructor(private service: PersonService) {
    this.persons = [];

  }

  ngOnInit(): void {
    this.service.getAllDocuments().subscribe(
      responsePersons => {
        responsePersons['persons'].forEach(person => {
          this.persons.push(new Person(person.id, person.fullName, person.weight, person.dateOfBirth, person.bloodType));
        });

      }
    );
  }

  getSorted(): void{
    this.persons = [];
    this.service.getSortedDocuments().subscribe(
      responsePersons => responsePersons['persons'].forEach(person =>
      this.persons.push(new Person(person.id, person.fullName, person.weight, person.dateOfBirth, person.bloodType))
      )
    );
  }

}
