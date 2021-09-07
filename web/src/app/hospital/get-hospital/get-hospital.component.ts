import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Hospital} from "../shared/hospital.model";
import {HospitalService} from "../shared/hospital.service";

@Component({
  selector: 'app-get-hospital',
  templateUrl: './get-hospital.component.html',
  styleUrls: ['./get-hospital.component.css']
})
export class GetHospitalComponent implements OnInit {

  private userId : string
  hospital : Hospital

  constructor(
    private route: ActivatedRoute,
    private service: HospitalService
  ) {
    this.userId = ""
    this.hospital= new Hospital(0,0,"","")
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get("id")
    this.userId= id === null? "":id

    this.service.getDocument(parseInt(this.userId)).subscribe(
      res => this.hospital = res
    )
  }

}
