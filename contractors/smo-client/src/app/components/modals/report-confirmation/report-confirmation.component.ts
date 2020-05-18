import {Component, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {ReportFieldUploadInterface} from "../../../interfaces/models/report-field.interface";
import {faCheck, faTimes} from "@fortawesome/free-solid-svg-icons";
import {Subject} from "rxjs";
import {ReportFormatTypeEnum} from "../../../enums/report-format-type.enum";

@Component({
  selector: 'app-report-confirmation',
  templateUrl: './report-confirmation.component.html'
})
export class ReportConfirmationComponent implements OnInit {

  public onClose: Subject<ReportFormatTypeEnum>;
  title: string;
  text: string;
  reportFields: ReportFieldUploadInterface[] = [];
  exportFileExtensions: string[] = [];
  faCheck = faCheck;
  faTimes = faTimes;

  constructor(public bsModalRef: BsModalRef) {
    this.onClose = new Subject<ReportFormatTypeEnum>();
  }

  ngOnInit(): void {

  }

  onCancel() {
    this.onClose.next(null);
    this.bsModalRef.hide();
  }

  onConfirm() {
    this.onClose.next(ReportFormatTypeEnum.XLS);
    this.bsModalRef.hide();
  }

}
