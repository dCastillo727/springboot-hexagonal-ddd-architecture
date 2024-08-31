package com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DrivenPort;
import com.dcastillo.hexagonalarchitecture.common.utils.event.Event;
import com.dcastillo.hexagonalarchitecture.layers.application.event.authentication.UserLoggedInEvent;

@DrivenPort
public interface MicroservicesPublisher {
    void genericHandleEvent(Event event);

    void handleUserLoggedInEvent(UserLoggedInEvent event);
}
