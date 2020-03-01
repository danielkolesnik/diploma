import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchStudentResultInterface } from 'src/app/interfaces/models/search-student-result.interface';

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
export class PersonalDataComponent implements OnInit {

  @Input() public student: SearchStudentResultInterface;

  @ViewChild("DataForm") form: NgForm;
  public showContracts = false;
  public showOrders = false;
  constructor(private router:Router) { }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.form.value);
    this.form.reset();
  }
  close(){
    this.router.navigate(['']);
  }
  toggleContracts(){
    this.showContracts = !this.showContracts;
    
  }
  toggleOrders(){
    this.showOrders = !this.showOrders;
  }
}
