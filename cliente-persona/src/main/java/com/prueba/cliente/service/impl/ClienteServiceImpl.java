package com.prueba.cliente.service.impl;

import com.prueba.cliente.config.ClienteNoEncontradoException;
import com.prueba.cliente.config.ClienteYaExisteException;
import com.prueba.cliente.config.PersonaNoEncontradaException;
import com.prueba.cliente.entity.Cliente;
import com.prueba.cliente.entity.Persona;
import com.prueba.cliente.repository.ClienteRepository;
import com.prueba.cliente.repository.PersonaRepository;
import com.prueba.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public Cliente createCliente(Cliente cliente) {

        if (personaRepository.findByIdentificacion(cliente.getIdentificacion()).isPresent()) {
            throw new ClienteYaExisteException("El cliente con la identificación " + cliente.getIdentificacion() + " ya existe.");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new ClienteNoEncontradoException("Cliente no encontrado");
        }
        // Verificar si la persona existe
        Optional<Persona> persona = personaRepository.findById(cliente.getId());
        if (persona.isEmpty()) {
            throw new PersonaNoEncontradaException("Persona no encontrada");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(String identificacion) {
        Persona persona = personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
        Cliente cliente = clienteRepository.findById(persona.getId())
                .orElseThrow(() -> new ClienteNoEncontradoException("Persona no encontrada"));
        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente getClienteById(Integer clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new ClienteNoEncontradoException("Cliente no encontrado");
        }
        return cliente.get();
    }


    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}
