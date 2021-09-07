import {Drug} from '../../drug/shared/drug.model';

export class Prescription{
  id: number;
  doctorId: number;
  personId: number;
  drugs: Drug[];
  releaseDate: Date;

  constructor(id: number, doctorID: number, personID: number, drugs: Drug[], releaseDate: Date) {
    this.id = id;
    this.doctorId = doctorID;
    this.personId = personID;
    this.drugs = drugs;
    this.releaseDate = releaseDate;
  }

}
