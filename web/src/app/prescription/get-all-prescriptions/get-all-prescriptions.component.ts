import { Component, OnInit } from '@angular/core';
import {PrescriptionService} from '../shared/prescription.service';
import {Prescription} from '../shared/prescription.model';
import {Drug} from "../../drug/shared/drug.model";

@Component({
  selector: 'app-get-all-prescriptions',
  templateUrl: './get-all-prescriptions.component.html',
  styleUrls: ['./get-all-prescriptions.component.css']
})
export class GetAllPrescriptionsComponent implements OnInit {
  prescriptions: Array<Prescription>;
  constructor(private service: PrescriptionService) {
    this.prescriptions = [];
  }

  ngOnInit(): void {
    this.service.getAllDocuments().subscribe(
      responsePrescriptions => responsePrescriptions['prescriptions'].forEach(prescription => {
        console.log(prescription);
        const drugs = [];
        prescription.drugs.forEach(drug => drugs.push(new Drug(drug.id, drug.name, drug.recommendedAmount)));
        this.prescriptions.push(new Prescription(
          prescription.id,
          prescription.doctor.id,
          prescription.person.id,
          drugs,
          prescription.releaseDate));
      })
    );
  }

}
