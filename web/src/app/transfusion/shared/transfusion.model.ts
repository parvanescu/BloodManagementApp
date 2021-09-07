export class Transfusion{
  id: number;
  donorId: number;
  receiverId: number;
  doctorId: number;
  units: number;


  constructor(id: number, donorID: number, receiverID: number, doctorID: number, units: number) {
    this.id = id;
    this.donorId = donorID;
    this.receiverId = receiverID;
    this.doctorId = doctorID;
    this.units = units;
  }
}
