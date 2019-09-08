import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { User } from 'src/app/interfaces/user.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;
  public loginSuccess = undefined;
  public loginInProgress = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private localStorageService: LocalStorageService,
    formBuilder: FormBuilder
    ) {
      this.loginForm = formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      });
    }

  ngOnInit() {
    if (this.authService.isLoggedIn) { this.router.navigate(['/']); }
  }

  public onSubmit(): void {
    if (this.loginForm.valid) {
      this.loginInProgress = true;
      this.authService.auth({
        username: this.loginForm.controls['username'].value,
        password: this.loginForm.controls['password'].value,
      }).subscribe((user: User) => {
          this.loginInProgress = false;
          this.router.navigate(['/']);
        },
        error => {
          this.loginInProgress = false;
          this.loginSuccess = false;
        }
      );
    }
  }

}
