package com.university.contractors.model.dto.reports;

import com.university.contractors.model.dto.students.DTOBase;
import com.university.contractors.model.jpa.entity.ReportFields;
import com.university.contractors.repository.results.ReportFieldOption;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Report Fields View Entity Definition
 *
 * @author    Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @version   0.1.1
 * @since     2020-03-01
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(of = {"id", "name", "fieldType"})
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class ReportFieldViewDTO extends DTOBase<ReportFields> {

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 2)
    private String name;

    @ApiModelProperty(position = 4)
    private List<ReportFieldOption> options;

    @ApiModelProperty(position = 6, allowableValues = "TEXT,DROPDOWN,DATE")
    private String fieldType;

    @ApiModelProperty(position = 8)
    private Boolean isSummable;

    @ApiModelProperty(position = 10)
    private Boolean isGroupable;

    @ApiModelProperty(position = 12)
    private Boolean isEditable;

    /**
     * Entity based constructor
     *
     * @param entity    Base Entity
     */
    public ReportFieldViewDTO(ReportFields entity) {
        super(entity);
    }

    @Override
    public void fromEntity(ReportFields entity) {
        id = entity.getId();
        name = entity.getName();
        fieldType = entity.getFieldType();
        isSummable = entity.getIsSummable();
        isGroupable = entity.getIsGroupable();
        isEditable = entity.getIsEditable();

        options = new ArrayList<>();
    }

}
