import { Component, inject } from '@angular/core';
import { CartService } from '../../../services/cart.service';
import { OrderOverviewProductComponent } from './order-overview-product/order-overview-product.component';

@Component({
  selector: 'app-order-overview',
  imports: [OrderOverviewProductComponent],
  templateUrl: './order-overview.component.html',
  styleUrl: './order-overview.component.scss',
})
export class OrderOverviewComponent {
  private cartService = inject(CartService);
  public products = this.cartService.getProducts();
}
