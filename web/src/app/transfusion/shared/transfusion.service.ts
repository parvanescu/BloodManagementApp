import { Injectable } from '@angular/core';
import {Transfusion} from './transfusion.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Person} from "../../person/shared/person.model";

@Injectable({
  providedIn: 'root'
})
export class TransfusionService{
  private transfusionURL = 'http://localhost:8080/transfusions';
  constructor(private http: HttpClient) { }

  addDocument(id: number, donorID: number, receiverID: number, doctorID: number, units: number): Observable<Transfusion> {
    return this.http.post<Transfusion>(this.transfusionURL, new Transfusion(id, donorID, receiverID, doctorID, units));
  }

  deleteDocument(id: number): Observable<Transfusion> {
    return this.http.delete<Transfusion>(this.transfusionURL + '/' + id);
  }

  getAllDocuments(): Observable<Transfusion[]> {
    return this.http.get<Transfusion[]>(this.transfusionURL);
  }

  getDocument(id: number): Observable<Transfusion> {
    return this.http.get<Transfusion>(this.transfusionURL + '/' + id);
  }

  updateDocument(id: number, donorID: number, receiverID: number, doctorID: number, units: number): Observable<Transfusion> {
    return this.http.put<Transfusion>(this.transfusionURL, new Transfusion(id, donorID, receiverID, doctorID, units));
  }

  getDonors(): Observable<Person[]>{
    return this.http.get<Person[]>(this.transfusionURL + '/donors');
  }

  getReceivers(): Observable<Person[]>{
    return this.http.get<Person[]>(this.transfusionURL + '/receivers');
  }
}
