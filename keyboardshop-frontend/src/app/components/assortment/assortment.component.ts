import { Component, signal, inject, DestroyRef, effect } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { ToastService } from '../../services/toast.service';
import { PaginationComponent } from '../../shared/components/pagination/pagination.component';
import { ProductItemComponent } from '../../shared/components/product-item/product-item.component';
import { BreadCrumbsComponent } from '../../shared/components/bread-crumbs/bread-crumbs.component';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { SidebarFilterComponent } from './sidebar-filter/sidebar-filter.component';
import { BreadCrumb } from '../../models/breadcrumb.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { TranslateService } from '@ngx-translate/core';
import { LanguageService } from '../../services/language.service';

@Component({
  selector: 'app-assortment',
  imports: [
    ToastComponent,
    PaginationComponent,
    ProductItemComponent,
    BreadCrumbsComponent,
    SidebarFilterComponent,
    BreadCrumbsComponent,
  ],
  templateUrl: './assortment.component.html',
  styleUrl: './assortment.component.scss',
})
export class AssortmentComponent {
  private productService = inject(ProductService);
  private toastService = inject(ToastService);
  private destroyRef = inject(DestroyRef);
  private languageService = inject(LanguageService);
  private translateService = inject(TranslateService);

  public currentPage = signal<number>(0);
  public pageSize = 15;
  public totalProducts = signal<number>(0);
  public totalPages = signal<number>(0);
  public products = signal<Product[]>([]);
  public breadcrumbs = signal<BreadCrumb[]>([]);

  public constructor() {
    effect(() => {
      this.languageService.currentLang();
      this.breadcrumbs.set([
        {
          name: this.translateService.instant('assortment.assortment'),
          url: '',
        },
      ]);
      this.fetchProducts(this.currentPage());
    });
  }

  private fetchProducts(page: number): void {
    this.productService
      .getPaginatedProducts(page, this.pageSize)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.products.set(response.content);
        this.totalProducts.set(response.totalElements);
        this.totalPages.set(response.totalPages);
      });
  }

  public onPageChange(newPage: number): void {
    this.currentPage.set(newPage);
    window.scrollTo(0, 0);
  }

  public onAddProductToCart(): void {
    this.toastService.showTranslatedToast('toast.product.cartAdded');
  }
}
