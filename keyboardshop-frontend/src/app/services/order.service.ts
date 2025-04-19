import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Order } from '../models/order.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private httpClient = inject(HttpClient);
  private apiUrl = environment.orderUrl;

  public getOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.apiUrl);
  }

  public getOrdersByUserId(userId: number, lang: string): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${this.apiUrl}/users/${userId}`, { params: { lang }});
  }

  public createGuestOrder(orderData: Order): Observable<Order> {
    return this.httpClient.post<Order>(this.apiUrl, orderData);
  }

  public createUserOrder(orderData: Order): Observable<Order> {
    return this.httpClient.post<Order>(`${this.apiUrl}/user`, orderData);
  }
}
