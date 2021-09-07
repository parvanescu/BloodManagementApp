import { Injectable } from '@angular/core';
import {Prescription} from './prescription.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Drug} from '../../drug/shared/drug.model';
import {PrescriptionDTO} from './prescription-dto.model';
import {DatePipe} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService{
  private prescriptionURL = 'http://localhost:8080/prescriptions';
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe
  ) { }

  addDocument(id: number, doctorID: number, personID: number, drugs: number[]): Observable<Prescription> {
    return this.http.post<Prescription>(this.prescriptionURL, new PrescriptionDTO(id, doctorID, personID, drugs, new Date()));
  }

  deleteDocument(id: number): Observable<Prescription> {
    return this.http.delete<Prescription>(this.prescriptionURL + '/' + id);
  }

  getAllDocuments(): Observable<Prescription[]> {
    return this.http.get<Prescription[]>(this.prescriptionURL);
  }

  getDocument(id: number): Observable<Prescription> {
    return this.http.get<Prescription>(this.prescriptionURL + '/' + id);
  }

  updateDocument(id: number, doctorID: number, personID: number, drugs: Drug[], releaseDate: Date): Observable<Prescription> {
    return this.http.put<Prescription>(this.prescriptionURL, new Prescription(id, doctorID, personID, drugs, releaseDate));
  }

  getPrescriptionsByDay(date: Date): Observable<Prescription[]>{
    return this.http.get<Prescription[]>(this.prescriptionURL + '/filteredByDay/' +
      this.datePipe.transform(date, 'yyyy-MM-dd'));
  }
}
