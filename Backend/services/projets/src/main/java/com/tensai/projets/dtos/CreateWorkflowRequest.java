package com.tensai.projets.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateWorkflowRequest<message>(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be less than 100 characters")
        String name,

        @Size(max = 500, message = "Description must be less than 500 characters")
        String description,

        @NotNull(message = "Project ID is required")
        @Positive(message = "Project ID must be positive")
        Long projectId,

        @NotBlank(message = "status is required")
        String status


) {

}