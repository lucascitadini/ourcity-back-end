package com.citadini.ourcity.resources;

import com.citadini.ourcity.domain.ApoioOcorrencia;
import com.citadini.ourcity.domain.ComentarioOcorrencia;
import com.citadini.ourcity.domain.Ocorrencia;
import com.citadini.ourcity.dto.ComentarioOcorrenciaDTO;
import com.citadini.ourcity.dto.OcorrenciaDTO;
import com.citadini.ourcity.dto.OcorrenciaNewDTO;
import com.citadini.ourcity.resources.utils.URL;
import com.citadini.ourcity.service.ApoioOcorrenciaService;
import com.citadini.ourcity.service.ComentarioOcorrenciaService;
import com.citadini.ourcity.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaResource {
	
	@Autowired
	private OcorrenciaService service;
	
	@Autowired
	private ComentarioOcorrenciaService comentarioService;
	
	@Autowired
	private ApoioOcorrenciaService apoioService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Ocorrencia> find(@PathVariable Long id) {
		Ocorrencia obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Ocorrencia>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Ocorrencia> list = service.search(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<Ocorrencia>> findPage(
			@RequestParam(value="endereco", defaultValue="") String endereco,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCriacao") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		String enderecoDecoded = "";
		if (endereco != null && !endereco.equals("")) {
			enderecoDecoded = URL.decodeParam(endereco);
		}
		Page<Ocorrencia> list = service.search(enderecoDecoded, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(method=RequestMethod.GET, value= "/comentarios/page")
	public ResponseEntity<Page<ComentarioOcorrencia>> findComentariosPage(
			@RequestParam(value="idOcorrencia", defaultValue="0") Long idOcorrencia,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="instante") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<ComentarioOcorrencia> list = comentarioService.search(page, linesPerPage, orderBy, direction, idOcorrencia);
		return ResponseEntity.ok(list);
	}

	@RequestMapping(value="/apoios/{idOcorrencia}", method=RequestMethod.GET)
	public ResponseEntity<List<ApoioOcorrencia>> findApoios(@PathVariable Long idOcorrencia) {
		List<ApoioOcorrencia> obj = apoioService.findByIdOcorrencia(idOcorrencia);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(value="/comentarios/{id}", method=RequestMethod.GET)
	public ResponseEntity<ComentarioOcorrencia> findComentario(@PathVariable Long id) {
		ComentarioOcorrencia obj = comentarioService.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OcorrenciaNewDTO objDto) {
		Ocorrencia obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/apoios/{idOcorrencia}", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@PathVariable Long idOcorrencia) {
		ApoioOcorrencia obj = apoioService.fromIdOcorrencia(idOcorrencia);
		obj = apoioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/comentarios", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ComentarioOcorrenciaDTO objDto) {
		ComentarioOcorrencia obj = comentarioService.fromDto(objDto);
		obj = comentarioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT) 
	public ResponseEntity<Void> update(@Valid @RequestBody OcorrenciaDTO objDto) {
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Ocorrencia> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/apoios/{idOcorrencia}", method=RequestMethod.DELETE)
	public ResponseEntity<ApoioOcorrencia> deleteApoio(@PathVariable Long idOcorrencia) {
		apoioService.delete(idOcorrencia);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/comentarios/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ComentarioOcorrencia> deleteComentario(@PathVariable Long id) {
		comentarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/picture/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadPicture(
			@PathVariable Long id,
			@RequestParam(name="file") MultipartFile file
			) {
		URI uri = service.uploadOcorrenciaPicture(file, id);
		return ResponseEntity.created(uri).build();
	}
}