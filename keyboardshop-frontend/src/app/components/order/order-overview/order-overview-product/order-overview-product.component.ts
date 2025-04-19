import {
  Component,
  input,
  inject,
  signal,
  computed,
  OnInit,
  effect,
  DestroyRef,
} from '@angular/core';
import { Product } from '../../../../models/product.model';
import { CartService } from '../../../../services/cart.service';
import { CurrencyPipe } from '@angular/common';
import { environment } from '../../../../../environments/environment';
import { TranslatePipe } from '@ngx-translate/core';
import { LanguageService } from '../../../../services/language.service';
import { ProductService } from '../../../../services/product.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-order-overview-product',
  imports: [CurrencyPipe, TranslatePipe],
  templateUrl: './order-overview-product.component.html',
  styleUrl: './order-overview-product.component.scss',
})
export class OrderOverviewProductComponent implements OnInit {
  private cartService = inject(CartService);
  private productService = inject(ProductService);
  private languageService = inject(LanguageService);
  private destroyRef = inject(DestroyRef);

  public product = signal<Product | undefined>(undefined);
  public productId = input.required<number>();
  public price = input.required<number>();
  public quantity = signal<number>(0);
  public totalPrice = computed<number>(() => this.price() * this.quantity());
  public imageUrl = environment.imageUrl + '/';

  public constructor() {
    effect(() => {
      const lang = this.languageService.currentLang();
      this.productService
        .getProductById(this.productId(), lang)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe((product) => {
          this.product.set(product);
        });
    });
  }

  public ngOnInit(): void {
    this.quantity.set(
      this.cartService.getProductFromCart(this.productId())?.quantity ?? 0
    );
  }
}
