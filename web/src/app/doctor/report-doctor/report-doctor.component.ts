import { Component, OnInit } from '@angular/core';
import {DoctorService} from '../shared/doctor.service';
import {Hospital} from '../../hospital/shared/hospital.model';
import {Doctor} from '../shared/doctor.model';

@Component({
  selector: 'app-report-doctor',
  templateUrl: './report-doctor.component.html',
  styleUrls: ['./report-doctor.component.css']
})
export class ReportDoctorComponent implements OnInit {

  doctors: Map<Hospital, Doctor[]>;

  constructor(
    private service: DoctorService
  ) { }

  ngOnInit(): void {
    this.service.getReport().subscribe(response => {

      for (const item of response){
        console.log(item);
      }
    });
  }

}
