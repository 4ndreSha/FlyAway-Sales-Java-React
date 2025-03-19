import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../components/header/header.component';
import { FooterComponent } from '../components/footer/footer.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, FooterComponent],
  template: `
    <app-header></app-header>
    <div class="container mt-4 vh-100">
      <h2>Личный кабинет</h2>
      <p *ngIf="message" class="text-success">{{ message }}</p>
      
      <div class="card p-3 mb-3">
        <ng-container *ngIf="editing; else viewProfile">
          <div class="mb-3">
            <label class="form-label">Email:</label>
            <input type="email" name="email" class="form-control" [(ngModel)]="editableUser.email" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Имя:</label>
            <input type="text" name="firstName" class="form-control" [(ngModel)]="editableUser.firstName" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Фамилия:</label>
            <input type="text" name="lastName" class="form-control" [(ngModel)]="editableUser.lastName" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Отчество:</label>
            <input type="text" name="patronymic" class="form-control" [(ngModel)]="editableUser.patronymic">
          </div>
          <div class="mb-3">
            <label class="form-label">Телефон:</label>
            <input type="text" name="phone" class="form-control" [(ngModel)]="editableUser.phone">
          </div>
          <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-success me-md-2" (click)="handleSave()">Сохранить</button>
            <button class="btn btn-secondary" (click)="editing = false">Отмена</button>
          </div>
        </ng-container>
        <ng-template #viewProfile>
          <h4>{{ user.firstName }} {{ user.lastName }}</h4>
          <p>Email: {{ user.email }}</p>
          <p *ngIf="user.patronymic">Отчество: {{ user.patronymic }}</p>
          <p>Телефон: {{ user.phone }}</p>
          <button class="btn btn-primary" (click)="editing = true">Редактировать профиль</button>
        </ng-template>
      </div>

      <h3>История бронирований</h3>
      <ng-container *ngIf="tickets.length === 0; else ticketsTable">
        <p>Нет забронированных билетов.</p>
      </ng-container>
      <ng-template #ticketsTable>
        <div class="table-responsive">
          <table class="table table-striped table-hover">
            <thead class="thead-dark">
              <tr>
                <th>Номер билета</th>
                <th>Номер брони</th>
                <th>Дата бронирования</th>
                <th>Сумма</th>
                <th>Статус</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let ticket of tickets">
                <td>{{ ticket.ticketNo }}</td>
                <td>{{ ticket.bookRef }}</td>
                <td>{{ formatDate(ticket.bookDate) }}</td>
                <td>{{ formatCurrency(ticket.totalAmount) }}</td>
                <td>
                  <span class="badge bg-success">
                    {{ ticket.status || 'Подтверждено' }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </ng-template>
    </div>
    <app-footer></app-footer>
  `
})
export class ProfileComponent implements OnInit {
  user: any = null;
  editableUser: any = null;
  editing: boolean = false;
  message: string = '';
  tickets: any[] = [];

  async ngOnInit() {
    try {
      const response = await fetch('/api/user/info', { credentials: 'include' });
      const data = await response.json();
      this.user = data;
      this.editableUser = { ...data };
    } catch (error) {
      console.error('Ошибка загрузки пользователя:', error);
    }

    try {
      const response = await fetch('/api/user/tickets', { credentials: 'include' });
      this.tickets = await response.json();
    } catch (error) {
      console.error('Ошибка загрузки билетов:', error);
    }
  }

  formatCurrency(amount: number): string {
    return new Intl.NumberFormat('ru-RU', {
      style: 'currency',
      currency: 'RUB',
      minimumFractionDigits: 2
    }).format(amount);
  }

  formatDate(dateStr: string): string {
    return new Date(dateStr).toLocaleDateString('ru-RU', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  async handleSave() {
    try {
      const response = await fetch('/api/user/update', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.editableUser),
        credentials: 'include'
      });
      const result = await response.text();
      if (result === 'User updated successfully') {
        this.user = { ...this.editableUser };
        this.editing = false;
        this.message = 'Данные успешно обновлены!';
        setTimeout(() => this.message = '', 3000);
      } else {
        this.message = 'Ошибка обновления данных';
      }
    } catch (error) {
      this.message = 'Ошибка обновления данных';
    }
  }
}
