import { Component, DestroyRef, inject, OnInit, signal } from '@angular/core';
import { SearchService } from '../../services/search.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Product } from '../../models/product.model';
import { ProductItemComponent } from '../../shared/components/product-item/product-item.component';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { TranslatePipe } from '@ngx-translate/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-search',
  imports: [ProductItemComponent, ToastComponent, RouterLink, TranslatePipe],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
})
export class SearchComponent implements OnInit {
  private searchService = inject(SearchService);
  private route = inject(ActivatedRoute);
  private destroyRef = inject(DestroyRef);
  private toastService = inject(ToastService);
  public products = signal<Product[]>([]);
  public searchQuery = '';

  public ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      const searchQuery = params['query'];
      if (searchQuery) {
        this.searchQuery = searchQuery;
        this.onSearch(searchQuery);
      }
    });
  }

  private onSearch(searchQuery: string): void {
    this.searchService
      .getSearch(searchQuery)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((products) => {
        this.products.set(products);
      });
  }

  public onAddProductToCart(): void {
    this.toastService.showTranslatedToast('toast.product.cartAdded');
  }
}
