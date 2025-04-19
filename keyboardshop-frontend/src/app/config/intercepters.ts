import { HttpEvent, HttpHandlerFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';

export function authInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> {
  const loginService = inject(AuthService);
  const authToken = loginService.getToken();

  if (authToken != null) {
    const newRequest = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${authToken}`),
    });

    return next(newRequest);
  }

  return next(req);
}
