import { Component } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterLink } from '@angular/router';
import { SpacerComponent } from '../../shared/components/spacer/spacer.component';

@Component({
  selector: 'app-not-found',
  imports: [
    TranslatePipe,
    HeaderComponent,
    FooterComponent,
    RouterLink,
    SpacerComponent,
  ],
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.scss',
})
export class NotFoundComponent {}
