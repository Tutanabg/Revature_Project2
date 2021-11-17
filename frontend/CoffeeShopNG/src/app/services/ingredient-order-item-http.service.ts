import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { IngredientOrderItem } from '../models/IngredientOrderItem';

@Injectable({providedIn: 'root'})
export class IngredientOrderItemHttpService {
  // Base url
  baseurl = 'http://localhost:8080/ingredientOrderItems/';
  
  constructor(private http: HttpClient) { }
  
  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  // Super post
  submitOrder(data): Observable<Array<Array<IngredientOrderItem>>> {
    console.log("hi");
    return this.http.post<Array<Array<IngredientOrderItem>>>("http://localhost:8080/ingredientOrderItems/submitOrder/", JSON.stringify(data), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }  

  
  // POST
  createIngredientOrderItem(data): Observable<IngredientOrderItem> {
    return this.http.post<IngredientOrderItem>(this.baseurl , JSON.stringify(data), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }  
  
  // GET
  getIngredientOrderItem(id): Observable<IngredientOrderItem> {
    return this.http.get<IngredientOrderItem>(this.baseurl + id)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }
  
  // GET
  getAllIngredientOrderItems(): Observable<Array<IngredientOrderItem>> {
    return this.http.get<Array<IngredientOrderItem>>(this.baseurl)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }
  
  // PUT
  updateIngredientOrderItem(id, data): Observable<IngredientOrderItem> {
    return this.http.put<IngredientOrderItem>(this.baseurl + id, JSON.stringify(data), this.httpOptions)
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