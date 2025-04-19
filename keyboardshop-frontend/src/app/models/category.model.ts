import { Product } from './product.model';

export interface Category {
  id: number;
  name: string;
  description: string;
  slug: string;
  image: string;
  products: Product[];
};