import { Component, OnInit } from '@angular/core';
import {PrescriptionService} from '../shared/prescription.service';
import {Prescription} from "../shared/prescription.model";
import {FormBuilder, FormControl} from "@angular/forms";

@Component({
  selector: 'app-report-prescription',
  templateUrl: './report-prescription.component.html',
  styleUrls: ['./report-prescription.component.css']
})
export class ReportPrescriptionComponent implements OnInit {

  prescriptions: Array<Prescription>;
  filteringForm = this.formBuilder.group({
    date: new FormControl(new Date())
  });

  constructor(
    private service: PrescriptionService,
    private formBuilder: FormBuilder
  ) {
    this.prescriptions = [];
  }

  ngOnInit(): void {
  }

  getPrescriptions(): void{
    this.service.getPrescriptionsByDay(this.filteringForm.controls.date.value).subscribe(
      response => response['prescriptions'].forEach(prescription => {
        console.log(prescription);
        this.prescriptions.push(
        new Prescription(prescription.id, prescription.doctor.id, prescription.person.id, prescription.drugs, prescription.releaseDate));
      }
    ));
  }
}
