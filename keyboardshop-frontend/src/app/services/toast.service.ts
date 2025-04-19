import { inject, Injectable, signal, WritableSignal } from '@angular/core';
import { Toast } from '../models/toast.model';
import { TranslateService } from '@ngx-translate/core';

@Injectable({ 
  providedIn: 'root' 
})
export class ToastService {
  private translateService = inject(TranslateService);
  private toastsList = signal<Toast[]>([]);
  private counter = 0;
  private lastToastMap = new Map<string, number>();
  private readonly DEBOUNCE_TIME = 1000;
  private readonly MAX_TOASTS = 3;

  public getToasts(): WritableSignal<Toast[]> {
    return this.toastsList;
  }

  public show(toast: Toast): string {
    if (this.toastsList().length >= this.MAX_TOASTS) {
      return '';
    }

    const now = Date.now();
    const lastShown = this.lastToastMap.get(toast.message);
    
    if (lastShown && (now - lastShown) < this.DEBOUNCE_TIME) {
      return '';
    }

    this.lastToastMap.set(toast.message, now);
    
    const id = this.generateUniqueId();
    const toastWithId = { ...toast, id };

    this.toastsList.update((current) => [...current, toastWithId]);

    if (toast.duration) {
      setTimeout(() => this.dismiss(id), toast.duration);
    }

    return id;
  }

  public dismiss(id: string): void {
    this.toastsList.update((current) => current.filter((t) => t.id !== id));
  }

  public success(message: string, duration = 3000): string {
    return this.show({ message, type: 'success', duration });
  }

  public error(message: string, duration = 3000): string {
    return this.show({ message, type: 'error', duration });
  }

  public info(message: string, duration = 3000): string {
    return this.show({ message, type: 'info', duration });
  }

  public warning(message: string, duration = 3000): string {
    return this.show({ message, type: 'warning', duration });
  }

  private generateUniqueId(): string {
    this.counter++;
    return `toast-${this.counter}`;
  }

  public showTranslatedToast(
    key: string,
    type: 'success' | 'error' | 'warning' = 'success',
    params?: Record<string, string | number | boolean>
  ): void {
    this.translateService.get(key, params).subscribe((message: string) => {
      this[type](message, 1500);
    });
  }
}
