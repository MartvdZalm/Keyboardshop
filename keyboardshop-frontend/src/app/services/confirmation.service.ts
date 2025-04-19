import { Injectable, signal, WritableSignal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ConfirmationService {
  private confirmationsList = signal<Confirmation[]>([]);
  private counter = 0;

  public getConfirmations(): WritableSignal<Confirmation[]> {
    return this.confirmationsList;
  }

  public confirm(options: ConfirmationOptions): Promise<boolean> {
    return new Promise((resolve) => {
      const id = this.generateUniqueId();

      const confirmation: Confirmation = {
        ...options,
        id,
        confirmText: options.confirmText || 'Confirm',
        cancelText: options.cancelText || 'Cancel',
        onConfirm: () => {
          this.dismiss(id);
          resolve(true);
        },
        onCancel: () => {
          this.dismiss(id);
          resolve(false);
        },
      };

      this.confirmationsList.update((current) => [...current, confirmation]);
    });
  }

  public dismiss(id: string): void {
    this.confirmationsList.update((current) =>
      current.filter((c) => c.id !== id)
    );
  }

  private generateUniqueId(): string {
    this.counter++;
    return `confirmation-${this.counter}`;
  }
}

export interface ConfirmationOptions {
  title?: string;
  message: string;
  confirmText?: string;
  cancelText?: string;
}

export interface Confirmation extends ConfirmationOptions {
  id: string;
  onConfirm: () => void;
  onCancel?: () => void;
}
