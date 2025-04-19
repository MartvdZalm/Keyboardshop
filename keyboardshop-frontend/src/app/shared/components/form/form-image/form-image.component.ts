import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { environment } from '../../../../../environments/environment';
import { FormBaseComponent } from '../form-base.component';

@Component({
  selector: 'app-form-image',
  imports: [ReactiveFormsModule],
  templateUrl: './form-image.component.html',
  styleUrl: './form-image.component.scss',
})
export class FormImageComponent extends FormBaseComponent {
  public imageUrl = environment.imageUrl + '/';

  public get errorMessage(): string {
    return this.getErrorMessage();
  }

  public onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files) {
      Array.from(target.files).map((file) => {
        const reader = new FileReader();
        reader.onloadend = (): void => {
          this.form().value.images.push({
            previewUrl: reader.result as string,
            file,
            url: null,
          });
        };
        reader.readAsDataURL(file);
      });
    }
  }

  public removeImage(index: number): void {
    this.form().value.images.splice(index, 1);
  }
}
