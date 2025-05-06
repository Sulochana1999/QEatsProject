
package com.crio.qeats.controller;

import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.services.RestaurantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;



@RestController

@Log4j2

@RequestMapping(RestaurantController.RESTAURANT_API_ENDPOINT)
public class RestaurantController {
  public static final String RESTAURANT_API_ENDPOINT = "/qeats/v1";
  public static final String RESTAURANTS_API = "/restaurants";
  public static final String MENU_API = "/menu";
  public static final String CART_API = "/cart";
  public static final String CART_ITEM_API = "/cart/item";
  public static final String CART_CLEAR_API = "/cart/clear";
  public static final String POST_ORDER_API = "/order";
  public static final String GET_ORDERS_API = "/orders";
  @Autowired
  private RestaurantService restaurantService;

  @GetMapping(RESTAURANTS_API)

  public ResponseEntity<GetRestaurantsResponse> getRestaurants(@RequestParam(name="latitude") Double lattitude, @RequestParam(name="longitude") Double longitude,

                                                               GetRestaurantsRequest getRestaurantsRequest) {

    log.info("getRestaurants called with {}", getRestaurantsRequest);
    /// try{
    // if(lattitude < 0 || lattitude >90 || longitude < 0 || longitude > 90)
    // {
    //   return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    // }

    GetRestaurantsResponse getRestaurantsResponse;
    if(getRestaurantsRequest.getLattitude()!= null && getRestaurantsRequest.getLongitude()!=null &&
            getRestaurantsRequest.getLattitude()>=-90 && getRestaurantsRequest.getLattitude()<=90 &&
            getRestaurantsRequest.getLongitude()>=-180 && getRestaurantsRequest.getLongitude()<=180)
    {
      //   //CHECKSTYLE:OFF
      getRestaurantsResponse = restaurantService
              .findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());
      log.info("getRestaurants returned {}", getRestaurantsResponse);
      return ResponseEntity.ok().body(getRestaurantsResponse);

    }
    else{
      return ResponseEntity.badRequest().body(null);
    }
    // if(getRestaurantsResponse == null )
    //      {
    //         return ResponseEntity.badRequest().body(null);

    //       }


    //CHECKSTYLE:ON
//       if(getRestaurantsRequest.getId() != null)
//       {
//         boolean isValidRestarnant=restaurantService.validateRestarnantId(getRestaurantsRequest.getId());
//  if(!isValidRestarnant)
//  {
//   return ResponseEntity.badRequest().body(null);

//  }
//  return ResponseEntity.ok(getRestaurantsResponse);
// }
//     }catch(Exception e)
// {
//   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
// }
    // return ResponseEntity.ok().body(getRestaurantsResponse);

    //return null;
  }
}

// TIP(MODULE_MENUAPI): Model Implementation for getting menu given a restaurantId.
// Get the Menu for the given restaurantId
// API URI: /qeats/v1/menu?restaurantId=11
// Method: GET
// Query Params: restaurantId
// Success Output:
// 1). If restaurantId is present return Menu
// 2). Otherwise respond with BadHttpRequest.
//

// @GetMapping(MENU_API)

// public ResponseEntity<GetRestaurantsResponse> getMEntity(@RequestParam ("restaurantId") String restaurantId, GetRestaurantsRequest getRestaurantsRequest)
// {

// GetRestaurantsResponse getRestaurantsResponse;
// getRestaurantsResponse = restaurantService
// .findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());

//      if(restaurantId ==null || restaurantId.isEmpty())
//      {
//         return ResponseEntity.ok().build();
//      }

//      if(getRestaurantsResponse == null )
//      {

//         return ResponseEntity.badRequest().body(null);

//      }
//     return ResponseEntity.ok().body(getRestaurantsResponse);


//     }

// HTTP Code: 200
// {
//  "menu": {
//    "items": [
//      {
//        "attributes": [
//          "South Indian"
//        ],
//        "id": "1",
//        "imageUrl": "www.google.com",
//        "itemId": "10",
//        "name": "Idly",
//        "price": 45
//      }
//    ],
//    "restaurantId": "11"
//  }
// }
// Error Response:
// HTTP Code: 4xx, if client side error.
//          : 5xx, if server side error.
// Eg:
// curl -X GET "http://localhost:8081/qeats/v1/menu?restaurantId=11"


