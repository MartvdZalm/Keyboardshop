import { CurrencyPipe } from '@angular/common';
import { Component, inject, input, output } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Product } from '../../../models/product.model';
import { Router, RouterLink } from '@angular/router';
import { CartService } from '../../../services/cart.service';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-product-item',
  imports: [CurrencyPipe, RouterLink, TranslatePipe],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.scss',
})
export class ProductItemComponent {
  private router = inject(Router);
  private cartService = inject(CartService);

  public product = input.required<Product>();
  public imageUrl = environment.imageUrl + '/';
  public addProductToCart = output();

  public navigateToProduct(): void {
    this.router.navigate(['/product', this.product().slug]);
  }

  public onAddToCart(): void {
    this.cartService.addProduct({
      ...this.product(),
      quantity: 1,
    });
    this.addProductToCart.emit();
  }
}
