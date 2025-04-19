import {
  Component,
  DestroyRef,
  effect,
  inject,
  input,
  OnInit,
  signal,
} from '@angular/core';
import { OrderProduct } from '../../../../models/order-product.model';
import { environment } from '../../../../../environments/environment';
import { TranslatePipe } from '@ngx-translate/core';
import { CurrencyPipe } from '@angular/common';
import { LanguageService } from '../../../../services/language.service';
import { ProductService } from '../../../../services/product.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-user-orders-product',
  imports: [TranslatePipe, CurrencyPipe],
  templateUrl: './user-orders-product.component.html',
  styleUrl: './user-orders-product.component.scss',
})
export class UserOrdersProductComponent implements OnInit {
  private languageService = inject(LanguageService);
  private productService = inject(ProductService);
  private destroyRef = inject(DestroyRef);
  public product = input.required<OrderProduct>();
  public imageUrl = environment.imageUrl + '/';
  public productName = signal<string>('');

  public constructor() {
    effect(() => {
      if (this.product().product) {
        const lang = this.languageService.currentLang();
        this.productService
          .getProductById(this.product().product.id, lang)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe((response) => {
            this.productName.set(response.name);
          });
      }
    });
  }

  public ngOnInit(): void {
    const product = this.product().product;
    if (this.product().product) {
      this.productName.set(product.name);
    } else {
      this.productName.set(this.product().productName);
    }
  }
}
