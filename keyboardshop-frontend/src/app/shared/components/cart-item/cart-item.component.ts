import {
  Component,
  input,
  signal,
  inject,
  computed,
  effect,
} from '@angular/core';
import { Product } from '../../../models/product.model';
import { FormsModule } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { CartService } from '../../../services/cart.service';
import { Router, RouterLink } from '@angular/router';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-cart-item',
  imports: [FormsModule, CurrencyPipe, RouterLink],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent {
  private router = inject(Router);
  private cartService = inject(CartService);

  public product = input.required<Product>();
  public quantity = signal<number>(0);
  public imageUrl = environment.imageUrl + '/';
  public price = computed(() => this.product().price * this.quantity());

  public constructor() {
    effect(() => {
      this.quantity.set(
        this.cartService.getProductFromCart(this.product().id)?.quantity ?? 0
      );
    });
  }

  public navigateToProduct(): void {
    this.router.navigate(['/', 'product', this.product().slug]);
  }

  public onDelete(): void {
    this.cartService.removeProduct(this.product().id);
  }

  public increaseQuantity(): void {
    const current = this.quantity();
    if (current <= 10) {
      this.updateQuantity(current + 1);
    }
  }

  public decreaseQuantity(): void {
    const newQuantity = Math.max(1, this.quantity() - 1);
    this.updateQuantity(newQuantity);
  }

  public onQuantityChange(newValue: string | number): void {
    let quantity = +newValue;
    if (isNaN(quantity)) {
      return;
    }

    quantity = Math.min(10, Math.max(1, quantity));
    this.updateQuantity(quantity);
  }

  private updateQuantity(newQuantity: number): void {
    this.quantity.set(newQuantity);
    this.cartService.updateQuantity(this.product().id, newQuantity);
  }
}
