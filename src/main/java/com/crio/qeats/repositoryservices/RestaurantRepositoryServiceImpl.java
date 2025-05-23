package com.crio.qeats.repositoryservices;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.models.RestaurantEntity;
import com.crio.qeats.repositories.RestaurantRepository;
import com.crio.qeats.utils.GeoUtils;
import jakarta.inject.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantRepositoryServiceImpl implements RestaurantRepositoryService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private Provider<ModelMapper> modelMapperProvider;
    @Autowired
    private RestaurantRepository restaurantRepository;
    // private MongoRepository<RestaurantEntity, String> restaurantRepository;

    private boolean isOpenNow(LocalTime time, RestaurantEntity res) {
        LocalTime openingTime = LocalTime.parse(res.getOpensAt());
        LocalTime closingTime = LocalTime.parse(res.getClosesAt());

        return time.isAfter(openingTime) && time.isBefore(closingTime);
    }

    // TODO: CRIO_TASK_MODULE_NOSQL
    // Objectives:
    // 1. Implement findAllRestaurantsCloseby.
    // 2. Remember to keep the precision of GeoHash in mind while using it as a key.
    // Check RestaurantRepositoryService.java file for the interface contract.

    public List<Restaurant> findAllRestaurantsCloseBy(Double latitude,
                                                      Double longitude, LocalTime currentTime, Double servingRadiusInKms) {

        ModelMapper mp=modelMapperProvider.get();

        //List<RestaurantEntity> restaurants =mongoTemplate.findAll(RestaurantEntity.class);
        List<RestaurantEntity> restaurants =restaurantRepository.findAll();
        List<Restaurant> restaurants2=new ArrayList<>();

        for(RestaurantEntity rs:restaurants)
        {
            if(isOpenNow(currentTime, rs))
            {
                if(isRestaurantCloseByAndOpen(rs, currentTime, latitude, longitude, servingRadiusInKms))
                {
                    restaurants2.add(mp.map(rs,Restaurant.class));
                }
            }
        }

        //CHECKSTYLE:OFF
        //CHECKSTYLE:ON


        return restaurants2;
    }








    // TODO: CRIO_TASK_MODULE_NOSQL
    // Objective:
    // 1. Check if a restaurant is nearby and open. If so, it is a candidate to be returned.
    // NOTE: How far exactly is "nearby"?

    /**
     * Utility method to check if a restaurant is within the serving radius at a given time.
     * @return boolean True if restaurant falls within serving radius and is open, false otherwise
     */
    private boolean isRestaurantCloseByAndOpen(RestaurantEntity restaurantEntity,
                                               LocalTime currentTime, Double latitude, Double longitude, Double servingRadiusInKms) {
        if (isOpenNow(currentTime, restaurantEntity)) {
            return GeoUtils.findDistanceInKm(latitude, longitude,

                    restaurantEntity.getLatitude(), restaurantEntity.getLongitude())
                    < servingRadiusInKms;
        }

        return false;
    }



}

