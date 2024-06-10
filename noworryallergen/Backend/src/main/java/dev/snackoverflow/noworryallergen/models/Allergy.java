package dev.snackoverflow.noworryallergen.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Allergy {
    @Id
    private String allergyId;
    private String allergySymptom;
    private int allergySeverity;
    private String allergyDescription;

    public Allergy(String allergySymptom, int allergySeverity, String allergyDescription) {
        this.allergySymptom = allergySymptom;
        this.allergySeverity = allergySeverity;
        this.allergyDescription = allergyDescription;
    }

}
