import { Component, input, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-admin-table',
  imports: [],
  templateUrl: './admin-table.component.html',
  styleUrl: './admin-table.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class AdminTableComponent {
  public title = input.required<string>();
}
