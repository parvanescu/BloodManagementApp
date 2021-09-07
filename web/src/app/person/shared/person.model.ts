export class Person {
  id: number;
  fullName: string;
  weight: number;
  dateOfBirth: Date;
  bloodType: string;


  constructor(id: number, fullName: string, weight: number, dateOfBirth: Date, bloodType: string) {
    this.id = id;
    this.fullName = fullName;
    this.weight = weight;
    this.dateOfBirth = dateOfBirth;
    this.bloodType = bloodType;
  }
}
