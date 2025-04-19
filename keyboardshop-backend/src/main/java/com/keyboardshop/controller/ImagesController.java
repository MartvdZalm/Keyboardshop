package com.keyboardshop.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.keyboardshop.services.ImagesService;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:4200")
public class ImagesController
{
	private final ImagesService imagesService;

	public ImagesController(ImagesService imagesService)
	{
		this.imagesService = imagesService;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image)
	{
		return this.imagesService.uploadImage(image);
	}

	@GetMapping("/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename)
	{
		return this.imagesService.getImage(filename);
	}
}
