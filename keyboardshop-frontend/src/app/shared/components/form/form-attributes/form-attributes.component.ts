import { Component, inject } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { FormBaseComponent } from '../form-base.component';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-form-attributes',
  imports: [ReactiveFormsModule, FormsModule, TranslatePipe],
  templateUrl: './form-attributes.component.html',
  styleUrl: './form-attributes.component.scss',
})
export class FormAttributesComponent extends FormBaseComponent {
  private formBuilder = inject(FormBuilder);

  public errorMessage(index: number, controlName: string): string {
    const control = this.attributes.at(index).get(controlName);

    if (control && control.errors) {
      for (const key in control.errors) {
        if (this.validationMessages()[key]) {
          return this.translateService.instant(this.validationMessages()[key]);
        }
      }
    }

    return '';
  }

  public get attributes(): FormArray {
    return this.form().get(this.controlName()) as FormArray;
  }

  public addAttribute(): void {
    const attributeGroup = this.formBuilder.group({
      name: ['', Validators.required],
      value: ['', Validators.required],
    });
    this.attributes.push(attributeGroup);
  }

  public removeAttribute(index: number): void {
    this.attributes.removeAt(index);
  }
}
