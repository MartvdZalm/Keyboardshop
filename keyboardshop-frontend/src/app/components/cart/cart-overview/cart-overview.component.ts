import { Component, inject } from '@angular/core';
import { CartService } from '../../../services/cart.service';
import { TranslatePipe } from '@ngx-translate/core';
import { RouterLink } from '@angular/router';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-cart-overview',
  imports: [TranslatePipe, RouterLink, CurrencyPipe],
  templateUrl: './cart-overview.component.html',
  styleUrl: './cart-overview.component.scss',
})
export class CartOverviewComponent {
  private cartService = inject(CartService);
  public totalPrice = this.cartService.getTotalPrice();
  public totalProducts = this.cartService.getTotalProducts();

  public onEmptyCart(): void {
    this.cartService.clearCart();
  }
}
