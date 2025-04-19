import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-user-sidebar',
  imports: [RouterLink, RouterLinkActive, TranslatePipe],
  templateUrl: './user-sidebar.component.html',
  styleUrl: './user-sidebar.component.scss',
})
export class UserSidebarComponent {
  private authService = inject(AuthService);
  private router = inject(Router);
  public isAdmin = this.authService.hasRole('ADMIN');

  public onLogout(): void {
    this.authService.logout();
  }

  public navigateTo(route: string): void {
    this.router.navigate([route]);
  }
}
