import { Routes } from '@angular/router';

import { AdminComponent } from './components/admin/admin.component';
import { ShopComponent } from './components/shop.component';
import { adminGuard } from './shared/guards/admin.guard';
import { routes as adminRoutes } from './components/admin/admin.routes';
import { routes as shopRoutes } from './components/shop.routes';
import { NotFoundComponent } from './components/not-found/not-found.component';

export const routes: Routes = [
  {
    path: '',
    component: ShopComponent,
    children: shopRoutes,
  },
  {
    path: 'admin',
    canMatch: [adminGuard],
    component: AdminComponent,
    children: adminRoutes,
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];
