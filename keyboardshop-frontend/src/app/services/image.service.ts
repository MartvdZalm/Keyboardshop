import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ImageService {
  private httpClient = inject(HttpClient);
  private apiUrl = environment.imageUrl;

  public uploadImage(formData: FormData): Observable<string> {
    return this.httpClient.post<string>(this.apiUrl, formData);
  }
}
