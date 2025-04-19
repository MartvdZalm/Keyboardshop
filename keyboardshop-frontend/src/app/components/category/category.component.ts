import {
  Component,
  computed,
  DestroyRef,
  effect,
  inject,
  OnInit,
  signal,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category.model';
import { BreadCrumbsComponent } from '../../shared/components/bread-crumbs/bread-crumbs.component';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';
import { ProductItemComponent } from '../../shared/components/product-item/product-item.component';
import { ToastService } from '../../services/toast.service';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { PaginationComponent } from '../../shared/components/pagination/pagination.component';
import { LanguageService } from '../../services/language.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { BreadCrumb } from '../../models/breadcrumb.model';

@Component({
  selector: 'app-category',
  imports: [
    BreadCrumbsComponent,
    ProductItemComponent,
    ToastComponent,
    PaginationComponent,
  ],
  templateUrl: './category.component.html',
  styleUrl: './category.component.scss',
})
export class CategoryComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private productService = inject(ProductService);
  private categoryService = inject(CategoryService);
  private destroyRef = inject(DestroyRef);
  private languageService = inject(LanguageService);
  private toastService = inject(ToastService);

  public category = signal<Category | undefined>(undefined);
  public categorySlug = signal<string>('');
  public currentPage = signal<number>(0);
  public pageSize = 15;
  public products = signal<Product[]>([]);
  public totalProducts = signal<number>(0);
  public totalPages = signal<number>(0);

  public breadcrumbs = computed<BreadCrumb[]>(() => {
    const category = this.category();
    return category ? [{ name: category.name, url: category.slug }] : [];
  });

  public constructor() {
    effect(() => {
      const slug = this.categorySlug();
      if (slug) {
        this.fetchCategory(slug);
      }
    });

    effect(() => {
      const category = this.category();
      if (category) {
        this.fetchProducts();
      }
    });
  }

  public ngOnInit(): void {
    this.route.paramMap
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((params) => {
        const slug = params.get('categorySlug');
        if (slug) {
          this.categorySlug.set(slug);
          this.fetchCategory(slug);
        }
      });
  }

  private fetchCategory(slug: string): void {
    this.categoryService
      .getCategoryBySlug(slug, this.languageService.currentLang())
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((category) => {
        this.category.set(category);
        this.currentPage.set(0);
        this.fetchProducts();
      });
  }

  private fetchProducts(): void {
    const category = this.category();
    if (!category) {
      return;
    }

    this.productService
      .getPaginatedProductsByCategory(
        this.currentPage(),
        this.pageSize,
        this.category()!.id,
        this.languageService.currentLang()
      )
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
