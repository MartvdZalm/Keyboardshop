import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { FormInputComponent } from '../../../shared/components/form/form-input/form-input.component';
import { AuthService } from '../../../services/auth.service';
import { TranslatePipe } from '@ngx-translate/core';
import { ValidationService } from '../../../services/validation.service';

@Component({
  selector: 'app-register',
  imports: [
    FormsModule,
    RouterLink,
    ReactiveFormsModule,
    FormInputComponent,
    TranslatePipe,
  ],
  templateUrl: './register.component.html',
  styleUrl: '../auth.component.scss',
})
export class RegisterComponent {
  private authService = inject(AuthService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);
  public registerForm: FormGroup;
  public errorMessage = '';

  public constructor() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', ValidationService.firstNameValidator()],
      lastName: ['', ValidationService.lastNameValidator()],
      email: ['', ValidationService.emailValidator()],
      password: ['', ValidationService.passwordValidator()],
    });
  }

  public onSubmit(): void {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    this.authService.register(this.registerForm.value).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: (response) => {
        console.log(response);
        this.errorMessage = 'validation.failed.register';
      },
    });
  }
}
