package org.duckdns.wru.app.userLocation.service;

import org.duckdns.wru.app.user.domain.User;
import org.duckdns.wru.app.user.exception.UserNotFoundException;
import org.duckdns.wru.app.user.repository.UserRepository;
import org.duckdns.wru.app.userLocation.domain.UserLocation;
import org.duckdns.wru.app.userLocation.dto.FriendsLocationRes;
import org.duckdns.wru.app.userLocation.dto.UserLocationRequest;
import org.duckdns.wru.app.userLocation.dto.UserLocationResponse;
import org.duckdns.wru.app.userLocation.repository.UserLocationRepository;
import org.duckdns.wru.global.auth.dto.CustomUserDetails;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserLocationService {

    private final UserRepository userRepository;
    private final UserLocationRepository userLocationRepository;

    public UserLocationService(UserRepository userRepository, UserLocationRepository userLocationRepository) {
        this.userRepository = userRepository;
        this.userLocationRepository = userLocationRepository;
    }

    @Transactional
    public ApiResponse<?> updateUserLocation (UserLocationRequest userLocationRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = userDetails.getUserId();

        Optional<User> userOptional = userRepository.findById(userId);
        User user = null;
        if(userOptional.isPresent()) {
            user = userOptional.get();
        }else{
            return ApiResponse.error(new UserNotFoundException(userId));
        }
        UserLocation userLocation = new UserLocation();
        userLocation.setLatitude(userLocationRequest.getLatitude());
        userLocation.setLongitude(userLocationRequest.getLongitude());
        userLocation.setTimestamp(LocalDateTime.now());
        user.addLocation(userLocation);
        userLocationRepository.save(userLocation);

        UserLocationResponse response = new UserLocationResponse();

        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setLatitude(userLocation.getLatitude());
        response.setLongitude(userLocation.getLongitude());
        response.setTimestamp(userLocation.getTimestamp());

        return ApiResponse.success(response);
    }

    public ApiResponse<?> getFriendsLocation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = userDetails.getUserId();

        UserLocationResponse userLocation = userLocationRepository.findLatestUserLocation(userId);
        List<UserLocationResponse> friendsLocations = userLocationRepository.findLatestFriendsLocations(userId);
        System.out.println(friendsLocations);

        FriendsLocationRes response = new FriendsLocationRes();
        if (userLocation != null) {
            response.setId(userLocation.getUserId());
            response.setUsername(userLocation.getUsername());
            response.setLatitude(userLocation.getLatitude());
            response.setLongitude(userLocation.getLongitude());
        }

        response.setFriendsLocations(friendsLocations);

        return ApiResponse.success(response);
    }
}
