import { Component, DestroyRef, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { FormInputComponent } from '../../../shared/components/form/form-input/form-input.component';
import { TranslatePipe } from '@ngx-translate/core';
import { ValidationService } from '../../../services/validation.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    FormInputComponent,
    RouterLink,
    TranslatePipe,
  ],
  templateUrl: './login.component.html',
  styleUrl: '../auth.component.scss',
})
export class LoginComponent {
  private authService = inject(AuthService);
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);
  private formBuilder = inject(FormBuilder);
  public loginForm: FormGroup;
  public errorMessage = '';

  public constructor() {
    this.loginForm = this.formBuilder.group({
      email: ['', ValidationService.emailValidator()],
      password: ['', ValidationService.passwordValidator()],
    });
  }

  public onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.authService
      .login(this.loginForm.value)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: () => {
          if (this.authService.hasRole('ADMIN')) {
            this.router.navigate(['admin']);
          } else {
            this.router.navigate(['/']);
          }
        },
        error: () => {
          this.errorMessage = 'validation.failed.login';
        },
      });
  }
}
