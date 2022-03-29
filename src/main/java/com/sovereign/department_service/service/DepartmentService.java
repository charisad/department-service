package com.sovereign.department_service.service;

import com.sovereign.department_service.domain.Department;
import com.sovereign.department_service.model.DepartmentDTO;
import com.sovereign.department_service.repos.DepartmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> mapToDTO(department, new DepartmentDTO()))
                .collect(Collectors.toList());
    }

    public DepartmentDTO get(final Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(department -> mapToDTO(department, new DepartmentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final DepartmentDTO departmentDTO) {
        final Department department = new Department();
        mapToEntity(departmentDTO, department);
        return departmentRepository.save(department).getDepartmentId();
    }

    public void update(final Long departmentId, final DepartmentDTO departmentDTO) {
        final Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(departmentDTO, department);
        departmentRepository.save(department);
    }

    public void delete(final Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    private DepartmentDTO mapToDTO(final Department department, final DepartmentDTO departmentDTO) {
        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        departmentDTO.setDepartmentAddress(department.getDepartmentAddress());
        departmentDTO.setDepartmentCode(department.getDepartmentCode());
        return departmentDTO;
    }

    private Department mapToEntity(final DepartmentDTO departmentDTO, final Department department) {
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentAddress(departmentDTO.getDepartmentAddress());
        department.setDepartmentCode(departmentDTO.getDepartmentCode());
        return department;
    }

}
