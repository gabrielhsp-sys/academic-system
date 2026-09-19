package org.example.academic.system.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Usuario do sistema academico. A igualdade e definida pelo username
 * (TUS-2382). A senha nunca participa de equals, hashCode ou toString.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(of = {"username", "role"})
public class User implements Identifiable<String> {

    @EqualsAndHashCode.Include
    private String username;

    private String password;

    private Role role;

    @Override
    public String getId() {
        return username;
    }
}
