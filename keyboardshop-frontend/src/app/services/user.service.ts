import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private httpClient = inject(HttpClient);
  private apiUrl = environment.userUrl;

  public getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.apiUrl);
  }

  public getUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/profile`);
  }

  public updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(this.apiUrl, {
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      streetName: user.streetName,
      houseNumber: user.houseNumber,
      postalCode: user.postalCode,
      city: user.city,
      phoneNumber: user.phoneNumber
    });
  }
}
