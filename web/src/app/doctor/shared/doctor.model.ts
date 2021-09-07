export class Doctor{
  id: number;
  fullName: string;
  practicingSince: string;
  hospitalId: number;

  constructor(id: number, fullName: string, practicingSince: string, hospitalId: number) {
    this.id = id;
    this.fullName = fullName;
    this.practicingSince = practicingSince;
    this.hospitalId = hospitalId;
  }
}
