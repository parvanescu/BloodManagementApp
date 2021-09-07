import { Component, OnInit } from '@angular/core';
import {HospitalService} from '../shared/hospital.service';
import {Hospital} from '../shared/hospital.model';
import {FormBuilder, FormControl} from '@angular/forms';

@Component({
  selector: 'app-filter-hospital',
  templateUrl: './filter-hospital.component.html',
  styleUrls: ['./filter-hospital.component.css']
})
export class FilterHospitalComponent implements OnInit {

  filteredHospitals: Array<Hospital>;
  form = this.formBuilder.group({
    minCapacity : new FormControl('')
  });


  constructor(
    private service: HospitalService,
    private formBuilder: FormBuilder
  ) {
    this.filteredHospitals = [];
  }

  ngOnInit(): void {
  }



  getFilteredHospitals(): void{
    this.filteredHospitals = [];
    this.service.getAllDocuments().subscribe(
      responseHospitals => responseHospitals['hospitals']
        .filter(hospital => hospital.capacity <= parseInt(this.form.controls.minCapacity.value, 10))
        .forEach( filteredHospital =>
        this.filteredHospitals.push(
          new Hospital(filteredHospital.id, filteredHospital.capacity, filteredHospital.name, filteredHospital.address))
      )
    );
  }

}
