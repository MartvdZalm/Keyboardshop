import { Component, DestroyRef, inject, OnInit, signal } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import { Order } from '../../../models/order.model';
import { AdminTableComponent } from '../../../shared/components/admin-table/admin-table.component';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { TranslatePipe } from '@ngx-translate/core';
import { TitleCasePipe } from '@angular/common';

@Component({
  selector: 'app-admin-orders',
  imports: [AdminTableComponent, TranslatePipe, TitleCasePipe],
  templateUrl: './admin-orders.component.html',
  styleUrl: './admin-orders.component.scss',
})
export class AdminOrdersComponent implements OnInit {
  private orderService = inject(OrderService);
  private destroyRef = inject(DestroyRef);
  public orders = signal<Order[]>([]);

  public ngOnInit(): void {
    this.orderService
      .getOrders()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (orders) => {
          this.orders.set(orders);
        },
      });
  }
}
