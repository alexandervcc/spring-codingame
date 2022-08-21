package acc.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.ClientDto;
import acc.spring.exceptions.NotFoundException;
import acc.spring.model.Client;
import acc.spring.repository.ClientRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
  private ClientRepository clienteRepository;

  @Override
  public Client createNewClient(ClientDto clientdto) {
    Client newCliente = new Client();
    newCliente.setNombre(clientdto.nombre);
    newCliente.setGenero(clientdto.genero);
    newCliente.setEdad(clientdto.edad);
    newCliente.setDireccion(clientdto.direccion);
    newCliente.setIdentificacion(clientdto.identificacion);
    newCliente.setDireccion(clientdto.direccion);
    newCliente.setTelefono(clientdto.telefono);

    newCliente.setContrasena(clientdto.contrasena);
    newCliente.setEstado(clientdto.estado);
    return clienteRepository.save(newCliente);
  }

  @Override
  public void deleteClientById(Long id) throws NotFoundException {
    Client clientToDelete = clienteRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    clienteRepository.delete(clientToDelete);
  }

  @Override
  public List<Client> getAllClients() {
    List<Client> listaClientes = clienteRepository.findAll();
    return listaClientes;
  }

  @Override
  public Client getClientById(Long id) throws NotFoundException {
    Client cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    return cliente;
  }

  @Override
  public Client updateClient(Long id, ClientDto clienteDto) throws NotFoundException {
    Client cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    if (clienteDto.contrasena != null)
      cliente.setContrasena(clienteDto.contrasena);
    if (clienteDto.estado != null)
      cliente.setEstado(clienteDto.estado);
    return clienteRepository.save(cliente);
  }

}
