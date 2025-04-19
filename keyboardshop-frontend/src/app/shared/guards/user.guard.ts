import { inject } from "@angular/core";
import { CanMatchFn, RedirectCommand, Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";

export const userGuard: CanMatchFn = () => {
  const router = inject(Router);
  const authService = inject(AuthService);

  if (authService.isAuthenticated()) {
    return true;
  }

  return new RedirectCommand(router.parseUrl("/login"))
};