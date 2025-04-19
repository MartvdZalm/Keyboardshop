import { Product } from "./product.model";

export interface OrderProduct {
  id: number;
  product: Product;
  productImage: string;
  productName: string;
  productDescription: string;
  productPrice: number;
  quantity: number;
};