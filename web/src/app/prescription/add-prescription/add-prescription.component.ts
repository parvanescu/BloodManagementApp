import { Component, OnInit } from '@angular/core';
import {PrescriptionService} from '../shared/prescription.service';
import {PersonService} from '../../person/shared/person.service';
import {DrugService} from '../../drug/shared/drug.service';
import {DoctorService} from '../../doctor/shared/doctor.service';
import {FormBuilder, FormControl} from '@angular/forms';
import {Drug} from '../../drug/shared/drug.model';
import {Person} from '../../person/shared/person.model';
import {Doctor} from '../../doctor/shared/doctor.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-prescription',
  templateUrl: './add-prescription.component.html',
  styleUrls: ['./add-prescription.component.css']
})
export class AddPrescriptionComponent implements OnInit {

  drugs: Array<Drug>;
  persons: Array<Person>;
  doctors: Array<Doctor>;
  selectedDrugs: Array<Drug>;

  prescriptionForm = this.formBuilder.group({
    drug: new FormControl(''),
    person: new FormControl(''),
    doctor: new FormControl(''),
    id: new FormControl('')
  });

  constructor(
    private prescriptionService: PrescriptionService,
    private personService: PersonService,
    private drugService: DrugService,
    private doctorService: DoctorService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.drugs = [];
    this.persons = [];
    this.doctors = [];
    this.selectedDrugs = [];
  }

  ngOnInit(): void {
    this.drugService.getAllDocuments().subscribe(
      responseDrugs => responseDrugs['drugs'].forEach(
        drug => this.drugs.push(new Drug(drug.id, drug.name, drug.recommendedAmount))
      )
    );

    this.personService.getAllDocuments().subscribe(
      responsePersons => {
        responsePersons['persons'].forEach(person => {
          this.persons.push(new Person(person.id, person.fullName, person.weight, person.dateOfBirth, person.bloodType));
        });

      }
    );

    this.doctorService.getAllDocuments().subscribe(
      doctors => {
        doctors['doctors'].forEach(doctor => {
          this.doctors.push(new Doctor(doctor.id, doctor.fullName, doctor.practicingSince, doctor.hospitalId));
        });
      }
    );
  }

  addPrescription(): void {
    const id = this.prescriptionForm.controls.id.value;
    const doctorId = this.doctors.find(doctor => doctor.fullName === this.prescriptionForm.controls.doctor.value).id;
    const personId = this.persons.find(person => person.fullName === this.prescriptionForm.controls.person.value).id;

    this.prescriptionService.addDocument(id, doctorId, personId, this.selectedDrugs.map(drug => drug.id)).subscribe();
    this.router.navigate(['/prescriptions/get-all']);
  }

  addDrug(): void {
    this.selectedDrugs.push(this.drugs.find(drug => drug.name === this.prescriptionForm.controls.drug.value));
  }
}
