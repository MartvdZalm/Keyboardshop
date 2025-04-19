import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import translationsEN from "../../public/i18n/en.json";
import translationsNL from "../../public/i18n/nl.json";
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  public title = 'KeyHub';

  private translateService = inject(TranslateService);

  public constructor() {
    this.initialiseTranslateService();
  }

  private initialiseTranslateService(): void {
    this.translateService.addLangs(['nl', 'en']);
    this.translateService.setTranslation('en', translationsEN);
    this.translateService.setTranslation('nl', translationsNL);
    this.translateService.setDefaultLang('nl');
  }
}
