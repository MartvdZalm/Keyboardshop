import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private httpClient = inject(HttpClient);
  private apiUrl = environment.searchUrl;
  
  public getSearch(query: string): Observable<Product[]>  {
    return this.httpClient.get<Product[]>(this.apiUrl, {
      params: {
        query: query
      }
    });
  }
}
