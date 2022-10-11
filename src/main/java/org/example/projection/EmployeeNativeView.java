package org.example.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeNativeView {

    @Value("#{target.id + ' ' + target.firstName}")
    Integer getId();

    String getFullName();
}
