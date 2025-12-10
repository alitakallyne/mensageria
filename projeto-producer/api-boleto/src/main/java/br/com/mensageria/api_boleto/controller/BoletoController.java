package br.com.mensageria.api_boleto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mensageria.api_boleto.dto.BoletoDTO;
import br.com.mensageria.api_boleto.service.BoletoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/boleto")
public class BoletoController {

	 private final BoletoService boletoService;

	    public BoletoController(BoletoService boletoService) {
	        this.boletoService = boletoService;
	    }

		@GetMapping("/{codigoBarras}")
		public ResponseEntity<BoletoDTO> buscarBoletoPorCodigoBarras(@PathVariable("codigoBarras") String codigoBarras) {
	        var boletoDTO = boletoService.buscarBoletoPorCodigoBarras(codigoBarras);
	        return ResponseEntity.ok(boletoDTO);
	    }
	    
	    @PostMapping
	    public ResponseEntity<BoletoDTO> salvar(@Valid @RequestBody BoletoDTO boletoRequestDTO) {
	        var boleto = boletoService.salvar(boletoRequestDTO.getCodigoBarras());
	        return new ResponseEntity<>(boleto, HttpStatus.CREATED);
	    }
}
