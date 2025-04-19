import { OrderProduct } from "./order-product.model";
import { User } from "./user.model";

export interface Order {
  id: number;
  user: User;
  guestEmail: string;
  guestFirstName: string;
  guestLastName: string;
  streetName: string;
  houseNumber: string;
  postalCode: string;
  city: string;
  total: number;
  status: string;
  products: OrderProduct[];
  createdAt: string;
};