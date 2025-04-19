import {
  Component,
  DestroyRef,
  effect,
  inject,
  OnInit,
  signal,
} from '@angular/core';
import { OrderService } from '../../../services/order.service';
import { Order } from '../../../models/order.model';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.model';
import { TranslatePipe } from '@ngx-translate/core';
import { UserOrdersProductComponent } from './user-orders-product/user-orders-product.component';
import { CurrencyPipe, DatePipe, TitleCasePipe } from '@angular/common';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { LanguageService } from '../../../services/language.service';

@Component({
  selector: 'app-user-orders',
  imports: [TranslatePipe, UserOrdersProductComponent, CurrencyPipe, DatePipe, TitleCasePipe],
  templateUrl: './user-orders.component.html',
  styleUrl: './user-orders.component.scss',
})
export class UserOrdersComponent implements OnInit {
  private orderService = inject(OrderService);
  private userService = inject(UserService);
  private destroyRef = inject(DestroyRef);
  private languageService = inject(LanguageService);
  public orders = signal<Order[]>([]);
  public user = signal<User | undefined>(undefined);

  public constructor() {
    effect(() => {
      const lang = this.languageService.currentLang();
      const user = this.user();
      if (user) {
        this.fetchOrders(user, lang);
      }
    });
  }

  public ngOnInit(): void {
    this.userService
      .getUser()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.user.set(response);
      });
  }

  private fetchOrders(user: User, lang: string): void {
    if (!user.id) {
      return;
    }

    this.orderService
      .getOrdersByUserId(user.id, lang)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.orders.set(response);
      });
  }
}
