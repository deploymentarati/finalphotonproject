package net.codejava;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {
    private Long id;
    
    private String name;
    
    private String project;
    
    private String boundedContext;
    
    private String namespace;
    
    private String status;
    
    private List<String> tags;
    
    private LocalDateTime created_at;
    
    private LocalDateTime updated_at;

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBoundedContext() {
        return boundedContext;
    }

    public void setBoundedContext(String boundedContext) {
        this.boundedContext = boundedContext;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
