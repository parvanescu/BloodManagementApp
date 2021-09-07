import {Component, OnInit} from '@angular/core';
import {HospitalService} from '../shared/hospital.service';
import {Hospital} from '../shared/hospital.model';
import {Router} from '@angular/router';
import {MatTableModule} from '@angular/material/table';

@Component({
  selector: 'app-get-all-hospitals',
  templateUrl: './get-all-hospitals.component.html',
  styleUrls: ['./get-all-hospitals.component.css']
})
export class GetAllHospitalsComponent implements OnInit {
  hospitals: Array<Hospital>;
  displayedColumns =  ['id', 'capacity', 'name', 'address'];

  constructor(
    private service: HospitalService,
    private router: Router
  ) {
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


  getDetails(hospitalId: number): void {
    this.router.navigate(['/hospitals/get', {id: hospitalId}]);
  }

  sortHospitals(): void{
    this.hospitals = this.hospitals.sort((hosp1, hosp2) => hosp1.capacity - hosp2.capacity);
  }
}
