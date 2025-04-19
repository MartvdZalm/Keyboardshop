import { ProductImage } from './product-image.model';
import { ProductAttribute } from './product-attribute.model';

export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  quantity: number;
  slug: string;
  images: ProductImage[];
  attributes: ProductAttribute[];
  categoryId: number;
  active: boolean;
}

export interface ProductSave {
  price: number;
  quantity: number;
  images: ProductImage[];
  attributes: ProductAttribute[];
  categoryId: number;
  translations: { language: string; name: string; description: string }[];
}
