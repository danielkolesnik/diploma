package com.university.contractors.model.dto.reports;

import com.university.contractors.model.jpa.domains.ReportFieldType;
import com.university.contractors.repository.results.ReportFieldOption;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Report Field Upload Entity Definition
 *
 * @author   Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 */
@Setter
@Getter
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"}, callSuper = false)
public class ReportFieldUploadDTO {

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 2)
    private String name;

    @ApiModelProperty(position = 4)
    private List<ReportFieldOption> values;

    @ApiModelProperty(position = 6, allowableValues = "TEXT,DROPDOWN,DATE")
    private ReportFieldType fieldType;

    @ApiModelProperty(position = 8)
    private Boolean isSummable;

    @ApiModelProperty(position = 10)
    private Boolean isGroupable;

    @ApiModelProperty(position = 12)
    private Boolean isEditable;

    /**
     * Default constructor
     */
    public ReportFieldUploadDTO() {
        values = new ArrayList<>();
        id = 1L;
    }

    @java.beans.ConstructorProperties({"id", "name", "values", "fieldType", "isSummable", "isGroupable", "isEditable"})
    public ReportFieldUploadDTO(Long id, String name, List<ReportFieldOption> values, String fieldType, Boolean isSummable, Boolean isGroupable, Boolean isEditable) {
        this.id = id;
        this.name = name;
        this.values = values;
        this.fieldType = ReportFieldType.of(fieldType);
        this.isSummable = isSummable;
        this.isGroupable = isGroupable;
        this.isEditable = isEditable;
    }
}
