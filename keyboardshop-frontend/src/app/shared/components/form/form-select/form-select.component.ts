import { Component, input } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { SelectOption } from '../../../../models/select-option.model';
import { FormBaseComponent } from '../form-base.component';

@Component({
  selector: 'app-form-select',
  imports: [ReactiveFormsModule],
  templateUrl: './form-select.component.html',
  styleUrl: './form-select.component.scss',
})
export class FormSelectComponent extends FormBaseComponent {
  public options = input.required<SelectOption[]>();

  public get errorMessage(): string {
    return this.getErrorMessage();
  }
}
