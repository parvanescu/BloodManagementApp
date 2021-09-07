import { Component, OnInit } from '@angular/core';
import {DoctorService} from '../shared/doctor.service';
import {Doctor} from '../shared/doctor.model';

@Component({
  selector: 'app-get-all-doctors',
  templateUrl: './get-all-doctors.component.html',
  styleUrls: ['./get-all-doctors.component.css']
})
export class GetAllDoctorsComponent implements OnInit {
  public doctors: Array<Doctor>;

  constructor(private service: DoctorService) {
    this.doctors = [];
  }

  ngOnInit(): void {
    this.service.getAllDocuments().subscribe(
      doctors => {
        doctors['doctors'].forEach(doctor => {
          this.doctors.push(new Doctor(doctor.id, doctor.fullName, doctor.practicingSince, doctor.hospitalId));
        });
      }
    );
  }

}
