import { Component, inject } from '@angular/core';
import { ConfirmationService } from '../../../services/confirmation.service';

@Component({
  selector: 'app-confirmation',
  imports: [],
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.scss',
})
export class ConfirmationComponent {
  private confirmationService = inject(ConfirmationService);
  public confirmations = this.confirmationService.getConfirmations();
}
