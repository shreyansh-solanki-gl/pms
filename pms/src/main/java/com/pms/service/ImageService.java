package com.pms.service;

import com.pms.entity.Image;
import com.pms.entity.User;
import com.pms.repository.ImageRepository;
import com.pms.repository.UserRepository;
import com.pms.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private UserRepository userRepository;

  public void uploadImage(MultipartFile image, User user) throws Exception {
      // Create ImageEntity and associate with the user
      Image imageEntity = new Image();
      imageEntity.setName(image.getOriginalFilename());
      imageEntity.setImage(ImageUtil.compressImage(image.getBytes()));
      imageEntity.setUser(user);

      user.setImage(imageEntity);
      userRepository.save(user);
      imageRepository.save(imageEntity);
  }
}
