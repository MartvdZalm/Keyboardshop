import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { BreadCrumb } from '../../../models/breadcrumb.model';

@Component({
  selector: 'app-bread-crumbs',
  imports: [RouterLink],
  templateUrl: './bread-crumbs.component.html',
  styleUrl: './bread-crumbs.component.scss'
})
export class BreadCrumbsComponent {
  public breadcrumbs = input.required<BreadCrumb[]>();
}
