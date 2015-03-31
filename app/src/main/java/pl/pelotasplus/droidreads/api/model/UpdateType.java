package pl.pelotasplus.droidreads.api.model;

import android.text.TextUtils;

public enum UpdateType {
    FRIEND("friend"),
    REVIEW("review"),
    READ_STATUS("readstatus"),
    RECOMMENDATION("recommendation"),
    USER_STATUS("userstatus"),
    UNKNOWN("unknown");

    String resource_type;

    UpdateType(String resource_type) {
        this.resource_type = resource_type;
    }

    public static UpdateType fromString(String value) {
        if (TextUtils.isEmpty(value))
            return UNKNOWN;

        for (UpdateType resourceType : UpdateType.values()) {
            if (value.equalsIgnoreCase(resourceType.resource_type)) {
                return resourceType;
            }
        }

        return UNKNOWN;
    }
}
