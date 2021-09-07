import { Component, OnInit } from '@angular/core';
import {HospitalService} from '../shared/hospital.service';
import {Hospital} from '../shared/hospital.model';
import {FormControl} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-delete-hospital',
  templateUrl: './delete-hospital.component.html',
  styleUrls: ['./delete-hospital.component.css']
})
export class DeleteHospitalComponent implements OnInit {

  hospitals: Array<Hospital>;
  id = new FormControl('');
  showConfirmation: boolean;

  constructor(
    private service: HospitalService,
    private router: Router
  ) {
    this.hospitals = [];
    this.showConfirmation = false;
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

  updateHospitalId(hospitalName: string): void{
    this.id.setValue(this.hospitals.find(hospital => hospital.name === hospitalName).id);
  }

  deleteHospital(): void{
    this.service.deleteDocument(parseInt(this.id.value, 10)).subscribe();
    this.router.navigate(['/hospitals/get-all']);
  }

  updatePopUp(): void{
    this.showConfirmation = !this.showConfirmation;
  }

}
