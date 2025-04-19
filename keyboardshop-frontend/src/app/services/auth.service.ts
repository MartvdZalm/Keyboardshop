import { inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { Token } from '../models/token.model';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { Login, Register } from '../models/auth.model';
import { CustomJwtPayload } from '../models/custom-jwt-payload';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authUrl = environment.authUrl;
  private httpClient = inject(HttpClient);
  private router = inject(Router);
  private token = signal<string | null>(null);

  public constructor() {
    this.loadTokenFromLocalStorage();
  }

  public login(login: Login): Observable<Token> {
    return this.httpClient
      .post<Token>(`${this.authUrl}/login`, {
        email: login.email,
        password: login.password,
      })
      .pipe(
        tap((response) => {
          this.token.set(response.token);
          this.saveTokenInLocalStorage(response.token);
        })
      );
  }

  public register(register: Register): Observable<string> {
    return this.httpClient.post<string>(`${this.authUrl}/register`, {
      firstName: register.firstName,
      lastName: register.lastName,
      email: register.email,
      password: register.password,
    });
  }

  public logout(): void {
    localStorage.removeItem('authToken');
    this.token.set(null);
    this.router.navigate(['/login']);
  }

  public isAuthenticated(): boolean {
    const token = this.token();
    return token !== null && !this.isTokenExpired(token);
  }

  public getRole(): string | null {
    const decodedToken = this.decodeToken();
    if (decodedToken) {
      return decodedToken.role;
    }
    return null;
  }

  public getToken(): string | null {
    return this.token();
  }

  public hasRole(role: string): boolean {
    return this.getRole() === role;
  }

  private isTokenExpired(token: string): boolean {
    const decoded = jwtDecode<JwtPayload>(token);
    if (!decoded.exp) return false;

    const expirationDate = new Date(0);
    expirationDate.setUTCSeconds(decoded.exp);
    return expirationDate.valueOf() < new Date().valueOf();
  }

  private saveTokenInLocalStorage(token: string): void {
    localStorage.setItem('authToken', token);
  }

  private loadTokenFromLocalStorage(): void {
    this.token.set(localStorage.getItem('authToken'));
  }

  private decodeToken(): CustomJwtPayload | null {
    const token = this.token();
    if (!token) {
      return null;
    }

    try {
      return jwtDecode<CustomJwtPayload>(token);
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }
}
