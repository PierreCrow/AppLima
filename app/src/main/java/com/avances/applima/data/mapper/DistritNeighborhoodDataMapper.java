package com.avances.applima.data.mapper;

import com.avances.applima.data.datasource.cloud.model.synchronization.WsDistritNeighborhood;
import com.avances.applima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.domain.model.DistritNeighborhood;

import java.util.ArrayList;
import java.util.List;

public class DistritNeighborhoodDataMapper {


    public DistritNeighborhoodDataMapper() {
    }


    public ArrayList<DbDistritNeighborhood> transformWsToDb(WsData wsData) {
        ArrayList<DbDistritNeighborhood> dbDistritNeighborhoods = new ArrayList<>();
        for (WsDistritNeighborhood wsDistritNeighborhood : wsData.getWsDistritNeighborhoods()) {
            DbDistritNeighborhood distritNeighborhood = new DbDistritNeighborhood(wsDistritNeighborhood.getId(),
                    wsDistritNeighborhood.getIdDistritType(),
                    wsDistritNeighborhood.getDistrit(),
                    wsDistritNeighborhood.getShortDescription(),
                    wsDistritNeighborhood.getCompleteDescription(),
                    wsDistritNeighborhood.getImageList(),
                    wsDistritNeighborhood.isActive(),
                    wsDistritNeighborhood.getIdPlaceList(),
                    wsDistritNeighborhood.getLatitude(),
                    wsDistritNeighborhood.getLongitude(),
                    wsDistritNeighborhood.getUrlVideo(),
                    wsDistritNeighborhood.getTags(),
                    wsDistritNeighborhood.getTagList());
            dbDistritNeighborhoods.add(distritNeighborhood);
        }
        return dbDistritNeighborhoods;
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

    public ArrayList<DistritNeighborhood> transformDbToEntity(List<DbDistritNeighborhood> dbDistritNeighborhoods) {
        ArrayList<DistritNeighborhood> distritNeighborhoods = new ArrayList<>();
        for (DbDistritNeighborhood dbDistritNeighborhood : dbDistritNeighborhoods) {
            DistritNeighborhood distritNeighborhood = new DistritNeighborhood(dbDistritNeighborhood.getIdCloud(),
                    dbDistritNeighborhood.getIdDistritType(),
                    dbDistritNeighborhood.getDistrit(),
                    dbDistritNeighborhood.getShortDescription(),
                    dbDistritNeighborhood.getCompleteDescription(),
                    dbDistritNeighborhood.getImageList(),
                    dbDistritNeighborhood.isActive(),
                    dbDistritNeighborhood.getIdPlaceList(),
                    dbDistritNeighborhood.getLatitude(),
                    dbDistritNeighborhood.getLongitude(),
                    dbDistritNeighborhood.getUrlVideo(),
                    dbDistritNeighborhood.getTags(),
                    dbDistritNeighborhood.getTagList(),
                    false);
            distritNeighborhoods.add(distritNeighborhood);
        }
        return distritNeighborhoods;
    }


}
