import { Component, input } from '@angular/core';
import { ProductAttribute } from '../../../models/product-attribute.model';
import { TitleCasePipe } from '@angular/common';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-product-specifications',
  imports: [TitleCasePipe, TranslatePipe],
  templateUrl: './product-specifications.component.html',
  styleUrl: './product-specifications.component.scss'
})
export class ProductSpecificationsComponent {
  public attributes = input<ProductAttribute[]>([]);
}
