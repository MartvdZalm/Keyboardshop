import { Component, inject, DestroyRef, signal, effect } from '@angular/core';

import { CategoryItemComponent } from './category-item/category-item.component';
import { ProductCarouselComponent } from './product-carousel/product-carousel.component';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category.model';
import { SpacerComponent } from '../../shared/components/spacer/spacer.component';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { LanguageService } from '../../services/language.service';

@Component({
  selector: 'app-home',
  imports: [CategoryItemComponent, ProductCarouselComponent, SpacerComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  private categoryService = inject(CategoryService);
  private languageService = inject(LanguageService);
  private destroyRef = inject(DestroyRef);

  public categories = signal<Category[]>([]);

  public constructor() {
    this.fetchCategories();

    effect(() => {
      this.languageService.currentLang();
      this.fetchCategories();
    });
  }

  private fetchCategories(): void {
    this.categoryService
      .getCategories(this.languageService.currentLang())
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((categories) => {
        this.categories.set(categories);
      });
  }
}
