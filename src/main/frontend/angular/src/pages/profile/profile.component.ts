import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, FooterComponent],
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {
  user: any = null;
  editableUser: any = null;
  editing: boolean = false;
  message: string = '';
  tickets: any[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUserData();
    this.loadUserTickets();
  }

  loadUserData(): void {
    this.userService.getUserInfo().subscribe({
      next: (data) => {
        this.user = data;
        this.editableUser = { ...data };
      },
      error: (error) => console.error('Ошибка загрузки пользователя:', error)
    });
  }

  loadUserTickets(): void {
    this.userService.getUserTickets().subscribe({
      next: (data) => (this.tickets = data),
      error: (error) => console.error('Ошибка загрузки билетов:', error)
    });
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

  handleSave(): void {
    this.userService.updateUser(this.editableUser).subscribe({
      next: (result) => {
        if (result === 'User updated successfully') {
          this.user = { ...this.editableUser };
          this.editing = false;
          this.message = 'Данные успешно обновлены!';
          setTimeout(() => (this.message = ''), 3000);
        } else {
          this.message = 'Ошибка обновления данных';
        }
      },
      error: () => {
        this.message = 'Ошибка обновления данных';
      }
    });
  }
}
