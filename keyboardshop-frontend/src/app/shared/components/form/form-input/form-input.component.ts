import { Component, input } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBaseComponent } from '../form-base.component';

@Component({
  selector: 'app-form-input',
  imports: [ReactiveFormsModule],
  templateUrl: './form-input.component.html',
  styleUrl: './form-input.component.scss'
})
export class FormInputComponent extends FormBaseComponent {
  public type = input<string>('text');

  public get errorMessage(): string {
    return this.getErrorMessage();
  }
}
