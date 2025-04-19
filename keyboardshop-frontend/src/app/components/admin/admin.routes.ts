import { Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminProductsComponent } from './admin-products/admin-products.component';
import { AdminOrdersComponent } from './admin-orders/admin-orders.component';
import { AdminProductComponent } from './admin-product/admin-product.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    component: AdminDashboardComponent,
  },
  {
    path: 'users',
    component: AdminUsersComponent,
  },
  {
    path: 'product',
    component: AdminProductComponent,
  },
  {
    path: 'product/:productId',
    component: AdminProductComponent,
  },
  {
    path: 'products',
    component: AdminProductsComponent,
  },
  {
    path: 'orders',
    component: AdminOrdersComponent,
  },
];
