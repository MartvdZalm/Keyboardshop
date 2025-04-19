import {
  Component,
  DestroyRef,
  effect,
  inject,
  OnInit,
  signal,
} from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink } from '@angular/router';

import { Category } from '../../models/category.model';
import { CategoryService } from '../../services/category.service';
import { FormsModule } from '@angular/forms';
import { SwitchLanguageComponent } from './switch-language/switch-language.component';
import { TranslatePipe } from '@ngx-translate/core';
import { LanguageService } from '../../services/language.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-header',
  imports: [RouterLink, FormsModule, SwitchLanguageComponent, TranslatePipe],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  private authService = inject(AuthService);
  private categoryService = inject(CategoryService);
  private destroyRef = inject(DestroyRef);
  private router = inject(Router);
  private languageService = inject(LanguageService);

  public enteredSearch = signal<string>('');
  public isMenuOpen = signal<boolean>(false);
  public isLoggedIn = this.authService.isAuthenticated();
  public categories = signal<Category[]>([]);

  public constructor() {
    effect(() => {
      this.languageService.currentLang();
      this.fetchCategories();
    });
  }

  public ngOnInit(): void {
    this.fetchCategories();
  }

  public fetchCategories(): void {
    this.categoryService
      .getCategories(this.languageService.currentLang())
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (categories) => {
          this.categories.set(categories);
        },
      });
  }

  public onLogout(): void {
    this.authService.logout();
  }

  public toggleMenu(): void {
    this.isMenuOpen.update((value) => !value);
  }

  public closeMenu(): void {
    this.isMenuOpen.set(false);
  }

  public onSearch(): void {
    const query = this.enteredSearch().trim();
    if (query) {
      this.router.navigate(['/search'], { queryParams: { query } });
      this.enteredSearch.set('');
      this.closeMenu();
    }
  }
}
