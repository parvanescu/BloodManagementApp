import { Injectable } from '@angular/core';
import {Hospital} from './hospital.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HospitalService{
  private hospitalURL = 'http://localhost:8080/hospitals';
  constructor(private http: HttpClient) { }

  addDocument(id: number, capacity: number, name: string, address: string): Observable<Hospital> {
    return this.http.post<Hospital>(this.hospitalURL, new Hospital(id, capacity, name, address));
  }

  deleteDocument(id: number): Observable<Hospital> {
    return this.http.delete<Hospital>(this.hospitalURL + '/' + id);
  }

  getAllDocuments(): Observable<Hospital[]> {
    return this.http.get<Hospital[]>(this.hospitalURL);
  }

  getDocument(id: number): Observable<Hospital> {
    return this.http.get<Hospital>(this.hospitalURL + '/' + id);
  }

  updateDocument(id: number, capacity: number, name: string, address: string): Observable<Hospital> {
    return this.http.put<Hospital>(this.hospitalURL, new Hospital(id, capacity, name, address));
  }
}
