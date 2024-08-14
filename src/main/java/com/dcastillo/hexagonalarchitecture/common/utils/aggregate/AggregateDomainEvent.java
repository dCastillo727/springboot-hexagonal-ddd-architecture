package com.dcastillo.hexagonalarchitecture.common.utils.aggregate;

import com.dcastillo.hexagonalarchitecture.common.utils.event.DomainEvent;

import java.util.List;

public interface AggregateDomainEvent {
    void clearEvents();

    List<DomainEvent> listEvents();
}
