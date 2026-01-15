package com.rs.Stream;

import java.util.List;

public class ModelToDto {

    List<PersonModel> p = List.of(
            new PersonModel("Rohit", "Shelar", "IT", 50000),
            new PersonModel("John", "Doe", "HR", 60000),
            new PersonModel("Jane", "Smith", "IT", 55000)
    );

    public List<ResponseDTO> convertModelToDto(List<PersonModel> personModels) {
        return personModels.stream()
                .collect(java.util.stream.Collectors.groupingBy(PersonModel::department))
                .entrySet().stream()
                .map(entry -> new ResponseDTO(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(pm -> new PersonDTO(pm.firstName() + " " + pm.lastName(), pm.salary()))
                                .toList()
                ))
                .toList();
    }

    public static void main(String[] args) {
        ModelToDto modelToDto = new ModelToDto();
        List<ResponseDTO> response = modelToDto.convertModelToDto(modelToDto.p);
        response.forEach(r -> {
            System.out.println("Department: " + r.department());
            r.personeList().forEach(pd ->
                System.out.println("  Name: " + pd.fullName() + ", Salary: " + pd.salary())
            );
        });
    }
}

record PersonDTO(String fullName, int salary) {}

record PersonModel(String firstName, String lastName, String department, int salary) {}

record ResponseDTO(String department, List<PersonDTO> personeList) {}