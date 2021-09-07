import { Component, OnInit } from '@angular/core';
import {HospitalService} from '../shared/hospital.service';
import {FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-hospital',
  templateUrl: './add-hospital.component.html',
  styleUrls: ['./add-hospital.component.css']
})
export class AddHospitalComponent implements OnInit {

  addForm = this.formBuilder.group({
    id: '',
    capacity: '',
    name: '',
    address: ''
  });

  constructor(
    private service: HospitalService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  addHospital(): void{
    this.service.addDocument(
      parseInt(this.addForm.get('id')?.value, 10),
      parseInt(this.addForm.get('capacity')?.value, 10),
      this.addForm.get('name')?.value,
      this.addForm.get('address')?.value
    ).subscribe();
    this.router.navigate(['/hospitals/get-all']);
  }

}
