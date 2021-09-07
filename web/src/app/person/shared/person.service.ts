import { Injectable } from '@angular/core';
import {Person} from './person.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonService{
  private personURL = 'http://localhost:8080/persons';
  constructor(private http: HttpClient) { }

  addDocument(id: number, fullName: string, weight: number, dateOfBirth: Date, bloodType: string): Observable<Person> {
    return this.http.post<Person>(this.personURL, new Person(id, fullName, weight, dateOfBirth, bloodType));
  }

  deleteDocument(id: number): Observable<Person> {
    return this.http.delete<Person>(this.personURL + '/' + id);
  }

  getAllDocuments(): Observable<Person[]> {
    return this.http.get<Array<Person>>(this.personURL);
  }

  getDocument(id: number): Observable<Person> {
    return this.http.get<Person>(this.personURL + '/' + id);
  }

  updateDocument(id: number, fullName: string, weight: number, dateOfBirth: Date, bloodType: string): Observable<Person> {
    return this.http.put<Person>(this.personURL, new Person(id, fullName, weight, dateOfBirth, bloodType));
  }

  getSortedDocuments(): Observable<Person[]>{
    return this.http.get<Array<Person>>(this.personURL + '/sorted');
  }

  getFilteredDocumentsByWeight(weight: number): Observable<Person[]>{
    return this.http.get<Array<Person>>(this.personURL + '/jpafilter/' + weight);
  }
}
