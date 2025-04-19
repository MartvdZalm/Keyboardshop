import { Component, inject } from '@angular/core';
import { animate, style, transition, trigger } from '@angular/animations';
import { ToastService } from '../../../services/toast.service';
import { Toast } from '../../../models/toast.model';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.scss',
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateX(100%)' }),
        animate('300ms ease-out', 
          style({ opacity: 1, transform: 'translateX(0)' }))
      ]),
      transition(':leave', [
        animate('300ms ease-in', 
          style({ opacity: 0, transform: 'translateX(100%)' }))
      ])
    ])
  ]
})
export class ToastComponent {
  private toastService = inject(ToastService);
  public toasts = this.toastService.getToasts();

  public getIcon(type: Toast['type']): string {
    const icons = {
      success: 'check_circle',
      error: 'error',
      info: 'info',
      warning: 'warning'
    };
    return icons[type];
  }

  public dismiss(id: string): void {
    this.toastService.dismiss(id);
  }
}