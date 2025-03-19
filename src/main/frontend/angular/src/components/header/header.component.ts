import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule], // <-- Добавляем необходимые модули
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isAuthenticated = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.checkAuthStatus();
  }

  private checkAuthStatus() {
    fetch('/api/auth/status', { credentials: 'include' })
      .then(response => response.json())
      .then(data => {
        this.isAuthenticated = data.authenticated;
      })
      .catch(error => console.error('Ошибка проверки авторизации:', error));
  }

  handleLogout() {
    fetch('/api/auth/logout', { method: 'POST', credentials: 'include' })
      .then(() => {
        this.isAuthenticated = false;
        this.router.navigate(['/']); // Возвращаем навигацию на главную
      })
      .catch(error => console.error('Ошибка при выходе:', error));
  }
}