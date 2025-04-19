import { Component } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';
import { SpacerComponent } from '../../shared/components/spacer/spacer.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-footer',
  imports: [TranslatePipe, SpacerComponent, RouterLink],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss',
})
export class FooterComponent {}
