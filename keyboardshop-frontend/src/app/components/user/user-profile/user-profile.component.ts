import { Component, inject, OnInit, signal } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.model';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-user-profile',
  imports: [TranslatePipe],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss',
})
export class UserProfileComponent implements OnInit {
  private userService = inject(UserService);
  public user = signal<User | undefined>(undefined);

  public ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: (response) => {
        this.user.set(response);
      },
    });
  }
}
