import { Component, inject, input } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Category } from '../../../models/category.model';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-category-item',
  imports: [RouterLink],
  templateUrl: './category-item.component.html',
  styleUrl: './category-item.component.scss',
})
export class CategoryItemComponent {
  private router = inject(Router);
  public category = input.required<Category>();
  public imageUrl = environment.imageUrl + '/';

  public navigateToCategory(): void {
    this.router.navigate(['/category', this.category().slug]);
  }
}
