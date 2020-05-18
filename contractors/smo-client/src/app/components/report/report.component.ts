import {
  Component,
  DoCheck,
  EventEmitter,
  Input,
  IterableDiffer,
  IterableDiffers,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import {ReportFieldUploadInterface, ReportFieldInterface} from "../../interfaces/models/report-field.interface";
import {ReportFieldTypeEnum} from "../../enums/report-field-type.enum";
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {faTrashAlt} from "@fortawesome/free-solid-svg-icons";
import each from "lodash/each";
import {Subject} from "rxjs";


@Component({
  selector: 'app-report',
  templateUrl: './report.component.html'
})
export class ReportComponent implements OnInit, DoCheck, OnDestroy {
  @Input() fields: ReportFieldInterface[];
  @Input() submitReportSubject: Subject<any>;
  @Output() onDeleteField: EventEmitter<any> = new EventEmitter();
  @Output() onReportSubmit: EventEmitter<any> = new EventEmitter();

  public FIELD_TYPE = ReportFieldTypeEnum;
  public faTrashAlt = faTrashAlt;
  public dropdownSettings: IDropdownSettings = {};

  private submitted: boolean = false;
  private iterableDiffer: IterableDiffer<{}>;
  // map for operating with entered values for fields
  // key (String) - id of field
  // value (ReportFieldUploadInterface) - field with values
  private selectedFields = {};
  private reportForm: FormGroup;

  // convenience getter for easy access to form fields
  get f() { return this.reportForm.controls; }

  constructor(private iterableDiffers: IterableDiffers, private formBuilder: FormBuilder) {
    this.initializeReportForm();
    this.iterableDiffer = iterableDiffers.find([]).create(null);
  }

  ngDoCheck() {
    let changes = this.iterableDiffer.diff(this.fields);
    // if array of fields changed, then we need to update our
    if (changes) {
      this.fields.forEach(field => {
        // adds
        if (!this.selectedFields[field.id]) {
          this.selectedFields[field.id] = {
            id: field.id,
            name: field.name,
            fieldType: field.fieldType,
            values: [],
            isSummable: false,
            isGroupable: false,
            isEditable: field.isEditable
          };
          if (field.isEditable && (field.fieldType === this.FIELD_TYPE.DATE || field.fieldType === this.FIELD_TYPE.TEXT)) {
            this.reportForm.addControl(field.id + '@value', new FormControl('', Validators.required));
          } else if (field.fieldType === this.FIELD_TYPE.DROPDOWN) {
            this.reportForm.addControl(field.id + '@values', new FormControl({value: ''}, MultiselectDropdownValidator));
          }
          this.reportForm.addControl(field.id + '@sum', new FormControl({value: '', disabled: !field.isSummable}));
          this.reportForm.addControl(field.id + '@group', new FormControl({value: '', disabled: !field.isGroupable}));
        }
      });
    }
  }

  ngOnInit() {
    this.submitReportSubject.subscribe(value => {
      if (value === true) {
        this.submitted = true;
        this.handleReportConfirmation();
      }
    });
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'value',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }

  handleDeleteField(field: ReportFieldInterface): void {
    try {
      this.reportForm.removeControl(field.id + '@values');
      this.reportForm.removeControl(field.id + '@value');
      this.reportForm.removeControl(field.id + '@sum');
      this.reportForm.removeControl(field.id + '@group');
    } catch (e) {}
    delete this.selectedFields[field.id];
    this.onDeleteField.emit(field);
  }

  handleReportConfirmation() {
    // discard report confirmation in case of form errors
    if (this.reportForm.invalid) {
      return;
    } else if (Object.keys(this.selectedFields).length === 0) {
      // TODO: provide some popup or toast to notify user that he should select any fields before submitting
      this.submitted = false;
      return;
    }

    let resultReport: ReportFieldUploadInterface[] = [];

    each(this.selectedFields, (selectedField, key) => {
      let resultReportField = {...selectedField};


      if ((selectedField.fieldType == ReportFieldTypeEnum.TEXT || selectedField.fieldType == ReportFieldTypeEnum.DATE) && selectedField.isEditable) {
        resultReportField.values = [{id: null, value: this.reportForm.get(key + '@value').value}];

      } else if (selectedField.fieldType == ReportFieldTypeEnum.DROPDOWN) {
        resultReportField.values = this.reportForm.get(key + '@values').value;
      }

      resultReportField.isSummable = this.reportForm.get(key + '@sum').value || false;
      resultReportField.isGroupable = this.reportForm.get(key + '@group').value || false;
      resultReport.push(resultReportField);
    });
    this.onReportSubmit.emit(resultReport);
  }

  initializeReportForm () {
    let formFields = {};
    this.reportForm = this.formBuilder.group(formFields);
  }

  ngOnDestroy(): void {
    this.submitReportSubject.unsubscribe();
  }

}

function MultiselectDropdownValidator(control: AbstractControl) {
  if (!(Array.isArray(control.value) && control.value.length > 0)) {
    return { required: true };
  }
  return null;
}
