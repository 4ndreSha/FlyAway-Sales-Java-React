import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../components/header/header.component';
import { FooterComponent } from '../components/footer/footer.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, FooterComponent],
  template: `
    <app-header></app-header>
    <div class="container mt-4 vh-100">
      <h2>Вход</h2>
      <p *ngIf="message" class="text-danger">{{ message }}</p>
      <form (ngSubmit)="handleSubmit()">
        <div class="mb-3">
          <label>Email:</label>
          <input
            type="email"
            name="email"
            class="form-control"
            [(ngModel)]="credentials.email"
            required
          >
        </div>
        <div class="mb-3">
          <label>Пароль:</label>
          <input
            type="password"
            name="password"
            class="form-control"
            [(ngModel)]="credentials.password"
            required
          >
        </div>
        <button type="submit" class="btn btn-primary">Войти</button>
      </form>
    </div>
    <app-footer></app-footer>
  `
})
export class LoginComponent implements OnInit {
  credentials = { email: '', password: '' };
  message: string = '';

  constructor(private router: Router) {}

  ngOnInit() {
    const lastVisitedPath = history.state.from || '/';
    sessionStorage.setItem('lastVisitedPath', lastVisitedPath);
  }

  async handleSubmit() {
    try {
      const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.credentials),
        credentials: 'include'
      });
      const result = await response.text();
      if (result === 'Login successful') {
        const lastVisitedPath = sessionStorage.getItem('lastVisitedPath') || '/';
        this.router.navigateByUrl(lastVisitedPath);
      } else {
        this.message = result;
      }
    } catch (error) {
      this.message = 'Ошибка авторизации';
    }
  }
}
