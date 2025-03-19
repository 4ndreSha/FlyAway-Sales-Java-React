import { Component } from '@angular/core';
import 'bootstrap/dist/css/bootstrap.min.css';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
    <footer class="bg-dark text-white text-center py-3 mt-4">
      <div class="container">
        &copy; FlyAway Sales 2025
      </div>
    </footer>
  `,
  styles: []
})
export class FooterComponent {}
