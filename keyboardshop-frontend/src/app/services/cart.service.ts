import { Injectable, signal, WritableSignal } from '@angular/core';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartKey = 'cart';
  private cartProducts = signal<Product[]>(this.getCartFromStorage());
  private totalPrice = signal<number>(this.calculateTotalPrice());
  private totalProducts = signal<number>(this.calculateTotalQuantity());

  private getCartFromStorage(): Product[] {
    const storedCart = localStorage.getItem(this.cartKey);
    return storedCart ? JSON.parse(storedCart) : [];
  }

  private saveCartToStorage(cart: Product[]): void {
    localStorage.setItem(this.cartKey, JSON.stringify(cart));
    this.cartProducts.set(cart);
    this.updateCartSummary();
  }

  private updateCartSummary(): void {
    this.totalPrice.set(this.calculateTotalPrice());
    this.totalProducts.set(this.calculateTotalQuantity());
  }

  private calculateTotalPrice(): number {
    return this.cartProducts().reduce(
      (total, product) => total + product.price * product.quantity,
      0
    );
  }

  private calculateTotalQuantity(): number {
    return this.cartProducts().reduce(
      (total, product) => total + product.quantity,
      0
    );
  }

  public getProductFromCart(productId: number): Product | undefined {
    const cart = this.cartProducts();
    const product = cart.find((item) => item.id === productId);
    return product;
  }

  public getProductTotalPrice(productId: number): number {
    const product = this.getProductFromCart(productId);
    return product!.quantity * product!.price;
  }

  public addProduct(product: Product): void {
    const cart = this.cartProducts();
    const existingProductIndex = cart.findIndex(
      (item) => item.id === product.id
    );

    let updatedCart: Product[];

    if (existingProductIndex !== -1) {
      updatedCart = [...cart];
      updatedCart[existingProductIndex] = {
        ...updatedCart[existingProductIndex],
        quantity: updatedCart[existingProductIndex].quantity + product.quantity,
      };
    } else {
      updatedCart = [...cart, product];
    }

    this.saveCartToStorage(updatedCart);
  }

  public removeProduct(productId: number): void {
    let cart = this.cartProducts();
    cart = cart.filter((product) => product.id !== productId);
    this.saveCartToStorage(cart);
  }

  public getProducts(): WritableSignal<Product[]> {
    return this.cartProducts;
  }

  public clearCart(): void {
    localStorage.removeItem(this.cartKey);
    this.cartProducts.set([]);
    this.updateCartSummary();
  }

  public getTotalQuantity(): number {
    return this.totalProducts();
  }

  public getTotalPrice(): WritableSignal<number> {
    return this.totalPrice;
  }

  public getTotalProducts(): WritableSignal<number> {
    return this.totalProducts;
  }

  public updateQuantity(productId: number, newQuantity: number): void {
    const updatedCart = this.cartProducts().map((product) =>
      product.id === productId ? { ...product, quantity: newQuantity } : product
    );

    this.saveCartToStorage(updatedCart);
  }

  public increaseQuantity(productId: number): void {
    const updatedCart = this.cartProducts().map((product) =>
      product.id === productId
        ? { ...product, quantity: product.quantity + 1 }
        : product
    );

    this.saveCartToStorage(updatedCart);
  }

  public decreaseQuantity(productId: number): void {
    const updatedCart = this.cartProducts().map((product) =>
      product.id === productId
        ? { ...product, quantity: Math.max(1, product.quantity - 1) }
        : product
    );

    this.saveCartToStorage(updatedCart);
  }
}
