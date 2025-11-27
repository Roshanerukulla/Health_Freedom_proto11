package com.herf.controller;

import com.herf.entity.Avatar;
import com.herf.entity.AvatarDTO;
import com.herf.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
	@Autowired
    private final AvatarService avatarService;

    
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public ResponseEntity<Long> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title,
            @RequestParam("description") String description) throws IOException {
        Avatar avatar = new Avatar();
        avatar.setName(title);
        avatar.setType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setUId(userId);
        avatar.setDescription(description);

        Avatar savedAvatar = avatarService.saveAvatar(avatar);
        return ResponseEntity.ok(savedAvatar.getId());
    }

    @GetMapping(value = "/{userId}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getAvatarImage(@PathVariable Long userId) {
        Avatar avatar = avatarService.getAvatarByUserId(userId);

        if (avatar != null && avatar.getData() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(avatar.getData().length);

            return new ResponseEntity<>(avatar.getData(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-images")
    public ResponseEntity<List<AvatarDTO>> getAllAvatarImages() {
        List<AvatarDTO> avatarDTOs = avatarService.getAllAvatarDTOs();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(avatarDTOs);
    }
}
