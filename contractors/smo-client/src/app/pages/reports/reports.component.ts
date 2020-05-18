import { Component, OnInit, OnDestroy } from "@angular/core";
import { HttpService } from "../../services/http/http.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Observable, Subject} from "rxjs";
import {ReportFieldUploadInterface, ReportFieldInterface} from "../../interfaces/models/report-field.interface";
import filter from "lodash-es/filter";
import {takeUntil} from "rxjs/operators";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ReportConfirmationComponent} from "../../components/modals/report-confirmation/report-confirmation.component";
import {ReportFormatTypeEnum} from "../../enums/report-format-type.enum";
import { saveAs } from "file-saver";

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html'
})
export class ReportsComponent implements OnInit, OnDestroy {

  public reportFields$: Observable<ReportFieldInterface[]>;
  private destroy$: Subject<any> = new Subject();
  public fields: ReportFieldInterface[] = [];
  public reportToolbarForm: FormGroup;
  public addedFields: ReportFieldInterface[] = [];
  public submitReportSubject: Subject<any> = new Subject();
  public confirmationModalRef: BsModalRef;

  constructor (private httpService: HttpService, private formBuilder: FormBuilder, private modalService: BsModalService) {
    this.initializeReportToolbarForm();
  }

  ngOnInit(): void {
    this.reportFields$ = this.httpService.get("entity/report/fields");
    this.reportFields$.pipe(takeUntil(this.destroy$)).subscribe(value => {
      this.fields = value;
    });
  }

  initializeReportToolbarForm () {
    this.reportToolbarForm = this.formBuilder.group({
      additionField: [null]
    });
  }

  handleAddField(): void {
    let additionField = this.reportToolbarForm.get('additionField').value;
    if (additionField !== null) {
      this.addedFields.push(additionField);
      // remove added to report field from list of fields available to select
      this.fields = filter(this.fields, field => field.id !== additionField.id);
      this.reportToolbarForm.get('additionField').setValue(null);
    }
  }

  handleDeleteField(removedFromReportField: ReportFieldInterface): void {
    this.fields.push(removedFromReportField);
    // remove field from report
    this.addedFields = filter(this.addedFields, field => field.id !== removedFromReportField.id);
  }

  triggerSubmitReport() {
    // triggers report component submit process (way to initiate process from outside of the component)
    this.submitReportSubject.next(true);
  }

  handleReportValues(resultReportFields: ReportFieldUploadInterface[]) {

    // Show confirmation modal window
    this.confirmationModalRef = this.modalService.show(ReportConfirmationComponent, {
      initialState: {
        reportFields: resultReportFields,
        title: 'Перевiрте обранi поля'
      },
      class: 'modal-xl'
    });

    // Send to server if confirmed or continue editing
    this.confirmationModalRef.content.onClose.subscribe((reportFormatType: ReportFormatTypeEnum) => {
      if (reportFormatType !== null) {
        // console.log('%c FORMAT', 'color: purple; font-size: 16px', reportFormatType);
        this.httpService._post(
          `entity/report/fields`,
          {
            format: reportFormatType,
            fields: resultReportFields
          }, { responseType: 'blob'})
          .toPromise()
          .then(result => {
            // download
            const blob = new Blob([result], { type: 'application/octet-stream' });
            saveAs(blob, "name.xlsx");
            console.log("%c SUCCESS Report Generated", 'font-size: 16px; color: green;', result);
          })
          .catch(error => {
            // notify
            console.log("%c ERROR While Generating Report", 'font-size: 16px; color: red;', error);
          })
          .finally(() => {
            // redirect
          });
      }
    })

  }

  ngOnDestroy(): void {
    // trigger the unsubscribe for httpService
    this.destroy$.next();
    // finalize & clean up the subject stream
    this.destroy$.complete();
  }



}
