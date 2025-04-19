import { Component, inject, signal, OnInit } from '@angular/core';
import { LanguageService } from '../../../services/language.service';

@Component({
  selector: 'app-switch-language',
  imports: [],
  templateUrl: './switch-language.component.html',
  styleUrl: './switch-language.component.scss',
})
export class SwitchLanguageComponent implements OnInit {
  private languageService = inject(LanguageService);
  public currentLang = signal('nl');

  public ngOnInit(): void {
    this.currentLang = this.languageService.currentLang;
  }

  public switchLanguage(language: string): void {
    this.languageService.setLanguage(language);
  }
}
