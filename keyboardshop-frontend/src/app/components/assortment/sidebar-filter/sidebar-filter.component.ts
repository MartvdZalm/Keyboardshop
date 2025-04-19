import { Component, DestroyRef, effect, inject, signal } from '@angular/core';
import { CategoryService } from '../../../services/category.service';
import { Category } from '../../../models/category.model';
import { RouterLink } from '@angular/router';
import { LanguageService } from '../../../services/language.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-sidebar-filter',
  imports: [RouterLink],
  templateUrl: './sidebar-filter.component.html',
  styleUrl: './sidebar-filter.component.scss',
})
export class SidebarFilterComponent {
  private categoryService = inject(CategoryService);
  private destroyRef = inject(DestroyRef);
  private languageService = inject(LanguageService);
  public categories = signal<Category[]>([]);

  public constructor() {
    effect(() => {
      const lang = this.languageService.currentLang();
      this.fetchCategories(lang);
    });
  }

  public fetchCategories(lang: string): void {
    this.categoryService
      .getCategories(lang)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (categories) => {
          this.categories.set(categories);
        },
      });
  }
}
