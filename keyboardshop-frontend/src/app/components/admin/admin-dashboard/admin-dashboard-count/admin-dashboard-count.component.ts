import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard-count',
  imports: [RouterLink],
  templateUrl: './admin-dashboard-count.component.html',
  styleUrl: './admin-dashboard-count.component.scss',
})
export class AdminDashboardCountComponent {
  public title = input.required<string>();
  public count = input.required<number>();
  public slug = input.required<string>();
}
