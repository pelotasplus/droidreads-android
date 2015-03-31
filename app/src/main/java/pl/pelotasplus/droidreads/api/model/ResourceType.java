package pl.pelotasplus.droidreads.api.model;

import android.text.TextUtils;

public enum ResourceType {
    FRIEND("Friend"),
    CHALLENGE("Challenge"),
    COMMENT("Comment"),
    UNKNOWN("unknown");

    String resource_type;

    ResourceType(String resource_type) {
        this.resource_type = resource_type;
    }

    public static ResourceType fromString(String value) {
        if (TextUtils.isEmpty(value))
            return UNKNOWN;

        for (ResourceType resourceType : ResourceType.values()) {
            if (value.equalsIgnoreCase(resourceType.name())) {
                return resourceType;
            }
        }

        return UNKNOWN;
    }
}
