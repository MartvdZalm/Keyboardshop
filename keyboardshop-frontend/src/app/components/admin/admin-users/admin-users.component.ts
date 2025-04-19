import { Component, inject, signal, OnInit, DestroyRef } from '@angular/core';

import { User } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { AdminTableComponent } from '../../../shared/components/admin-table/admin-table.component';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-admin-users',
  imports: [AdminTableComponent, TranslatePipe],
  templateUrl: './admin-users.component.html',
  styleUrl: './admin-users.component.scss',
})
export class AdminUsersComponent implements OnInit {
  private userService = inject(UserService);
  private destroyRef = inject(DestroyRef);
  public users = signal<User[]>([]);

  public ngOnInit(): void {
    this.userService
      .getUsers()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (users) => {
          this.users.set(users);
        },
      });
  }
}
