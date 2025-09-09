package br.com.github.gustavossobral.educational_api.domain.usuarios;

public enum UserRole {
    ADMIN("admin"),
    ESTUDANTE("estudante"),
    PROFESSOR("professor");

    private String role;

     UserRole(String role){
        this.role = role;
    }

    public String getRole(){
         return role;
    }
}
