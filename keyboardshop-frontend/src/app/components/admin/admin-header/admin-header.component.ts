import {
  Component,
  inject,
  signal,
  OnInit,
  DestroyRef,
  output,
} from '@angular/core';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-admin-header',
  imports: [],
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.scss',
})
export class AdminHeaderComponent implements OnInit {
  private userService = inject(UserService);
  private destroyRef = inject(DestroyRef);
  public user = signal<User | undefined>(undefined);
  public toggleSidebar = output();

  public ngOnInit(): void {
    this.userService
      .getUser()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.user.set(response);
        },
      });
  }

  public onToggleSidebar(): void {
    this.toggleSidebar.emit();
  }
}
