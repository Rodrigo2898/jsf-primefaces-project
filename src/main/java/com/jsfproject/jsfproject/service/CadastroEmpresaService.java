package com.jsfproject.jsfproject.service;

import com.jsfproject.jsfproject.entity.Empresa;
import com.jsfproject.jsfproject.repository.EmpresaRepository;
import com.jsfproject.jsfproject.util.Transactional;

import java.io.Serializable;

public class CadastroEmpresaService implements Serializable {
    private static long serialVersionUID = 1L;

    private final EmpresaRepository empresaRepository;

    public CadastroEmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    public void salvar(Empresa empresa) {
        empresaRepository.guardar(empresa);
    }

    @Transactional
    public void excluir(Empresa empresa) {
        empresaRepository.remover(empresa);
    }
}
