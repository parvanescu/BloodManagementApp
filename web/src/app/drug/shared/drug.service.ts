import { Injectable } from '@angular/core';
import {Drug} from './drug.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DrugService{
  private drugURL = 'http://localhost:8080/drugs';
  constructor(private http: HttpClient) { }

  addDocument(id: number, name: string, recommendedAmount: number): Observable<Drug> {
    return this.http.post<Drug>(this.drugURL, new Drug(id, name, recommendedAmount));
  }

  deleteDocument(id: number): Observable<Drug> {
    return this.http.delete<Drug>(this.drugURL + '/' + id);
  }

  getAllDocuments(): Observable<Drug[]> {
    return this.http.get<Array<Drug>>(this.drugURL);
  }

  getDocument(id: number): Observable<Drug> {
    return this.http.get<Drug>(this.drugURL + '/' + id);
  }

  updateDocument(id: number, name: string, recommendedAmount: number): Observable<Drug> {
    return this.http.put<Drug>(this.drugURL, new Drug(id, name, recommendedAmount));
  }
}
