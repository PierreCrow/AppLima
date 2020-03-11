package com.avances.lima.data.mapper;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsParameterValue;
import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.domain.model.Country;
import com.avances.lima.domain.model.PermanencyDay;

import java.util.ArrayList;
import java.util.List;

public class PermanencyDayDataMapper {


    public PermanencyDayDataMapper() {
    }


    public ArrayList<DbPermanencyDay> transformWsToDb(WsData wsData) {
        ArrayList<DbPermanencyDay> dbPermanencyDays = new ArrayList<>();
        for (WsParameterValue wsParameterValue : wsData.getWsPermanencyDay()) {
            DbPermanencyDay interest = new DbPermanencyDay(wsParameterValue.getId(),
                    wsParameterValue.getNameParameterValue(),
                    wsParameterValue.getDetailParameterValue(),
                    wsParameterValue.isActive());
            dbPermanencyDays.add(interest);
        }
        return dbPermanencyDays;
    }


/*
    public ArrayList<DistritNeighborhood> transformWsToEntity(WsData wsTotalSincronization) {
        ArrayList<DistritNeighborhood> places = new ArrayList<>();
        for (WsDistritNeighborhood wsPlace : wsTotalSincronization.getWsDistritNeighborhoods()) {
            DistritNeighborhood place = new DistritNeighborhood(wsPlace.getId(), wsPlace.getTittle(),
                    wsPlace.getResume(), wsPlace.getDetail(),wsPlace.getAddress(),wsPlace.getWebPage(),
                    wsPlace.getPhone(),wsPlace.getIdDistritoBarrio(),wsPlace.getLat(),wsPlace.getLng(),
                    wsPlace.isActive(),wsPlace.isDeleted(),wsPlace.getTestTagList());
            places.add(place);
        }
        return places;
    }
*/

    public ArrayList<PermanencyDay> transformDbToEntity(List<DbPermanencyDay> dbPermanencyDays) {
        ArrayList<PermanencyDay> permanencyDays = new ArrayList<>();
        for (DbPermanencyDay dbPermanencyDay : dbPermanencyDays) {
            PermanencyDay permanencyDay = new PermanencyDay(dbPermanencyDay.getIdCloud(),
                    dbPermanencyDay.getNameParameterValue(),
                    dbPermanencyDay.getDetailParameterValue(),
                    dbPermanencyDay.isActive());
            permanencyDays.add(permanencyDay);
        }
        return permanencyDays;
    }




}
