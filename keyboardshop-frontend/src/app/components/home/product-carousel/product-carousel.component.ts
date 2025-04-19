import {
  Component,
  DestroyRef,
  inject,
  OnInit,
  viewChild,
  ElementRef,
  OnDestroy,
  signal,
} from '@angular/core';
import { ProductService } from '../../../services/product.service';
import { Product } from '../../../models/product.model';
import { ProductItemComponent } from '../../../shared/components/product-item/product-item.component';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-product-carousel',
  imports: [ProductItemComponent],
  templateUrl: './product-carousel.component.html',
  styleUrl: './product-carousel.component.scss',
})
export class ProductCarouselComponent implements OnInit, OnDestroy {
  private productService = inject(ProductService);
  private destroyRef = inject(DestroyRef);
  private carouselInterval: ReturnType<typeof setTimeout> | null = null;

  public products = signal<Product[]>([]);
  public currentIndex = 0;
  public carousel = viewChild<ElementRef>('carousel');

  public ngOnInit(): void {
    this.productService.getProducts()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe({
      next: (products) => {
        this.products.set(products);
        this.updateCarousel();
        this.startAutoPlay();
      },
    });
  }

  public next(): void {
    this.currentIndex = (this.currentIndex + 1) % this.products().length;
    this.updateCarousel();
  }

  public prev(): void {
    this.currentIndex = (this.currentIndex - 1 + this.products().length) % this.products().length;
    this.updateCarousel();
  }

  private startAutoPlay(): void {
    this.carouselInterval = setInterval(() => {
      this.next();
    }, 5000);
  }

  public updateCarousel(): void {
    const carouselEl = this.carousel()?.nativeElement as HTMLElement;
    carouselEl.style.transform = `translateX(-${
      this.currentIndex * 300
    }px)`;
  }

  public ngOnDestroy(): void {
    if (this.carouselInterval) {
      clearInterval(this.carouselInterval);
      this.carouselInterval = null;
    }
  }
}
