package com.avances.applima.data.mapper;


import com.avances.applima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.applima.data.datasource.cloud.model.synchronization.WsEvent;
import com.avances.applima.data.datasource.db.model.DbEvent;
import com.avances.applima.domain.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDataMapper {


    public EventDataMapper() {
    }


    public ArrayList<DbEvent> transformWsToDb(WsData wsData) {
        ArrayList<DbEvent> dbEvents = new ArrayList<>();
        for (WsEvent wsEvent : wsData.getWsEvents()) {
            DbEvent dbEvent = new DbEvent(wsEvent.getId(), wsEvent.getTittle(),
                    wsEvent.getShortDecription(), wsEvent.getDescription(), wsEvent.getEventDate(),
                    wsEvent.getEventTime(), wsEvent.getIdEventType(), wsEvent.isActive(), wsEvent.isDeleted(),
                    wsEvent.getImage(),wsEvent.getStartDate(),wsEvent.getFinalDate());
            dbEvents.add(dbEvent);
        }
        return dbEvents;
    }


    public ArrayList<Event> transformDbToEntity(List<DbEvent> dbEvents) {
        ArrayList<Event> events = new ArrayList<>();
        for (DbEvent dbEvent : dbEvents) {
            Event place = new Event(dbEvent.getIdCloud(), dbEvent.getTittle(),
                    dbEvent.getShortDecription(), dbEvent.getDescription(), dbEvent.getEventDate(),
                    dbEvent.getEventTime(), dbEvent.getIdEventType(), dbEvent.isActive(), dbEvent.isDeleted(),
                    dbEvent.getImage(),dbEvent.getStartDate(),dbEvent.getFinalDate());
            events.add(place);
        }
        return events;
    }


}
