import {Drug} from '../../drug/shared/drug.model';
import {Person} from '../../person/shared/person.model';
import {Doctor} from '../../doctor/shared/doctor.model';


export class PrescriptionDTO {
  id: number;
  doctorID: number;
  personID: number;
  drugs: number[];
  releaseDate: Date;

  constructor(id: number, doctorID: number, personID: number, drugs: number[], releaseDate: Date) {
    this.id = id;
    this.doctorID = doctorID;
    this.personID = personID;
    this.drugs = drugs;
    this.releaseDate = releaseDate;
  }
}
