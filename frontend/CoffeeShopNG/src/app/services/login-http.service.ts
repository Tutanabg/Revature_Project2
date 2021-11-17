import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { User } from '../models/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class loginHttpService {

  constructor(private http: HttpClient) { }

  loginValidation(username: string, password: string): Observable<any> {
    return this.http.get('http://localhost:8080/login', { headers: {
    'username': `${username}`,
    'pa$$word': `${password}`}, observe: 'response'});
  }

}