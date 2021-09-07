import { Component, OnInit } from '@angular/core';
import {HospitalService} from '../shared/hospital.service';
import {Hospital} from '../shared/hospital.model';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-update-hospital',
  templateUrl: './update-hospital.component.html',
  styleUrls: ['./update-hospital.component.css']
})
export class UpdateHospitalComponent implements OnInit {

  hospitals: Array<Hospital>;
  updateForm = this.formBuilder.group({
    capacity: new FormControl({value: ''}, Validators.required),
    name: new FormControl({value: ''}, Validators.required),
    address: new FormControl({value: ''}, Validators.required),
    id: new FormControl({value: '', disabled: true}, Validators.required)
  });
  currentHospital = '';

  constructor(
    private service: HospitalService,
    private formBuilder: FormBuilder,
    private router: Router) {
    this.hospitals = [];
  }

  ngOnInit(): void {
    this.service.getAllDocuments().subscribe(
      responseHospitals => {
        responseHospitals['hospitals'].forEach(hospital => {
            this.hospitals.push(new Hospital(hospital.id, hospital.capacity, hospital.name, hospital.address));
          }
        );
      }
    );
  }

  updateHospital(): void{
    const capacity = parseInt(this.updateForm.get('capacity')?.value, 10);
    const name = this.updateForm.get('name')?.value;
    const address = this.updateForm.get('address')?.value;
    const id = parseInt(this.updateForm.get('id')?.value, 10);
    this.service.updateDocument(id, capacity, name, address).subscribe();
    this.router.navigate(['/hospitals/get-all']);
  }

  changeSelection(newSelection: string): void{
    this.currentHospital = newSelection;
    const hosp = this.hospitals.find(hospital => hospital.name === this.currentHospital);
    this.updateForm.controls.capacity.setValue(hosp.capacity);
    this.updateForm.controls.name.setValue(hosp.name);
    this.updateForm.controls.address.setValue(hosp.address);
    this.updateForm.controls.id.setValue(hosp.id);
  }

}
