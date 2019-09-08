import { Component, OnInit } from '@angular/core';
import moment from "moment/src/moment";
import { interval } from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css']
})
export class NotFoundComponent implements OnInit {
  private timeout: Number
  private location: String = ''

  constructor( private router: Router ) {}

  startCountdown = (seconds) => {
    let counter = seconds;
    this.timeout = counter
    let interval = setInterval(() => {
      counter--;
      this.timeout = counter

      if(counter < 0 ) {
        this.router.navigateByUrl('/search')
        clearInterval(interval);
      }
    }, 1000);

  }

  ngOnInit() {
    this.location = window.location.href

    this.startCountdown(10)

  }

}
