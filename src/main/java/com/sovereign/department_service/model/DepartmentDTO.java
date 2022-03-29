package com.sovereign.department_service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartmentDTO {

    private Long departmentId;

    @NotNull
    @Size(max = 255)
    private String departmentName;

    @NotNull
    @Size(max = 255)
    private String departmentAddress;

    @NotNull
    @Size(max = 255)
    private String departmentCode;

}
