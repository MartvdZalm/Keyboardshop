import {
  Component,
  inject,
  OnInit,
  signal,
  DestroyRef,
  effect,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';
import { ProductImageSliderComponent } from './product-image-slider/product-image-slider.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { BreadCrumbsComponent } from '../../shared/components/bread-crumbs/bread-crumbs.component';
import { CategoryService } from '../../services/category.service';
import { MiniCartComponent } from './mini-cart/mini-cart.component';
import { ProductDescriptionComponent } from './product-description/product-description.component';
import { ProductSpecificationsComponent } from './product-specifications/product-specifications.component';
import { LanguageService } from '../../services/language.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Category } from '../../models/category.model';
import { BreadCrumb } from '../../models/breadcrumb.model';

@Component({
  selector: 'app-product',
  imports: [
    ProductImageSliderComponent,
    ProductCardComponent,
    BreadCrumbsComponent,
    MiniCartComponent,
    ProductDescriptionComponent,
    ProductSpecificationsComponent,
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss',
})
export class ProductComponent implements OnInit {
  private productService = inject(ProductService);
  private categoryService = inject(CategoryService);
  private languageService = inject(LanguageService);
  private route = inject(ActivatedRoute);
  private destroyRef = inject(DestroyRef);

  public product = signal<Product | undefined>(undefined);
  public category = signal<Category | undefined>(undefined);
  public showMiniCart = signal(false);
  public breadCrumbs = signal<BreadCrumb[]>([]);
  public productSlug = signal<string | null>(null);

  public constructor() {
    effect(() => {
      const slug = this.productSlug();
      const lang = this.languageService.currentLang();

      if (slug) {
        this.fetchProduct(slug, lang);
      }
    });
  }

  public ngOnInit(): void {
    this.route.paramMap
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((params) => {
        const slug = params.get('productSlug');
        this.productSlug.set(slug);
      });
  }

  private fetchProduct(slug: string, lang: string): void {
    this.productService
      .getProductBySlug(slug, lang)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((product) => {
        this.product.set(product);
        this.fetchCategory(product, lang);
      });
  }

  private fetchCategory(product: Product, lang: string): void {
    this.categoryService
      .getCategoryById(product.categoryId, lang)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((category) => {
        this.category.set(category);
        this.updateBreadCrumbs(product, category);
      });
  }

  public updateBreadCrumbs(product: Product, category: Category): void {
    this.breadCrumbs.set([
      { name: category.name, url: `/category/${category.slug}` },
      { name: product.name, url: `/product/${product.slug}` },
    ]);
  }

  public onShowMiniCart(): void {
    this.showMiniCart.set(true);
  }

  public toggleShowMiniCart(): void {
    this.showMiniCart.update((show) => !show);
  }
}
