package com.avances.lima.data.mapper;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsInterest;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.lima.data.datasource.db.model.DbInterest;
import com.avances.lima.domain.model.Interest;

import java.util.ArrayList;
import java.util.List;

public class InterestDataMapper {


    public InterestDataMapper() {
    }


    public ArrayList<DbInterest> transformWsToDb(WsData wsData) {
        ArrayList<DbInterest> dbInterests = new ArrayList<>();
        for (WsInterest wsInterest : wsData.getWsInterests()) {
            DbInterest interest = new DbInterest(wsInterest.getId(),
                    wsInterest.getNameParameterValue(),
                    wsInterest.getDetailParameterValue(),
                    wsInterest.isActive());
            dbInterests.add(interest);
        }
        return dbInterests;
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

    public ArrayList<Interest> transformDbToEntity(List<DbInterest> dbInterests) {
        ArrayList<Interest> interests = new ArrayList<>();
        for (DbInterest dbInterest : dbInterests) {
            Interest interest = new Interest(dbInterest.getIdCloud(),
                    dbInterest.getNameParameterValue(),
                    dbInterest.getDetailParameterValue(),
                    dbInterest.isActive());
            interests.add(interest);
        }
        return interests;
    }




}
