package net.developz.classroom.backend.catalog.resource.application.dto;

public record UpdateResourceRequest(
    String title,
    String contentUrl
) {}
