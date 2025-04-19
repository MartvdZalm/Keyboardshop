import { Component, input, inject, signal, output } from '@angular/core';
import { Product } from '../../../models/product.model';
import { CurrencyPipe } from '@angular/common';
import { CartService } from '../../../services/cart.service';
import { FormsModule } from '@angular/forms';
import { TranslatePipe } from '@ngx-translate/core';
import { ToastService } from '../../../services/toast.service';
import { ToastComponent } from '../../../shared/components/toast/toast.component';

@Component({
  selector: 'app-product-card',
  imports: [CurrencyPipe, FormsModule, TranslatePipe, ToastComponent],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss',
})
export class ProductCardComponent {
  private cartService = inject(CartService);
  private toastService = inject(ToastService);
  public product = input<Product | undefined>(undefined);
  public quantity = signal<number>(1);
  public addProductToCart = output();

  public onAddToCart(): void {
    const product = this.product();
    const desiredQuantity = this.quantity();

    if (!product) {
      return;
    }

    const productInCart = this.cartService.getProductFromCart(product.id);
    const currentCartQuantity = productInCart?.quantity ?? 0;
    const newTotal = currentCartQuantity + desiredQuantity;

    if (newTotal > 10) {
      this.addProductToCart.emit();

      this.toastService.showTranslatedToast('toast.cart.limit', 'warning');
      return;
    }

    this.cartService.addProduct({
      ...product,
      quantity: desiredQuantity,
    });

    this.addProductToCart.emit();
  }

  public getMaxQuantity(): number {
    const product = this.product();
    if (!product) {
      return 10;
    }

    const inCart = this.cartService.getProductFromCart(product.id);
    const alreadyInCart = inCart?.quantity ?? 0;

    return Math.max(1, 10 - alreadyInCart);
  }
}
