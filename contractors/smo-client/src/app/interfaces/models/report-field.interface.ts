import {ReportFieldTypeEnum} from "../../enums/report-field-type.enum";

export interface ReportFieldOptionInterface {
  id: number,
  value: string
}

export interface ReportFieldInterface {
  id: number,
  name: string,
  options: ReportFieldOptionInterface[],
  fieldType: ReportFieldTypeEnum,
  isSummable: boolean,
  isGroupable: boolean,
  isEditable: boolean,
}

export interface ReportFieldUploadInterface {
  id: number,
  name: string,
  values: ReportFieldOptionInterface[],
  fieldType: ReportFieldTypeEnum,
  isSummable: boolean,
  isGroupable: boolean,
  isEditable: boolean
}
