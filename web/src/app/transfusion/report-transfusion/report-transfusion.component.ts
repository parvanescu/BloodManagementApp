import { Component, OnInit } from '@angular/core';
import {TransfusionService} from '../shared/transfusion.service';
import {Person} from '../../person/shared/person.model';

@Component({
  selector: 'app-report-transfusion',
  templateUrl: './report-transfusion.component.html',
  styleUrls: ['./report-transfusion.component.css']
})
export class ReportTransfusionComponent implements OnInit {
  donors: Array<Person>;
  receivers: Array<Person>;
  displayedColumns = ['id', 'fullName', 'weight', 'dateOfBirth', 'bloodType'];

  constructor(
    private service: TransfusionService
  ) {
    this.donors = [];
    this.receivers = [];
  }

  ngOnInit(): void {
    this.service.getDonors().subscribe(response => {
      response['persons'].forEach(donor => {
        this.donors.push(new Person(donor.id, donor.fullName, donor.weight, donor.dateOfBirth, donor.bloodType));
      });
    });
    this.service.getReceivers().subscribe(response => {
      response['persons'].forEach(receiver => {
        this.receivers.push(new Person(receiver.id, receiver.fullName, receiver.weight, receiver.dateOfBirth, receiver.bloodType));
      });
    });
  }

}
