package com.groupe2.gestionscolaire.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.groupe2.gestionscolaire.dao.LogoDao;
import com.groupe2.gestionscolaire.dao.LogoDao;
import com.groupe2.gestionscolaire.model.Logo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/logos")
public class LogoController {

	@Autowired
	LogoDao logoDao;
	
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Logo>> findAll() {
		return new ResponseEntity<List<Logo>>(logoDao.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Logo> findOne(@PathVariable long id) {
		Optional<Logo> optionLogo = logoDao.findById(id);

		return optionLogo.isPresent() ? new ResponseEntity<Logo>(optionLogo.get(), HttpStatus.OK)
				: new ResponseEntity<Logo>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable long id) {
		this.logoDao.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/upload")
    public ResponseEntity.BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        log.info("Original Image Byte Size - " + file.getBytes().length);
        Logo img = new Logo(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        logoDao.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }
	
	

    @GetMapping(path = {"/get/{imageName}"})
    public Logo getImage(@PathVariable("imageName") String imageName) throws IOException {

        final Optional<Logo> retrievedImage = logoDao.findByName(imageName);
        Logo img = new Logo(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }
    
    

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}

