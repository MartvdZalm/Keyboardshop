import { Component, input, inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

type StringDictionary = Record<string, string>;

@Component({
  template: '',
})
export class FormBaseComponent {
  protected translateService = inject(TranslateService);
  public form = input.required<FormGroup>();
  public controlName = input.required<string>();
  public label = input.required<string>();
  public validationMessages = input<StringDictionary>({});

  public getErrorMessage(): string {
    const control = this.form().get(this.controlName());
    if (control && control.errors) {
      for (const key in control.errors) {
        if (this.validationMessages()[key]) {
          return this.translateService.instant(this.validationMessages()[key]);
        }
      }
    }
    return '';
  }
}

