import { Component, OnInit } from '@angular/core';
import { FacultyService } from 'src/app/services/faculty/faculty.service';
import { Observable } from 'rxjs';
import { FacultyInterface } from 'src/app/interfaces/models/faculty.interface';
import { FormGroup, FormBuilder } from '@angular/forms';
import { StudentSearchService } from 'src/app/services/search/student-search.service';
import { SearchStudentResultInterface } from 'src/app/interfaces/models/search-student-result.interface';
import { SearchStudentInterface } from 'src/app/interfaces/models/search-student.interface';
import { EducationLevelInterface } from 'src/app/interfaces/models/education-level.interface';
import { EntityCrudService } from 'src/app/services/entity-crud/entity-crud.service';
import { EducationProgramInterface } from 'src/app/interfaces/models/education-program.interface';
import { DirectionInterface } from 'src/app/interfaces/models/direction.interface';
import { CountryInterface } from 'src/app/interfaces/models/country.interface';
import { EducationLanguageInterface } from 'src/app/interfaces/models/education-language.interface';
import { EducationFormInterface } from 'src/app/interfaces/models/education-form.interface';
import { ArrivalLineInterface } from 'src/app/interfaces/models/arrival-line.interface';
import { StudentService } from 'src/app/services/student/student.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public faculties$: Observable<FacultyInterface[]>;
  public educationLevels$: Observable<EducationLevelInterface[]>;
  public educationPrograms$: Observable<EducationProgramInterface[]>;
  public educationLanguage$: Observable<EducationLanguageInterface[]>;
  public educationForms$: Observable<EducationFormInterface[]>;
  public arrivalLines$: Observable<ArrivalLineInterface[]>;
  public directions$: Observable<DirectionInterface[]>;
  public countries$: Observable<CountryInterface[]>;
  public searchResult$: Observable<SearchStudentResultInterface[]>;
  public searchForm: FormGroup;

  constructor(
      private faculties: FacultyService,
      private entities: EntityCrudService,
      private searchService: StudentSearchService,
      private fb: FormBuilder,
      private studentService: StudentService,
      private router: Router,
    ) {
      this.resetForm();
    }

  ngOnInit() {
    this.faculties$ = this.faculties.getAllFaculties();
    this.educationLevels$ = this.entities.getAllEducationLevels();
    this.educationPrograms$ = this.entities.getAllEducationPrograms();
    this.educationLanguage$ = this.entities.getAllEducationLanguages();
    this.educationForms$ = this.entities.getAllEducationForms();
    this.arrivalLines$ = this.entities.getAllArrivalLines();
    this.countries$ = this.entities.getAllCountries();
    this.searchResult$ = this.searchService.search({});
  }

  public resetForm(): void {
    this.searchForm = this.fb.group({
      name: [''],
      surname: [''],
      dataOfBirth: [''],
      faculty: [null],
      direction: [null],
      educationLevel: [null],
      educationProgram: [null],
      educationLanguage: [null],
      educationForm: [null],
      arrivalLine: [null],
      country: [null],
      course: [null],
      contractNumber: [null],
    });
  }

  public onSubmit(): void {
    const searchParams = this.searchForm.value as SearchStudentInterface;
    if (searchParams.contractNumber === '') {
      delete searchParams.contractNumber;
    }
    console.log(searchParams);
    this.searchResult$ = this.searchService.search(searchParams);
  }

  public onFacultySelected(): void {
    const faculty = this.searchForm.get('faculty').value;
    if (faculty != null) {
      this.directions$ = this.entities.getDirectionByFaculty(faculty);
    } else {
      this.directions$ = undefined;
    }

    this.searchForm.get('direction').setValue(null);
  }

  public selectStudent(value: SearchStudentResultInterface): void {
    this.studentService.currentStudent = value;

    this.router.navigate(['/student-info']);
  }

}
