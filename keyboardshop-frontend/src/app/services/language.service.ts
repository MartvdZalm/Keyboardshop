import { DestroyRef, inject, Injectable, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root',
})
export class LanguageService {
  private translateService = inject(TranslateService);
  private destroyRef = inject(DestroyRef);
  public currentLang = signal<string>('nl');

  public constructor() {
    this.translateService.onLangChange
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((event) => {
        this.currentLang.set(event.lang);
      });
  }

  public setLanguage(lang: string): void {
    this.currentLang.set(lang);
    this.translateService.use(lang);
  }
}
