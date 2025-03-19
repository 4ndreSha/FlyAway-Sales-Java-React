import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, FooterComponent],
  templateUrl: './registration.component.html'
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

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    const lastVisitedPath = history.state.from || '/';
    sessionStorage.setItem('lastVisitedPath', lastVisitedPath);
  }

  handleSubmit(): void {
    this.authService.register(this.formData).subscribe({
      next: (result) => {
        if (result === 'Registration successful') {
          const lastVisitedPath = sessionStorage.getItem('lastVisitedPath') || '/';
          this.router.navigateByUrl(lastVisitedPath);
        } else {
          this.message = result;
        }
      },
      error: () => {
        this.message = 'Ошибка регистрации';
      }
    });
  }
}
