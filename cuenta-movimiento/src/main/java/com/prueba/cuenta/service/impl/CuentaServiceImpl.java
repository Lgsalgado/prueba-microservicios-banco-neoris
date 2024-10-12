package com.prueba.cuenta.service.impl;

import com.prueba.cuenta.config.CuentaNoEncontradaException;
import com.prueba.cuenta.config.CuentaYaExistenteException;
import com.prueba.cuenta.config.SaldoInvalidoException;
import com.prueba.cuenta.entity.Cuenta;
import com.prueba.cuenta.repository.CuentaRepository;
import com.prueba.cuenta.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        Optional<Cuenta> cuentaExistente = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());
        if (cuentaExistente.isPresent()) {
            throw new CuentaYaExistenteException("El número de cuenta " + cuenta.getNumeroCuenta() + " ya existe.");
        }

        // Verificar si el saldo inicial es negativo
        if (cuenta.getSaldoInicial().compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInvalidoException("El saldo inicial no puede ser negativo.");
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta())
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));
        if (cuenta.getSaldoInicial().compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInvalidoException("El saldo no puede ser negativo.");
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void eliminarCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));

        cuenta.setEstado(false); // Cambiar el estado a false
        cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuentaAndEstadoTrue(numeroCuenta)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));
    }
}