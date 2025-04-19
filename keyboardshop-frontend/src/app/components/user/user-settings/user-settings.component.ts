import { Component, inject, DestroyRef, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { FormInputComponent } from '../../../shared/components/form/form-input/form-input.component';
import { TranslatePipe } from '@ngx-translate/core';
import { ValidationService } from '../../../services/validation.service';
import { ToastComponent } from '../../../shared/components/toast/toast.component';
import { ToastService } from '../../../services/toast.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-user-settings',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    FormInputComponent,
    TranslatePipe,
    ToastComponent,
  ],
  templateUrl: './user-settings.component.html',
  styleUrl: './user-settings.component.scss',
})
export class UserSettingsComponent implements OnInit {
  private userService = inject(UserService);
  private destroyRef = inject(DestroyRef);
  private formBuilder = inject(FormBuilder);
  private toastService = inject(ToastService);
  public userForm: FormGroup;

  public constructor() {
    this.userForm = this.formBuilder.group({
      firstName: [
        '',
        [ValidationService.firstNameValidator(), Validators.required],
      ],
      lastName: [
        '',
        [ValidationService.lastNameValidator(), Validators.required],
      ],
      email: ['', [ValidationService.emailValidator(), Validators.required]],
      streetName: ['', ValidationService.streetNameValidator()],
      postalCode: ['', ValidationService.postalCodeValidator()],
      houseNumber: ['', ValidationService.houseNumberValidator()],
      city: ['', ValidationService.cityValidator()],
      phoneNumber: ['', ValidationService.phoneNumberValidator()],
      password: [
        '',
        [ValidationService.passwordValidator(), Validators.required],
      ],
    });
  }

  public ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: (response) => {
        if (response) {
          this.userForm.patchValue({
            firstName: response.firstName,
            lastName: response.lastName,
            email: response.email,
            streetName: response.streetName,
            postalCode: response.postalCode,
            houseNumber: response.houseNumber,
            city: response.city,
            phoneNumber: response.phoneNumber,
          });
        }
      },
    });
  }

  public onSubmit(): void {
    if (this.userForm.invalid) {
      this.userForm.markAllAsTouched();
      return;
    }

    this.userService
      .updateUser(this.userForm.value)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: () => {
          this.toastService.showTranslatedToast('toast.user.update');
        },
        error: () => {
          this.toastService.showTranslatedToast('toast.user.credentials', 'error');
        },
      });
  }
}
