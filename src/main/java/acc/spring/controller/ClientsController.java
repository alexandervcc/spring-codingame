package acc.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acc.spring.DTO.ClientDto;
import acc.spring.exceptions.list.NotFoundException;
import acc.spring.model.Client;
import acc.spring.services.IClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/clientes")
@AllArgsConstructor
public class ClientsController {
	private IClientService clienteService;

	@GetMapping(path = "/all")
	public ResponseEntity<List<?>> getAllClients() {
		List<Client> listaClientes = clienteService.getAllClients();
		return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
	}

	@GetMapping(path = "/")
	public ResponseEntity<?> getClientById(@RequestParam Long clientId) throws NotFoundException {
		Client client = clienteService.getClientById(clientId);
		return ResponseEntity.status(HttpStatus.OK).body(client);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Client> createNewClient(@RequestBody ClientDto clientDto) throws Exception {
		Client newClient = clienteService.createNewClient(clientDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
	}

	@PutMapping("/")
	public ResponseEntity<Client> updateClient(@RequestParam Long clientId, @RequestBody ClientDto clientDto)
			throws NotFoundException {
		Client client = clienteService.updateClient(clientId, clientDto);
		return ResponseEntity.status(HttpStatus.OK).body(client);
	}

	@DeleteMapping("/")
	public ResponseEntity<String> deleteClient(@RequestParam Long clientId) throws NotFoundException {
		clienteService.deleteClientById(clientId);
		return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado.");
	}
}