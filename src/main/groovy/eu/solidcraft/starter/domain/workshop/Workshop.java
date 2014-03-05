package eu.solidcraft.starter.domain.workshop;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class Workshop {
    String name;
    String teacher;
    String teachersEmail;
    String room;
    int limit;
    WorkshopSession workshopSession;

    Set<User> students = newHashSet();

    private Long version;
}
