import { Component, OnInit } from '@angular/core';
import {TransfusionService} from '../shared/transfusion.service';
import {Transfusion} from '../shared/transfusion.model';

@Component({
  selector: 'app-get-all-transfusions',
  templateUrl: './get-all-transfusions.component.html',
  styleUrls: ['./get-all-transfusions.component.css']
})
export class GetAllTransfusionsComponent implements OnInit {
  transfusions: Array<Transfusion>;

  constructor(private service: TransfusionService) {
    this.transfusions = [];
  }

  ngOnInit(): void {
    this.service.getAllDocuments().subscribe(
      responseTransfusions => responseTransfusions['transfusions'].forEach(
        transfusion => {
          this.transfusions.push(new Transfusion(
            transfusion.id, transfusion.donor.id, transfusion.receiver.id, transfusion.doctor.id, transfusion.units));
        }
      )
    );
  }

}
