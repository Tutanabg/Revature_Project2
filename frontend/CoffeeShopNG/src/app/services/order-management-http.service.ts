import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from '../models/Order';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { OrderItem } from '../models/OrderItem';

@Injectable({
  providedIn: 'root'
})
export class OrderManagementHttpService {

  constructor(private http: HttpClient) { }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  baseurl:string =  'http://localhost:8080/orders/';

  // POST
  CreateOrder(data): Observable<Order> {
    return this.http.post<Order>(this.baseurl , JSON.stringify(data), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }  
  
  // GET
  GetOrder(id): Observable<Order> {
    return this.http.get<Order>(this.baseurl + id)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }
  
  // GET
  GetAllOrders(): Observable<Array<Order>> {
    return this.http.get<Array<Order>>(this.baseurl)
    .pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }
  
  // PUT
  UpdateIngredientOrderItem(id, data): Observable<Order> {
    return this.http.put<Order>(this.baseurl + id, JSON.stringify(data), this.httpOptions)
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

  //extra services needed
  getOrdersToday(): Observable<Order[]>{
    let today = new Date();
    let day = new Date( today.getFullYear(), today.getMonth(), today.getDate());
    let dayms = day.getTime();
    return this.http.get<Order[]>('http://localhost:8080/orders/day/' + dayms);
  }

  getOrdersDay(day: number): Observable<Order[]>{
    return this.http.get<Order[]>('http://localhost:8080/orders/day/' + day);
  }

  getOrderItems(): Observable<OrderItem[]>{
    let today = new Date();
    console.log(today.getUTCMonth());
    let day = new Date( today.getFullYear(), today.getMonth(), today.getDate());
    let month = day.getMonth() + 1;

    let todayString = day.getFullYear() + '-' + month + "-" + day.getDate();
    return this.http.get<OrderItem[]>('http://localhost:8080/orderItems/today/'+todayString);
  }

  advanceOrder(order: Order): Observable<Order> {
    console.log(order);
    let url = 'http://localhost:8080/orders/advance/' + order.orderID;
    console.log(url);
    return this.http.put<Order>(url, JSON.stringify(order), this.httpOptions);
  }
}
