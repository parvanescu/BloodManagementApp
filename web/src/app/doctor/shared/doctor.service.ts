import { Injectable } from '@angular/core';
import {Doctor} from './doctor.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DatePipe} from '@angular/common';
import {Hospital} from '../../hospital/shared/hospital.model';

@Injectable({
  providedIn: 'root'
})
export class DoctorService{
  private doctorURL = 'http://localhost:8080/doctors';
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe
  ) { }

  addDocument(id: number, fullName: string, practicingSince: Date, hospitalId: number): Observable<Doctor> {
    return this.http.post<Doctor>(this.doctorURL,
      new Doctor(id, fullName, this.datePipe.transform(practicingSince, 'yyyy-MM-dd'), hospitalId));
  }

  deleteDocument(id: number): Observable<Doctor> {
    return this.http.delete<Doctor>(this.doctorURL + '/' + id);
  }

  getAllDocuments(): Observable<Doctor[]> {
    return this.http.get<Array<Doctor>>(this.doctorURL);
  }

  getDocument(id: number): Observable<Doctor> {
    return this.http.get<Doctor>(this.doctorURL + '/' + id);
  }

  updateDocument(id: number, newFullName: string, newPracticingSince: Date, newHospitalId: number): Observable<Doctor> {
    return this.http.put<Doctor>(this.doctorURL, new Doctor(id, newFullName, newPracticingSince.toLocaleDateString(), newHospitalId));
  }

  getReport(): Observable<Map<Hospital, Doctor[]>>{
    return this.http.get<Map<Hospital, Doctor[]>>(this.doctorURL + '/reports');
  }

  getFilteredDocuments(criteria: number): Observable<Doctor[]>{
    return this.http.get<Doctor[]>(this.doctorURL + '/filter/' + criteria);
  }
}
