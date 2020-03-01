import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student/student.service';
import { SearchStudentResultInterface } from 'src/app/interfaces/models/search-student-result.interface';

@Component({
  selector: 'app-student-info',
  templateUrl: './student-info.component.html',
  styleUrls: ['./student-info.component.css']
})
export class StudentInfoComponent implements OnInit {

  public currentStudent: SearchStudentResultInterface;

  constructor(
    private student: StudentService,
  ) { 
    this.currentStudent = this.student.currentStudent;
  }

  ngOnInit() {
    console.log(this.currentStudent);
    
  }

}
