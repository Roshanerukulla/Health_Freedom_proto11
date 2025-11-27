package com.herf.service;

import com.herf.entity.Avatar;
import com.herf.entity.AvatarDTO;
import com.herf.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvatarService {
	 @Autowired
    private final AvatarRepository avatarRepository;

   
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar getAvatar(Long avatarId) {
        return avatarRepository.findById(avatarId)
                .orElseThrow(() -> new EntityNotFoundException("Avatar not found"));
    }

    public Avatar saveAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public List<AvatarDTO> getAllAvatarDTOs() {
        List<Avatar> avatars = avatarRepository.findAll();
        return avatars.stream()
                .map(avatar -> new AvatarDTO(avatar.getId(), avatar.getName(), avatar.getType(), avatar.getData()))
                .collect(Collectors.toList());
    }

    public Avatar getAvatarByUserId(Long id) {
        return avatarRepository.findByUid(id);
    }

    private String encodeImageAsBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }
}
