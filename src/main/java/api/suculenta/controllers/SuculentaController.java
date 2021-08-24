package api.suculenta.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import api.suculenta.models.Suculenta;
import api.suculenta.repositories.SuculentaRepository;
import api.suculenta.services.SuculentaService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("suculenta/")
public class SuculentaController {

	@Autowired
	SuculentaService suculentaService;
	
	@Autowired
	SuculentaRepository sr;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/assets/uploads/";
	
	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Suculenta registrar(@RequestPart("photos") MultipartFile[] photos, @RequestPart("suculenta") String suculenta) throws IOException {
		
		Suculenta suculentaClass = new Gson().fromJson(suculenta, Suculenta.class);
		
		Suculenta sucuService = sr.save(suculentaClass);
		
		return suculentaService.registrarSuculenta(sucuService, photos);
	}
	
	@GetMapping("/dashboard")
	public List<Suculenta> listarSuculentas() {
		return sr.findAll();
	}
	
	@GetMapping("/list/{id}")
	public Suculenta listarSuculenta(@PathVariable Long id) {
		 return sr.getById(id);
	}
	
	@PutMapping("/update/{id}") 
	public Suculenta atualizarSuculenta(@RequestBody Suculenta suculenta) throws IOException {
		return suculentaService.atualizarSuculenta(suculenta);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletarSuculenta(@PathVariable Long id) {
		suculentaService.deletarSuculenta(id);
	}
	
	@GetMapping("/search/{name}")
	public List<Suculenta> procurarSuculenta(@PathVariable String name) {
		return sr.findByNameContainingIgnoreCase(name);
	}
}
