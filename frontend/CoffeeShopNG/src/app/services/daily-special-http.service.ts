import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { DailySpecial } from '../models/DailySpecial';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DailySpecialHttpService {

  //base url
  baseURL = 'http://localhost:8080/dailySpecials/'

  constructor(private http: HttpClient) { }

  //http headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  
  // GET
  getAllDailySpecials(): Observable<Array<DailySpecial>> {
    return this.http.get<Array<DailySpecial>>(this.baseURL)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }
  
  // Error handling
  errorHandl(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
