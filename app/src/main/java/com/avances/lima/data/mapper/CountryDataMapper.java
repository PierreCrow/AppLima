package com.avances.lima.data.mapper;

import com.avances.lima.data.datasource.cloud.model.synchronization.WsParameterValue;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.domain.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryDataMapper {


    public CountryDataMapper() {
    }


    public ArrayList<DbCountry> transformWsToDb(WsData wsData) {
        ArrayList<DbCountry> dbInterests = new ArrayList<>();
        for (WsParameterValue wsInterest : wsData.getWsCountries()) {
            DbCountry interest = new DbCountry(wsInterest.getId(),
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

    public ArrayList<Country> transformDbToEntity(List<DbCountry> dbCountries) {
        ArrayList<Country> interests = new ArrayList<>();
        for (DbCountry dbCountry : dbCountries) {
            Country country = new Country(dbCountry.getIdCloud(),
                    dbCountry.getNameParameterValue(),
                    dbCountry.getDetailParameterValue(),
                    dbCountry.isActive());
            interests.add(country);
        }
        return interests;
    }




}
