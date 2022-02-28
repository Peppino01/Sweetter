package com.azienda.sweetter.model.entity.dto;

import java.sql.Timestamp;

public class PeriodDTO extends LoginDTO {
    private Timestamp start, end;

    public PeriodDTO() {}

    public PeriodDTO(String username, String password, Timestamp start, Timestamp end) {
        super(username, password);
        this.start = start;
        this.end = end;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
