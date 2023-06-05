package com.pms.controller;

import com.pms.entity.User;
import com.pms.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/image-upload")
public class ImageController {

  @Autowired
  private ImageService imageService;

  @PostMapping(path="")
  public ResponseEntity<?> uploadImage(@RequestBody(required = false) MultipartFile image, @AuthenticationPrincipal User user) throws IOException {
    System.out.println(user.getId());
    System.out.println(image);
    try{
      imageService.uploadImage(image, user);
      return ResponseEntity.ok("Image uploaded successfully");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error uploading Image" + e.getMessage());
    }

  }
}
