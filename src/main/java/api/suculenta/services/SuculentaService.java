package api.suculenta.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.suculenta.models.Suculenta;
import api.suculenta.repositories.SuculentaRepository;

@Service
public class SuculentaService {

	@Autowired
	SuculentaRepository sr;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/assets/uploads";
	
	public Suculenta registrarSuculenta(Suculenta suculenta, MultipartFile[] files) throws IOException {
		
		int cont = 1;
		for (MultipartFile file : files) {
			String nomeImg = Long.toString(suculenta.getId());
			StringBuilder fileNames = new StringBuilder(nomeImg + "-" + cont);
			Path fileNameAndPath = Paths.get(uploadDirectory, fileNames.toString());
			cont++;
			
			Files.write(fileNameAndPath, file.getBytes());
				
			File input = new File(uploadDirectory, fileNames.toString());
		    File output = new File(uploadDirectory, fileNames.toString()+".png");

		    BufferedImage image = ImageIO.read(input);
		    BufferedImage result = new BufferedImage(
		            image.getWidth(),
		            image.getHeight(),
		            BufferedImage.TYPE_INT_ARGB);
		    result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
		    ImageIO.write(result, "png", output);
				
		    input.delete();
		}
		return suculenta;
	}
	
	public Suculenta atualizarSuculenta(Suculenta suculenta) {
		return sr.save(suculenta);
	}
	
	public void deletarSuculenta(Long id) {
		Suculenta suculenta = sr.getById(id);
		sr.delete(suculenta);
	}
}
