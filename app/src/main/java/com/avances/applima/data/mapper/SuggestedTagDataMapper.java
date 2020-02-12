package com.avances.applima.data.mapper;

import com.avances.applima.data.datasource.cloud.model.synchronization.WsCountry;
import com.avances.applima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.SuggestedTag;

import java.util.ArrayList;
import java.util.List;

public class SuggestedTagDataMapper {


    public SuggestedTagDataMapper() {
    }


    public ArrayList<DbSuggestedTag> transformWsToDb(WsData wsData) {
        ArrayList<DbSuggestedTag> dbInterests = new ArrayList<>();
        for (String wsInterest : wsData.getSuggestedTags()) {
            DbSuggestedTag interest = new DbSuggestedTag(wsInterest);
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

    public ArrayList<SuggestedTag> transformDbToEntity(List<DbSuggestedTag> dbSuggestedTags) {
        ArrayList<SuggestedTag> suggestedTags = new ArrayList<>();
        for (DbSuggestedTag dbCountry : dbSuggestedTags) {
            SuggestedTag suggestedTag = new SuggestedTag(dbCountry.getName());
            suggestedTags.add(suggestedTag);
        }
        return suggestedTags;
    }




}
