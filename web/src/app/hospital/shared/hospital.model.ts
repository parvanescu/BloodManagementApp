export class Hospital{
  id: number;
  capacity: number;
  name: string;
  address: string;

  constructor(id: number, capacity: number, name: string, address: string) {
    this.id = id;
    this.capacity = capacity;
    this.name = name;
    this.address = address;
  }
}
