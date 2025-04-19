import { Component, effect, input, signal } from '@angular/core';
import { ProductImage } from '../../../models/product-image.model';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-product-image-slider',
  imports: [],
  templateUrl: './product-image-slider.component.html',
  styleUrl: './product-image-slider.component.scss',
})
export class ProductImageSliderComponent {
  public images = input<ProductImage[]>([]);
  public imageIndex = signal<number>(0);
  public imageUrl = environment.imageUrl + '/';

  public constructor() {
    effect(() => {
      this.images();
      this.imageIndex.set(0);
    });
  }

  public changeMainImage(index: number): void {
    this.imageIndex.set(index);
  }
}
