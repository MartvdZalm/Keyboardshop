import { Component, input } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-admin-sidebar-item',
  templateUrl: './admin-sidebar-item.component.html',
  styleUrl: './admin-sidebar-item.component.scss',
  imports: [RouterLink, RouterLinkActive, TranslatePipe],
})
export class AdminSidebarItemComponent {
  public icon = input.required<string>();
  public label = input.required<string>();
  public link = input.required<string>();
}
