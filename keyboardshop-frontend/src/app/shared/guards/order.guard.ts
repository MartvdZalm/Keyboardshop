import { inject } from "@angular/core";
import { CanMatchFn, RedirectCommand, Router } from "@angular/router";
import { CartService } from "../../services/cart.service";

export const orderGuard: CanMatchFn = () => {
  const router = inject(Router);
  const cartService = inject(CartService);

  if (cartService.getProducts()().length > 0) {
    return true;
  }

  return new RedirectCommand(router.parseUrl("/cart"))
};