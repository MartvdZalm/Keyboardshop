import { Injectable } from '@angular/core';
import { ValidatorFn, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class ValidationService {
  public static emailValidator(): ValidatorFn | null {
    return Validators.compose([
      Validators.required,
      Validators.email,
      Validators.maxLength(254),
    ]);
  }

  public static passwordValidator(): ValidatorFn | null {
    return Validators.compose([
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(30),
      Validators.pattern(
        /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_])(?!.*\s).*$/
      ),
    ]);
  }

  public static firstNameValidator(): ValidatorFn | null {
    return Validators.compose([
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(50),
    ]);
  }

  public static lastNameValidator(): ValidatorFn | null {
    return Validators.compose([
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(50),
    ]);
  }

  public static streetNameValidator(): ValidatorFn | null {
    return Validators.compose([]);
  }

  public static houseNumberValidator(): ValidatorFn | null {
    return Validators.compose([]);
  }

  public static postalCodeValidator(): ValidatorFn | null {
    return Validators.compose([]);
  }

  public static cityValidator(): ValidatorFn | null {
    return Validators.compose([]);
  }

  public static phoneNumberValidator(): ValidatorFn | null {
    return Validators.compose([Validators.pattern(/^(\+|0)[0-9\s-]{9,15}$/)]);
  }
}
