import { Component, inject } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CartItemComponent } from '../../shared/components/cart-item/cart-item.component';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { TranslatePipe } from '@ngx-translate/core';
import { CartOverviewComponent } from './cart-overview/cart-overview.component';
import { SpacerComponent } from '../../shared/components/spacer/spacer.component';

@Component({
  selector: 'app-cart',
  imports: [
    CartItemComponent,
    ToastComponent,
    TranslatePipe,
    CartOverviewComponent,
    SpacerComponent
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {
  private cartService = inject(CartService);
  public products = this.cartService.getProducts();
}
