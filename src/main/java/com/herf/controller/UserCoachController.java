//package com.herf.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.herf.entity.User;
////import com.herf.feign.CoachFeignClient;
//import com.herf.service.UserService;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserCoachController {
//
//    @Autowired
//    private UserService userService;
//
////    @Autowired
////    private CoachFeignClient coachFeignClient;
//
//    @GetMapping("/getcoachinfo/{userId}")
//    public String getUserCoachInfo(@PathVariable Long userId) {
//        User user = userService.getUserDetails(userId);
//
//        if (user != null && user.getCoachId() != null) {
//            String coachName = coachFeignClient.getCoachNameById((user.getCoachId()));
//            // Assuming you have a getter for coachName in the User entity
//            return "Your coach is: " + coachName;
//        }
//
//        return "No coach assigned.";
//    }
//    @GetMapping("/getcoachidinfo/{userId}")
//    public Long getUserCoachidInfo(@PathVariable Long userId) {
//        User user = userService.getUserDetails(userId);
//
//       
//            return user.getCoachId();
//        
//    }
//
//
//  }
