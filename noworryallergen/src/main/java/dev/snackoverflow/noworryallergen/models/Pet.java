package dev.snackoverflow.noworryallergen.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Pet {
    @Id
    private String petId;
    private String ownerId;
    private String name;
    private String type;
    private List<Log> foodLogs;
    private List<Log> allergyLogs;
    private Map<String, Integer> allergenTracker;

    public Pet(String ownerId, String name, String type) {

    }

    public void addFoodLog(Food food) {

    }

    public void removeFoodLog(String logId) {

    }

    public void addAllergyLog(Allergy allergy) {

    }

    public void removeAllergyLog(String logId) {

    }

    public void identifyCommonAllergens() {
        allergenTracker.forEach((component, severity) -> {
            if (severity > 5) {
                System.out.println("Component: " + component + ", Total Severity: " + severity);
            }
        });
    }


}
