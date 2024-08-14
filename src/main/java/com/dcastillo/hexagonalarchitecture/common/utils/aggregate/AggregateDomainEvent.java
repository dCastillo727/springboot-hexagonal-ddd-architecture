package com.dcastillo.hexagonalarchitecture.common.utils.aggregate;

import com.dcastillo.hexagonalarchitecture.common.utils.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public interface AggregateDomainEvent {
    List<DomainEvent> events = new ArrayList<>();

    void clearEvents();

    List<DomainEvent> listEvents();
}
