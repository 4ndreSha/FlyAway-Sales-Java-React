import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, FooterComponent],
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  credentials = { email: '', password: '' };
  message: string = '';

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    const lastVisitedPath = history.state.from || '/';
    sessionStorage.setItem('lastVisitedPath', lastVisitedPath);
  }

  handleSubmit(): void {
    this.authService.login(this.credentials).subscribe({
      next: (result) => {
        if (result === 'Login successful') {
          const lastVisitedPath = sessionStorage.getItem('lastVisitedPath') || '/';
          this.router.navigateByUrl(lastVisitedPath);
        } else {
          this.message = result;
        }
      },
      error: () => {
        this.message = 'Ошибка авторизации';
      }
    });
  }
}
