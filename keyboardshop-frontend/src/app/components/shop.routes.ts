import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CategoryComponent } from './category/category.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { SearchComponent } from './search/search.component';
import { OrderComponent } from './order/order.component';
import { orderGuard } from '../shared/guards/order.guard';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { UserComponent } from './user/user.component';
import { userGuard } from '../shared/guards/user.guard';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserOrdersComponent } from './user/user-orders/user-orders.component';
import { UserSettingsComponent } from './user/user-settings/user-settings.component';
import { AssortmentComponent } from './assortment/assortment.component';
import { AboutComponent } from './about/about.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'assortment',
    component: AssortmentComponent,
  },
  {
    path: 'category/:categorySlug',
    component: CategoryComponent,
  },
  {
    path: 'product/:productSlug',
    component: ProductComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
  },
  {
    path: 'search',
    component: SearchComponent,
  },
  {
    path: 'order',
    component: OrderComponent,
    canMatch: [orderGuard],
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'user',
    component: UserComponent,
    canMatch: [userGuard],
    children: [
      {
        path: '',
        redirectTo: 'profile',
        pathMatch: 'full',
      },
      {
        path: 'profile',
        component: UserProfileComponent,
      },
      {
        path: 'orders',
        component: UserOrdersComponent,
      },
      {
        path: 'settings',
        component: UserSettingsComponent,
      },
    ],
  },
  {
    path: 'about',
    component: AboutComponent,
  },
];
