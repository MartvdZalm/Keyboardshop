import { Component, input, output } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-pagination',
  imports: [TranslatePipe],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {
  public currentPage = input.required<number>();
  public totalPages = input.required<number>();
  public pageSize = input.required<number>();

  public pageChange = output<number>();

  public canGoNext(): boolean {
    return this.currentPage() < this.totalPages() - 1;
  }

  public canGoPrevious(): boolean {
    return this.currentPage() > 0;
  }

  public nextPage(): void {
    if (this.canGoNext()) {
      this.pageChange.emit(this.currentPage() + 1);
    }
  }

  public previousPage(): void {
    if (this.canGoPrevious()) {
      this.pageChange.emit(this.currentPage() - 1);
    }
  }
}
