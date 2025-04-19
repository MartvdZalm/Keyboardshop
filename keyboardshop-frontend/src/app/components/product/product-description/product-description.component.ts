import { Component, input } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';
import { MarkdownComponent, provideMarkdown } from 'ngx-markdown';

@Component({
  selector: 'app-product-description',
  imports: [MarkdownComponent, TranslatePipe],
  providers: [provideMarkdown()],
  templateUrl: './product-description.component.html',
  styleUrl: './product-description.component.scss',
})
export class ProductDescriptionComponent {
  public description = input<string>('');
}
