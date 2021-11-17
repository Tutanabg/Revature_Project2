import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OrderItem } from '../models/OrderItem';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderItemHttpService {

  baseurl = 'http://localhost:8080/orderItem/';

  constructor(private http: HttpClient) { }

  

  private postHeaders = new HttpHeaders({ 'Context-Type': 'application/json' });


  addOrderItem(orderitem: OrderItem): Observable<OrderItem> {
    return this.http.post<OrderItem>(this.baseurl, orderitem, { headers: this.postHeaders }).pipe(
      retry(1),
      catchError(this.errorHandl)
    );
  }

  getAllOrderItems(): Observable<OrderItem> {
    return this.http.get<OrderItem>(this.baseurl).pipe(
      retry(1),
      catchError(this.errorHandl)
    );
  }


  getOrderItem(id): Observable<OrderItem> {
    return this.http.get<OrderItem>(this.baseurl + id).pipe(
      retry(1),
      catchError(this.errorHandl)
    );
  }

  updateOrderItem(id, data): Observable<OrderItem> {
    return this.http.put<OrderItem>(this.baseurl + id, JSON.stringify(data),{ headers: this.postHeaders }).pipe(
      retry(1),
      catchError(this.errorHandl)
    );
  }

  deleteOrderItem(id): Observable<OrderItem[]> {
    return this.http.delete<OrderItem[]>(this.baseurl + id,{ headers: this.postHeaders }).pipe(
      retry(1),
      catchError(this.errorHandl)
    );;
  }
  
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
