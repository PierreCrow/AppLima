package com.avances.lima.data.mapper;


import com.avances.lima.data.datasource.cloud.model.synchronization.WsPlace;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.domain.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceDataMapper {


    public PlaceDataMapper() {
    }


    public ArrayList<DbPlace> transformWsToDb(WsData wsData) {
        ArrayList<DbPlace> places = new ArrayList<>();

        for (WsPlace wsPlace : wsData.getWsPlaces()) {
            List<String> interviewed = new ArrayList<>();
            interviewed.add(wsPlace.getWsInterviewed().getInterviewedTittle());
            interviewed.add(wsPlace.getWsInterviewed().getInterviewed());
            interviewed.add(wsPlace.getWsInterviewed().getInterviewedImage());
            interviewed.add(wsPlace.getWsInterviewed().getInterviewedAudio());
            DbPlace dbPlace = new DbPlace(wsPlace.getId(), wsPlace.getTittle(),
                    wsPlace.getResume(), wsPlace.getDetail(), wsPlace.getAddress(), wsPlace.getWebPage(),
                    wsPlace.getPhone(), wsPlace.getIdDistritoBarrio(), wsPlace.getLat(), wsPlace.getLng(),
                    wsPlace.isActive(), wsPlace.isDeleted(), wsPlace.getTextTags(), wsPlace.getTestTagList(),
                    interviewed, wsPlace.getImageList(),false);
            places.add(dbPlace);
        }
        return places;
    }




    public ArrayList<Place> transformDbToEntity(List<DbPlace> dbPlaces) {
        ArrayList<Place> places = new ArrayList<>();
        for (DbPlace wsPlace : dbPlaces) {
            Place place = new Place(wsPlace.getIdCloud(), wsPlace.getTittle(),
                    wsPlace.getResume(), wsPlace.getDetail(), wsPlace.getAddress(), wsPlace.getWebPage(),
                    wsPlace.getPhone(), wsPlace.getIdDistritNeighborhood(), wsPlace.getLat(), wsPlace.getLng(),
                    wsPlace.isActive(), wsPlace.isDeleted(), wsPlace.getTextTags(), wsPlace.getTextTagsList(),
                    wsPlace.getInterviewed(), wsPlace.getImageList(),wsPlace.isFavorite());
            places.add(place);
        }
        return places;
    }




    public DbPlace transformEntityToDb(Place place) {

        DbPlace dbPlace = new DbPlace(place.getId(), place.getTittle(),
                place.getResume(), place.getDetail(), place.getAddress(), place.getWebPage(),
                place.getPhone(), place.getIdDistritNeighborhood(), place.getLat(), place.getLng(),
                place.isActive(), place.isDeleted(), place.getTextTags(), place.getTextTagsList(),
                place.getInterviewed(), place.getImageList(),place.isFavorite());

        return dbPlace;
    }


}
