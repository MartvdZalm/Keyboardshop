import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product, ProductSave } from '../models/product.model';
import { Pagination } from '../models/pagination.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { LanguageService } from './language.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private httpClient = inject(HttpClient);
  private languageService = inject(LanguageService);
  private apiUrl = environment.productsUrl;

  public getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl, {
      params: { lang: this.languageService.currentLang() },
    });
  }

  public getPaginatedProductsByCategory(
    page: number,
    size: number,
    categoryId: number,
    lang = 'en'
  ): Observable<Pagination> {
    return this.httpClient.get<Pagination>(`${this.apiUrl}/${categoryId}/pagination`, {
      params: {
        size,
        page,
        lang,
      },
    });
  }

  public getPaginatedProducts(
    page: number,
    size: number,
  ): Observable<Pagination> {
    return this.httpClient.get<Pagination>(`${this.apiUrl}/pagination`, {
      params: {
        size,
        page,
        lang: this.languageService.currentLang(),
      },
    });
  }

  public getProductById(productId: number,  lang: string): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrl}/${productId}`, {
      params: {
        lang
      }
    });
  }

  public getProductBySlug(productSlug: string, lang: string): Observable<Product> {
    return this.httpClient.get<Product>(`${this.apiUrl}/slug/${productSlug}`, {
      params: {
        lang
      },
    });
  }

  public createProduct(productData: ProductSave): Observable<string> {
    return this.httpClient.post<string>(this.apiUrl, productData);
  }

  public updateProduct(
    productId: number,
    productData: ProductSave
  ): Observable<string> {
    return this.httpClient.put<string>(
      `${this.apiUrl}/${productId}`,
      productData
    );
  }

  public softDeleteProduct(productId: number): Observable<string> {
    return this.httpClient.put<string>(
      `${this.apiUrl}/active/${productId}`,
      {}
    );
  }

  public deleteProduct(productId: number): Observable<string> {
    return this.httpClient.delete<string>(`${this.apiUrl}/${productId}`);
  }
}
