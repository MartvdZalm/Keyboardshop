import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBaseComponent } from '../form-base.component';

@Component({
  selector: 'app-form-textarea',
  imports: [ReactiveFormsModule],
  templateUrl: './form-textarea.component.html',
  styleUrl: './form-textarea.component.scss',
})
export class FormTextareaComponent extends FormBaseComponent {
  public get errorMessage(): string {
    return this.getErrorMessage();
  }
}
