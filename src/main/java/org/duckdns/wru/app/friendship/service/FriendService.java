package org.duckdns.wru.app.friendship.service;

import org.duckdns.wru.app.friendship.domain.Friendship;
import org.duckdns.wru.app.friendship.exception.AlreadyFriendsException;
import org.duckdns.wru.app.friendship.repository.FriendRepository;
import org.duckdns.wru.app.user.domain.User;
import org.duckdns.wru.app.user.exception.UserNotFoundException;
import org.duckdns.wru.app.user.repository.UserRepository;
import org.duckdns.wru.global.auth.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendService(FriendRepository friendRepository,UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public Friendship createFriendship(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long id = userDetails.getUserId();
        System.out.println("id: " + id);
        Friendship friendship = new Friendship();
        Optional<User> userOptional = userRepository.findById(id);
        User friend = userRepository.findByUsername(username);
        if(friend == null) {
            throw new UserNotFoundException(username);
        }
        System.out.println("username" + username);
        Long friendId = friend.getId();

        if(userOptional.isPresent()) {
            User me = userOptional.get();

            boolean exists = friendRepository.existsByUserAndFriend(me, friend);

            if (exists) {
                throw new AlreadyFriendsException("이미 친구 관계입니다.");
            }
            friendship.setUser(me);
            friendship.setFriend(friend);
            friendship.setCreatedAt(LocalDateTime.now());

            friendRepository.save(friendship);
            System.out.println("friendship = " + friendship);
            return friendship;
        } else {
            throw new UserNotFoundException(id);
        }


    }
}
