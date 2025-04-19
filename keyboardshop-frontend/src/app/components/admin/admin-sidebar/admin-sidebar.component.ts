import { Component, input, output } from '@angular/core';
import { AdminSidebarItemComponent } from './admin-sidebar-item/admin-sidebar-item.component';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrl: './admin-sidebar.component.scss',
  imports: [AdminSidebarItemComponent, TranslatePipe],
})
export class AdminSidebarComponent {
  public sidebarItems = [
    { icon: 'dashboard', label: 'admin.sidebar.dashboard', link: 'dashboard' },
    { icon: 'person', label: 'admin.sidebar.users', link: 'users' },
    { icon: 'inventory_2', label: 'admin.sidebar.products', link: 'products' },
    { icon: 'shopping_cart', label: 'admin.sidebar.orders', link: 'orders' },
    { icon: 'exit_to_app', label: 'Webshop', link: '../' },
    { icon: 'settings', label: 'admin.sidebar.settings', link: 'settings' },
  ];

  public isOpen = input<boolean>(false);
  public closeSidebar = output();

  public onCloseSidebar(): void {
    this.closeSidebar.emit();
  }
}
