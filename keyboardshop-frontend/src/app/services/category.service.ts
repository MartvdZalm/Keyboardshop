import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../models/category.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private httpClient = inject(HttpClient);
  private apiUrl = environment.categoriesUrl;

  public getCategories(lang = 'en'): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.apiUrl, { params: { lang }});
  }

  public getCategoryById(categoryId: number, lang: string): Observable<Category> {
    return this.httpClient.get<Category>(`${this.apiUrl}/${categoryId}`, {
      params: {
        lang
      }
    });
  }

  public getCategoryBySlug(slug: string, lang = 'en'): Observable<Category> {
    return this.httpClient.get<Category>(`${this.apiUrl}/slug/${slug}`, { params: { lang } });
  }
}
