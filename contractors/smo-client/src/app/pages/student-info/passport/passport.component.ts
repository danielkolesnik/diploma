import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-passport',
  templateUrl: './passport.component.html',
  styleUrls: ['./passport.component.css']
})
export class PassportComponent implements OnInit {
  public showNotes = false;
  constructor() { }

  ngOnInit() {
  }

  toggleNotes(){
    this.showNotes = !this.showNotes;
  }
}
