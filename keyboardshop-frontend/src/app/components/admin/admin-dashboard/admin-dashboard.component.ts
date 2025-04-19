import { Component, DestroyRef, inject, OnInit, signal } from '@angular/core';
import { AdminDashboardCountComponent } from './admin-dashboard-count/admin-dashboard-count.component';
import { ProductService } from '../../../services/product.service';
import { OrderService } from '../../../services/order.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-admin-dashboard',
  imports: [AdminDashboardCountComponent, TranslatePipe],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss', '../shared.component.scss'],
})
export class AdminDashboardComponent implements OnInit {
  private productService = inject(ProductService);
  private orderService = inject(OrderService);
  private destroyRef = inject(DestroyRef);
  public productCount = signal<number>(0);
  public orderCount = signal<number>(0);

  public ngOnInit(): void {
    this.fetchProducts();
    this.fetchOrders();
  }

  private fetchProducts(): void {
    this.productService
      .getProducts()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((products) => {
        this.productCount.set(products.length);
      });
  }

  private fetchOrders(): void {
    this.orderService
      .getOrders()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((orders) => {
        this.orderCount.set(orders.length);
      });
  }
}
