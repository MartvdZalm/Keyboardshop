import { inject } from "@angular/core";
import { CanMatchFn, Router, RedirectCommand } from "@angular/router";
import { AuthService } from "../../services/auth.service";

export const adminGuard: CanMatchFn = () => {
    const authService = inject(AuthService);
    const router = inject(Router);

    if (authService.hasRole("ADMIN")) {
        return true;
    }

    return new RedirectCommand(router.parseUrl("/"));
};