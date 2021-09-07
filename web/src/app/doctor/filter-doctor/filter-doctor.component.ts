import { Component, OnInit } from '@angular/core';
import {DoctorService} from '../shared/doctor.service';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import {Doctor} from '../shared/doctor.model';

@Component({
  selector: 'app-filter-doctor',
  templateUrl: './filter-doctor.component.html',
  styleUrls: ['./filter-doctor.component.css']
})
export class FilterDoctorComponent implements OnInit {

  filteringForm = this.formBuilder.group({
    minYears: new FormControl(0, Validators.min(0))
  });

  filteredDoctors: Array<Doctor>;

  constructor(
    private service: DoctorService,
    private formBuilder: FormBuilder
  ) {
    this.filteredDoctors = [];
  }

  ngOnInit(): void {
  }

  getFilteredDoctors(): void {
    this.filteredDoctors = [];
    this.service.getFilteredDocuments(this.filteringForm.controls.minYears.value).subscribe(
      response => response['doctors'].forEach(doctor => {
        this.filteredDoctors.push(new Doctor(doctor.id, doctor.fullName, doctor.practicingSince, doctor.hospitalId));
      })
    );
  }
}
