import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  isAuthenticated = false;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.checkAuthStatus();
  }

  private checkAuthStatus(): void {
    this.authService.checkAuthStatus().subscribe({
      next: (data) => {
        this.isAuthenticated = data.authenticated;
      },
      error: (error) => console.error('Ошибка проверки авторизации:', error)
    });
  }

  handleLogout(): void {
    this.authService.logout().subscribe({
      next: () => {
        this.isAuthenticated = false;
        this.router.navigate(['/']);
      },
      error: (error) => console.error('Ошибка при выходе:', error)
    });
  }
}
