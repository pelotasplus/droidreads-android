package pl.pelotasplus.droidreads.api.model;

import org.simpleframework.xml.Element;

/**
 * Created by alek on 10/01/15.
 */
public class GroupResource {
    @Element(required = false)
    Topic topic;

    @Element(required = false)
    Challenge challenge;

    @Element(required = false)
    Friend friend;

    @Override
    public String toString() {
        return "GroupResource{" +
                "topic=" + topic +
                ", challenge=" + challenge +
                ", friend=" + friend +
                '}';
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Friend getFriend() {
        return friend;
    }

    public Topic getTopic() {
        return topic;
    }
}
