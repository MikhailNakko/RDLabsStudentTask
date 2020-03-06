package enums;

import lombok.Getter;

@Getter
public enum PersonalDetailsItems {
    MALE("Male"),
    FEMALE("Female");


    public String var;

    PersonalDetailsItems(String var) {
        this.var = var;
    }

    public static PersonalDetailsItems getPersonalDetailsItems(String containerName) {
        for (PersonalDetailsItems container : PersonalDetailsItems.values()) {
            if (container.getVar().equalsIgnoreCase(containerName)) {
                return container;
            }
        }
        throw new IllegalStateException("Wrong parameter passed");
    }
}
