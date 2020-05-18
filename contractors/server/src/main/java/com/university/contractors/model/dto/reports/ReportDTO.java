package com.university.contractors.model.dto.reports;

import com.university.contractors.model.jpa.domains.ReportFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Report Definition
 *
 * @author    Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @version   0.1.1
 * @since     2020-03-01
 */
@Setter
@Getter
@ToString(of = {"format"})
public class ReportDTO {

    @ApiModelProperty(position = 0)
    private ReportFormat format;

    @ApiModelProperty(position = 2)
    private List<ReportFieldUploadDTO> fields;

    /**
     * Default constructor
     */
    public ReportDTO() {
        this.fields = new ArrayList<>();
    }

    @java.beans.ConstructorProperties({"format", "fields"})
    public ReportDTO(ReportFormat format, List<ReportFieldUploadDTO> fields) {
        this.format = format;
        this.fields = fields;
    }
}
