package com.avances.applima.data.mapper;

import com.avances.applima.data.datasource.cloud.model.synchronization.WsRoute;
import com.avances.applima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.domain.model.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteDataMapper {


    public RouteDataMapper() {
    }


    public ArrayList<DbRoute> transformWsToDb(WsData wsData) {
        ArrayList<DbRoute> dbRoutes = new ArrayList<>();
        for (WsRoute wsRoute : wsData.getWsRoutes()) {
            DbRoute dbRoute = new DbRoute(wsRoute.getId(),
                    wsRoute.getIdRouteType(),
                    wsRoute.getRouteName(),
                    wsRoute.getIdUserRegister(),
                    wsRoute.getIdUserModify(),
                    wsRoute.getRegisterDate(),
                    wsRoute.getModifyDate(),
                    wsRoute.isDeleted(),
                    wsRoute.getImage(),
                    wsRoute.getIconImage(),
                    wsRoute.getIdPlaceList(),
                    wsRoute.getTags(),
                    wsRoute.getTagList());
            dbRoutes.add(dbRoute);
        }
        return dbRoutes;
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

    public ArrayList<Route> transformDbToEntity(List<DbRoute> dbRoutes) {
        ArrayList<Route> routes = new ArrayList<>();
        for (DbRoute dbRoute : dbRoutes) {
            Route route = new Route(dbRoute.getIdCloud(),
                    dbRoute.getIdRouteType(),
                    dbRoute.getRouteName(),
                    dbRoute.getIdUserRegister(),
                    dbRoute.getIdUserModify(),
                    dbRoute.getRegisterDate(),
                    dbRoute.getModifyDate(),
                    dbRoute.isDeleted(),
                    dbRoute.getImage(),
                    dbRoute.getIconImage(),
                    dbRoute.getIdPlaceList(),
                    dbRoute.getTags(),
                    dbRoute.getTagList());
            routes.add(route);
        }
        return routes;
    }




}
