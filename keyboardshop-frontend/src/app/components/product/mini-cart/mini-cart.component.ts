import { Component, inject, output } from '@angular/core';
import { CartService } from '../../../services/cart.service';
import { Router, RouterLink } from '@angular/router';
import { CartItemComponent } from '../../../shared/components/cart-item/cart-item.component';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-mini-cart',
  imports: [CartItemComponent, RouterLink, TranslatePipe],
  templateUrl: './mini-cart.component.html',
  styleUrl: './mini-cart.component.scss',
})
export class MiniCartComponent {
  private cartService = inject(CartService);
  public router = inject(Router);
  public products = this.cartService.getProducts();
  public closeCart = output();

  public onDelete(id: number): void {
    this.cartService.removeProduct(id);
  }

  public onCloseCart(): void {
    this.closeCart.emit();
  }
}
