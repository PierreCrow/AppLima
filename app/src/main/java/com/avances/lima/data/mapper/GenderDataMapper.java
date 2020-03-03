package com.avances.lima.data.mapper;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsGender;
import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.domain.model.Gender;

import java.util.ArrayList;
import java.util.List;

public class GenderDataMapper {


    public GenderDataMapper() {
    }


    public ArrayList<DbGender> transformWsToDb(WsData wsData) {
        ArrayList<DbGender> dbGenders = new ArrayList<>();
        for (WsGender wsGender : wsData.getWsGenders()) {
            DbGender dbGender = new DbGender(wsGender.getId(),
                    wsGender.getNameParameterValue(),
                    wsGender.getDetailParameterValue(),
                    wsGender.isActive());
            dbGenders.add(dbGender);
        }
        return dbGenders;
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

    public ArrayList<Gender> transformDbToEntity(List<DbGender> dbGenders) {
        ArrayList<Gender> genders = new ArrayList<>();
        for (DbGender dbGender : dbGenders) {
            Gender gender = new Gender(dbGender.getIdCloud(),
                    dbGender.getNameParameterValue(),
                    dbGender.getDetailParameterValue(),
                    dbGender.isActive());
            genders.add(gender);
        }
        return genders;
    }




}
