import { Component, OnInit } from '@angular/core';
import {DoctorService} from '../shared/doctor.service';
import {HospitalService} from '../../hospital/shared/hospital.service';
import {Hospital} from '../../hospital/shared/hospital.model';
import {Doctor} from '../shared/doctor.model';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.css']
})
export class AddDoctorComponent implements OnInit {
  hospitals: Array<Hospital>;
  doctorForm = this.formBuilder.group({
    id: new FormControl('', Validators.required),
    name: new FormControl('', Validators.required),
    practicingSince: new FormControl('', Validators.required),
    hospital: new FormControl('', Validators.required)
  });


  constructor(
    private service: DoctorService,
    private hospitalService: HospitalService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.hospitals = [];
  }

  ngOnInit(): void {
    this.hospitalService.getAllDocuments().subscribe(
      response => response['hospitals'].forEach(hospital =>
      this.hospitals.push(new Hospital(hospital.id, hospital.capacity, hospital.name, hospital.address)))
    );
  }

  addHospital(): void{
    const id = this.doctorForm.controls.id.value;
    const name = this.doctorForm.controls.name.value;
    const practicingSince = this.doctorForm.controls.practicingSince.value;
    const doctorId = this.hospitals.find(hospital => hospital.name === this.doctorForm.controls.hospital.value).id;
    this.service.addDocument(id, name, practicingSince, doctorId).subscribe();
    this.router.navigate(['/doctors/get-all']);
  }

}
