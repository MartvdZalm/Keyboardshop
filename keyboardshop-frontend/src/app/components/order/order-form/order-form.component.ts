import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import {
  FormControl,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { AuthService } from '../../../services/auth.service';
import { FormInputComponent } from '../../../shared/components/form/form-input/form-input.component';
import { OrderService } from '../../../services/order.service';
import { CartService } from '../../../services/cart.service';
import { ValidationService } from '../../../services/validation.service';
import { ToastComponent } from '../../../shared/components/toast/toast.component';
import { Router } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ToastService } from '../../../services/toast.service';

@Component({
  selector: 'app-order-form',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    FormInputComponent,
    ToastComponent,
    TranslatePipe,
  ],
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.scss',
})
export class OrderFormComponent implements OnInit {
  private authService = inject(AuthService);
  private userService = inject(UserService);
  private orderService = inject(OrderService);
  private cartService = inject(CartService);
  private destroyRef = inject(DestroyRef);
  private toastService = inject(ToastService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);
  private userId: number | undefined;

  public isAuthenticated = this.authService.isAuthenticated();
  public orderForm: FormGroup;

  public constructor() {
    this.orderForm = this.formBuilder.group({
      firstName: ['', [ValidationService.firstNameValidator()]],
      lastName: ['', [ValidationService.lastNameValidator()]],
      streetName: ['', Validators.required],
      houseNumber: ['', Validators.required],
      postalCode: ['', Validators.required],
      city: ['', Validators.required],
    });

    if (!this.isAuthenticated) {
      this.orderForm.addControl(
        'email',
        new FormControl('', ValidationService.emailValidator())
      );
    }
  }

  public ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.fetchUserData();
    }
  }

  private fetchUserData(): void {
    this.userService.getUser().subscribe({
      next: (user) => {
        this.userId = user.id;
        this.orderForm.patchValue({
          firstName: user.firstName,
          lastName: user.lastName,
          streetName: user.streetName,
          houseNumber: user.houseNumber,
          postalCode: user.postalCode,
          city: user.city,
        });
      },
    });
  }

  public onSubmit(): void {
    if (this.orderForm.invalid) {
      this.orderForm.markAllAsTouched();
      return;
    }

    const orderData = {
      ...this.orderForm.value,
      ...(this.isAuthenticated && { userId: this.userId }),
      products: this.cartService
        .getProducts()()
        .map((product) => ({
          productId: product.id,
          quantity: product.quantity,
        })),
    };

    if (this.isAuthenticated) {
      this.orderService
        .createUserOrder(orderData)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe(() => {
          this.cartService.clearCart();
          this.toastService.showTranslatedToast('toast.order.placed');
          this.router.navigate(['/', 'cart']);
        });
    } else {
      this.orderService
        .createGuestOrder(orderData)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe({
          next: () => {
            this.cartService.clearCart();
            this.toastService.showTranslatedToast('toast.order.placed');
            this.router.navigate(['/', 'cart']);
          },
        });
    }
  }
}
