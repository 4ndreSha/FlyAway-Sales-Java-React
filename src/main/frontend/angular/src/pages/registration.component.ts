import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../components/header/header.component';
import { FooterComponent } from '../components/footer/footer.component';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, FooterComponent],
  template: `
    <app-header></app-header>
    <div class="container mt-4 vh-100">
      <h2>Регистрация</h2>
      <p *ngIf="message" class="text-danger">{{ message }}</p>
      <form (ngSubmit)="handleSubmit()">
        <div class="mb-3">
          <label>Email:</label>
          <input type="email" name="email" class="form-control" [(ngModel)]="formData.email" required>
        </div>
        <div class="mb-3">
          <label>Пароль:</label>
          <input type="password" name="password" class="form-control" [(ngModel)]="formData.password" required>
        </div>
        <div class="mb-3">
          <label>Имя:</label>
          <input type="text" name="firstName" class="form-control" [(ngModel)]="formData.firstName" required>
        </div>
        <div class="mb-3">
          <label>Фамилия:</label>
          <input type="text" name="lastName" class="form-control" [(ngModel)]="formData.lastName" required>
        </div>
        <div class="mb-3">
          <label>Отчество:</label>
          <input type="text" name="patronymic" class="form-control" [(ngModel)]="formData.patronymic">
        </div>
        <div class="mb-3">
          <label>Телефон:</label>
          <input type="text" name="phone" class="form-control" [(ngModel)]="formData.phone">
        </div>
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
      </form>
    </div>
    <app-footer></app-footer>
  `
})
export class RegistrationComponent implements OnInit {
  formData = {
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    patronymic: '',
    phone: ''
  };
  message: string = '';

  constructor(private router: Router) {}

  ngOnInit() {
    // Сохраняем предыдущий путь перед регистрацией
    const lastVisitedPath = history.state.from || '/';
    sessionStorage.setItem('lastVisitedPath', lastVisitedPath);
  }

  async handleSubmit() {
    try {
      const response = await fetch('/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.formData),
        credentials: 'include'
      });
      const result = await response.text();
      if (result === 'Registration successful') {
        const lastVisitedPath = sessionStorage.getItem('lastVisitedPath') || '/';
        this.router.navigateByUrl(lastVisitedPath);
      } else {
        this.message = result;
      }
    } catch (error) {
      this.message = 'Ошибка регистрации';
    }
  }
}
