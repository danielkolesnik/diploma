import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nostrification',
  templateUrl: './nostrification.component.html',
  styleUrls: ['./nostrification.component.css']
})
export class NostrificationComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
  addDocument() {
    this.router.navigate(['nostrification/add']);
  }
}
