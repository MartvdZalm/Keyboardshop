import { Component, DestroyRef, OnInit, inject, signal } from '@angular/core';

import { Router } from '@angular/router';
import { ProductService } from '../../../services/product.service';
import { Product } from '../../../models/product.model';
import { CategoryService } from '../../../services/category.service';
import { Category } from '../../../models/category.model';
import { AdminTableComponent } from '../../../shared/components/admin-table/admin-table.component';
import { CurrencyPipe } from '@angular/common';
import { ToastComponent } from '../../../shared/components/toast/toast.component';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-admin-products',
  imports: [AdminTableComponent, CurrencyPipe, ToastComponent, TranslatePipe],
  templateUrl: './admin-products.component.html',
  styleUrl: './admin-products.component.scss',
})
export class AdminProductsComponent implements OnInit {
  private productService = inject(ProductService);
  private categoryService = inject(CategoryService);
  private destroyRef = inject(DestroyRef);
  private router = inject(Router);

  public products = signal<Product[]>([]);
  public categories = signal<Category[]>([]);

  public ngOnInit(): void {
    this.fetchData();
  }

  private fetchData(): void {
    this.productService
      .getProducts()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((products) => {
        this.products.set(products);
      });

    this.categoryService
      .getCategories()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((categories) => {
        this.categories.set(categories);
      });
  }

  public onSelectProduct(productId: number): void {
    this.router.navigate(['/admin/product', productId]);
  }

  public onCreateProduct(): void {
    this.router.navigate(['admin/product']);
  }

  public getCategoryName(categoryId: number): string {
    const category = this.categories().find((cat) => cat.id === categoryId);
    return category?.name || 'Unknown Category';
  }
}
