package com.university.contractors.repository.results;

import lombok.*;

/**
 * Report Field Answer Option Definition
 *
 * @author    Daniel A. Kolesnik <daneelkolesnik@gmail.com>
 * @version   0.1.1
 * @since     2020-03-01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportFieldOption {

    private Long id;

    private String value;

}
