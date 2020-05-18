package com.university.contractors.model.jpa.domains;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = {"id", "title"})
public enum ReportFieldName {

    COUNTER(1L, "#"),
    STUDENT_SURNAME(2L, "Фамилия"),
    STUDENT_NAME(3L, "Имя"),
    STUDENT_MIDDLENAME(4L, "Отчество"),
    COUNTRY(5L, "Страна"),
    BIRTHDATE(6L, "Дата рождения"),
    FACULTY(7L, "Факультет"),
    DIRECTION(8L, "Направление"),
    EDUCATION_FORM(9L, "Форма обучения"),
    EDUCATION_LEVEL(10L, "Уроверь обучения"),
    EDUCATION_PROGRAM(11L, "Образовательная программа"),
    ENTRANCE_DATE(12L, "Дата зачисления"),
    GRADUATION_DATE(13L, "Дата окончания"),
    ARRIVAL_LINE(14L, "Линия прибытия"),
    COURSE(15L, "Курс"),
    EDUCATION_LANGUAGE(16L, "Язык обучения"),
    STATUS(17L, "Статус"),
    ENROLLMENT(18L, "Набор"),
    CONTRACT_VALUE(19L, "Сумма контракта"),
    ENROLLMENT_ORDER_NUMBER(20L, "Номер приказа зачисл"),
    CONTRACT_NUMBER(21L, "Номер контракта"),
    CONTRACT_TYPE(22L, "Тип контракта"),
    PAYER(23L, "Плательщик"),
    PREPAY_HRN(24L, "Предоплата в гривнах"),
    PREPAY_USD(25L, "Предоплата в валюте"),
    PAYMENT_HRN(26L, "Оплата в гривнах"),
    PAYMENT_USD(27L, "Оплата в валюте"),
    DEBT_HRN(28L, "Долг в гривнах"),
    DEBT_USD(29L, "Долг в валюте"),
    FINE_HRN(30L, "Пеня в гривнах"),
    FINE_USD(31L, "Пеня в валюте"),
    PERIOD_FROM(32L, "Период с"),
    PERIOD_TO(33L, "Период по"),
    ENROLLMENT_ORDER_DATE(35L, "Дата приказа зачисл"),
    NATIONALITY(36L, "Национальность"),
    DEDUCTION_ORDER_NUMBER(37L, "Номер приказа отчисления"),
    DEDUCTION_ORDER_DATE(38L, "Дата приказа отчисления"),
    DEDUCTION_REASON(39L, "Причина отчисления");

    public final Long id;
    // In case of localizations and/or scaling the app this
    // values should be moved into report_fields data base table as additional field
    // or even in separate table e.g. language_constants_ru
    public final String title;

    private ReportFieldName(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Returns Report Field Name by ID
     *
     * @param id
     * @return ReportFieldName
     */
    public static ReportFieldName of(Long id) {
        ReportFieldName result = null;

        if (id != null) {
            switch (id.intValue()) {
                case 1:
                return COUNTER;
                case 2:
                return STUDENT_SURNAME;
                case 3:
                return STUDENT_NAME;
                case 4:
                return STUDENT_MIDDLENAME;
                case 5:
                return COUNTRY;
                case 6:
                return BIRTHDATE;
                case 7:
                return FACULTY;
                case 8:
                return DIRECTION;
                case 9:
                return EDUCATION_FORM;
                case 10:
                return EDUCATION_LEVEL;
                case 11:
                return EDUCATION_PROGRAM;
                case 12:
                return ENTRANCE_DATE;
                case 13:
                return GRADUATION_DATE;
                case 14:
                return ARRIVAL_LINE;
                case 15:
                return COURSE;
                case 16:
                return EDUCATION_LANGUAGE;
                case 17:
                return STATUS;
                case 18:
                return ENROLLMENT;
                case 19:
                return CONTRACT_VALUE;
                case 20:
                return ENROLLMENT_ORDER_NUMBER;
                case 21:
                return CONTRACT_NUMBER;
                case 22:
                return CONTRACT_TYPE;
                case 23:
                return PAYER;
                case 24:
                return PREPAY_HRN;
                case 25:
                return PREPAY_USD;
                case 26:
                return PAYMENT_HRN;
                case 27:
                return PAYMENT_USD;
                case 28:
                return DEBT_HRN;
                case 29:
                return DEBT_USD;
                case 30:
                return FINE_HRN;
                case 31:
                return FINE_USD;
                case 32:
                return PERIOD_FROM;
                case 33:
                return PERIOD_TO;
                case 35:
                return ENROLLMENT_ORDER_DATE;
                case 36:
                return NATIONALITY;
                case 37:
                return DEDUCTION_ORDER_NUMBER;
                case 38:
                return DEDUCTION_ORDER_DATE;
                case 39:
                return DEDUCTION_REASON;
            }
        }

        return result;
    }

}
