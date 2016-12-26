package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.RestaurantInfoDao;
import ua.goit.domain.RestaurantInfo;

public class RestaurantInfoService {
    private RestaurantInfoDao restaurantInfoDao;

    @Transactional
    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfoDao.getAllDetails();
    }

    public void setRestaurantInfoDao(RestaurantInfoDao restaurantInfoDao) {
        this.restaurantInfoDao = restaurantInfoDao;
    }
}
