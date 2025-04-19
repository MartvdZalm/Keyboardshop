import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UserSidebarComponent } from './user-sidebar/user-sidebar.component';
import { SpacerComponent } from '../../shared/components/spacer/spacer.component';

@Component({
  selector: 'app-user',
  imports: [RouterOutlet, UserSidebarComponent, SpacerComponent],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss',
})
export class UserComponent {}
