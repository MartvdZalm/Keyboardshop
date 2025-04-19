import { Product } from "./product.model";

export interface Pagination {
  content: Product[];
  page: number;
  size: number;
  totalPages: number;
  totalElements: number;
}