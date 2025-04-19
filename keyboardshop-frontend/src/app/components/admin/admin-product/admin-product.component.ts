import {
  Component,
  input,
  inject,
  DestroyRef,
  OnInit,
  signal,
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ProductService } from '../../../services/product.service';
import { CategoryService } from '../../../services/category.service';
import { provideMarkdown } from 'ngx-markdown';
import { environment } from '../../../../environments/environment';
import { ImageService } from '../../../services/image.service';
import { FormInputComponent } from '../../../shared/components/form/form-input/form-input.component';
import { ToastComponent } from '../../../shared/components/toast/toast.component';
import { TranslatePipe } from '@ngx-translate/core';
import { FormTextareaComponent } from '../../../shared/components/form/form-textarea/form-textarea.component';
import { FormImageComponent } from '../../../shared/components/form/form-image/form-image.component';
import { FormAttributesComponent } from '../../../shared/components/form/form-attributes/form-attributes.component';
import { FormSelectComponent } from '../../../shared/components/form/form-select/form-select.component';
import { SelectOption } from '../../../models/select-option.model';
import { ToastService } from '../../../services/toast.service';
import { forkJoin } from 'rxjs';
import { Product } from '../../../models/product.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ConfirmationComponent } from '../../../shared/components/confirmation/confirmation.component';
import { ConfirmationService } from '../../../services/confirmation.service';

@Component({
  selector: 'app-product',
  imports: [
    FormsModule,
    FormInputComponent,
    FormTextareaComponent,
    FormImageComponent,
    FormAttributesComponent,
    FormSelectComponent,
    ToastComponent,
    TranslatePipe,
    ReactiveFormsModule,
    RouterLink,
    ConfirmationComponent,
  ],
  providers: [provideMarkdown()],
  templateUrl: './admin-product.component.html',
  styleUrl: './admin-product.component.scss',
})
export class AdminProductComponent implements OnInit {
  private productService = inject(ProductService);
  private categoryService = inject(CategoryService);
  private imageService = inject(ImageService);
  private toastService = inject(ToastService);
  private confirmationService = inject(ConfirmationService);
  private destroyRef = inject(DestroyRef);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  private productDataEN: Product | undefined = undefined;
  private productDataNL: Product | undefined = undefined;

  public productForm: FormGroup;
  public categories: SelectOption[] = [];
  public productName = '';
  public productActive = false;
  public productId = input.required<number>();
  public imageUrl = environment.imageUrl + '/';
  public currentLang = signal<'en' | 'nl'>('nl');

  public constructor() {
    this.productForm = this.formBuilder.group({
      nameEN: ['', [Validators.required]],
      descriptionEN: ['', [Validators.required]],
      nameNL: ['', [Validators.required]],
      descriptionNL: ['', [Validators.required]],
      price: ['', [Validators.required]],
      quantity: ['', [Validators.required]],
      images: [[]],
      attributes: this.formBuilder.array([]),
      category: ['', [Validators.required]],
    });
  }

  public ngOnInit(): void {
    if (this.productId()) {
      this.fetchProduct();
    }

    this.fetchCategories();
  }

  public setActiveLanguage(lang: 'en' | 'nl'): void {
    this.currentLang.set(lang);
  }

  private fetchProduct(): void {
    forkJoin({
      en: this.productService.getProductById(this.productId(), 'en'),
      nl: this.productService.getProductById(this.productId(), 'nl'),
    }).subscribe({
      next: (response: { en: Product; nl: Product }) => {
        this.productDataEN = response.en;
        this.productDataNL = response.nl;
        this.updateFormWithCurrentLanguage();

        this.productName =
          this.currentLang() === 'en' ? response.en.name : response.nl.name;
        this.productActive = response.en.active;

        this.productForm.patchValue({
          price: response.en.price,
          quantity: response.en.quantity,
          images: response.en.images,
          attributes: response.en.attributes,
          category: response.en.categoryId,
        });

        const attributesArray = this.productForm.get('attributes') as FormArray;
        attributesArray.clear();
        this.productDataEN.attributes.forEach((attribute) => {
          const attributeGroup = this.formBuilder.group({
            name: [attribute.name, Validators.required],
            value: [attribute.value, Validators.required],
          });
          attributesArray.push(attributeGroup);
        });
      },
      error: () => {
        this.router.navigate(['/products']);
      },
    });
  }

  private updateFormWithCurrentLanguage(): void {
    this.productForm.patchValue(
      {
        nameEN: this.productDataEN!.name,
        descriptionEN: this.productDataEN!.description,
        nameNL: this.productDataNL!.name,
        descriptionNL: this.productDataNL!.description,
      },
      { emitEvent: false }
    );
  }

  private fetchCategories(): void {
    this.categoryService
      .getCategories()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (categories) => {
          this.categories = categories.map((category) => ({
            id: category.id,
            name: category.name,
          }));
        },
      });
  }

  public onSubmit(): void {
    if (this.productForm.invalid) {
      this.productForm.markAllAsTouched();
      const invalidFields = Object.keys(this.productForm.controls).filter(
        (key) => this.productForm.get(key)?.invalid
      );

      this.toastService.showTranslatedToast('toast.product.invalidFields', 'warning', {
        fields: invalidFields.join(', ')
      });
      return;
    }

    const translations = [
      {
        language: 'en',
        name: this.productForm.value.nameEN,
        description: this.productForm.value.descriptionEN,
      },
      {
        language: 'nl',
        name: this.productForm.value.nameNL,
        description: this.productForm.value.descriptionNL,
      },
    ];

    const images = this.productForm.value.images.map((image: { url: string; file: File; previewUrl: string }) => {
        if (image.previewUrl) {
          return image.file.name;
        }
        return image.url;
      }
    );

    const productData = {
      price: this.productForm.value.price,
      quantity: this.productForm.value.quantity,
      images: images,
      attributes: this.productForm.value.attributes,
      categoryId: this.productForm.value.category,
      translations: translations,
    };

    if (this.productId()) {
      this.productService
        .updateProduct(this.productId(), productData)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe({
          next: () => {
            this.toastService.showTranslatedToast('toast.product.updated');
          },
          error: () => {
            this.toastService.showTranslatedToast('toast.product.updateError', 'error');
          },
        });
    } else {
      this.productService
        .createProduct(productData)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe({
          next: () => {
            this.toastService.showTranslatedToast('toast.product.created');
          },
          error: () => {
            this.toastService.showTranslatedToast('toast.product.createError', 'error');
          },
        });
    }

    this.productForm.value.images.forEach(
      (image: { url: string; file: File; previewUrl: string }) => {
        if (!image.url) {
          const formData = new FormData();
          formData.append('image', image.file);
          this.imageService
            .uploadImage(formData)
            .pipe(takeUntilDestroyed(this.destroyRef))
            .subscribe();
        }
      }
    );
  }

  public onActive(): void {
    this.productService
      .softDeleteProduct(this.productId())
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: () => {
          if (this.productActive) {
            this.toastService.showTranslatedToast('toast.product.deactivate');
          } else {
            this.toastService.showTranslatedToast('toast.product.activate');
          }
          this.router.navigate(['/admin/products']);
        },
        error: () => {
          this.toastService.showTranslatedToast('toast.product.deleteError', 'error');
        },
      });
  }

  public onDelete(): void {
    this.confirmationService
      .confirm({
        title: 'Delete Product',
        message:
          'Are you sure you want to delete this product? This action cannot be undone.',
        confirmText: 'Delete',
        cancelText: 'Keep it',
      })
      .then((confirmed) => {
        if (confirmed) {
          this.productService
            .deleteProduct(this.productId())
            .pipe(takeUntilDestroyed(this.destroyRef))
            .subscribe({
              next: () => {
                this.toastService.showTranslatedToast('toast.product.delete');
                this.router.navigate(['/admin/products']);
              },
              error: () => {
                this.toastService.showTranslatedToast('toast.product.deleteError', 'error');
              },
            });
        }
      });
  }
}
