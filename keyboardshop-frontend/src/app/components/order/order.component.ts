import { Component } from '@angular/core';
import { OrderFormComponent } from './order-form/order-form.component';
import { OrderOverviewComponent } from './order-overview/order-overview.component';
import { SpacerComponent } from '../../shared/components/spacer/spacer.component';

@Component({
  selector: 'app-order',
  imports: [OrderFormComponent, OrderOverviewComponent, SpacerComponent],
  templateUrl: './order.component.html',
  styleUrl: './order.component.scss',
})
export class OrderComponent {}
