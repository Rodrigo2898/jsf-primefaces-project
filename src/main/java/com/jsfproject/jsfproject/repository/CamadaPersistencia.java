package com.jsfproject.jsfproject.repository;

import com.jsfproject.jsfproject.entity.Empresa;
import com.jsfproject.jsfproject.entity.RamoAtivitdade;
import com.jsfproject.jsfproject.entity.enums.TipoEmpresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

public class CamadaPersistencia {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JsfPrime");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Declarando os repositórios
        RamoAtividadeRepository ramoAtividadeRepository = new RamoAtividadeRepository(entityManager);
        EmpresaRepository empresaRepository = new EmpresaRepository(entityManager);

        // Buscando as informações do banco
        List<RamoAtivitdade> ramoAtivitdadeList = ramoAtividadeRepository.pesquisar("");
        List<Empresa> empresaList = empresaRepository.pesquisar("");
        System.out.println(empresaList);

        // Criando uma empresa
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("Empresa Test");
        empresa.setCnpj("95.309.380/0001-57");
        empresa.setRazaoSocial("Razão test");
        empresa.setTipoEmpresa(TipoEmpresa.MEI);
        empresa.setDataFundacao(new Date());

        // Verificando se a lista ramoAtivitdadeList não está vazia
        if (!ramoAtivitdadeList.isEmpty()) {
            empresa.setRamoAtivitdade(ramoAtivitdadeList.get(0));
        } else {
            System.out.println("A lista ramoAtivitdadeList está vazia. Definindo um RamoAtivitdade padrão.");
//            RamoAtivitdade ramoAtivitdadePadrao = new RamoAtivitdade();
//            ramoAtivitdadePadrao.setId(1L);
//            ramoAtivitdadePadrao.setDescricao("TESTE");
//            empresa.setRamoAtivitdade(ramoAtivitdadePadrao);
        }

        // Salvando empresa
        empresaRepository.guardar(empresa);

        entityManager.getTransaction().commit();

        // Verificando se a inserção funcionou
        List<Empresa> empresaList2 = empresaRepository.pesquisar("");
        System.out.println(empresaList2);

        entityManager.close();
        entityManagerFactory.close();
    }
}
