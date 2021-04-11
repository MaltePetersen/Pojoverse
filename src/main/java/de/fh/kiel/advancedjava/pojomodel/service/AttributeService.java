package de.fh.kiel.advancedjava.pojomodel.service;

import de.fh.kiel.advancedjava.pojomodel.dto.AddAttributeDTO;
import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import de.fh.kiel.advancedjava.pojomodel.model.Primitive;
import de.fh.kiel.advancedjava.pojomodel.model.PrimitiveDataType;
import de.fh.kiel.advancedjava.pojomodel.model.Reference;
import de.fh.kiel.advancedjava.pojomodel.repository.PojoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttributeService {

    private final PojoRepository pojoRepository;

    AttributeService(PojoRepository pojoRepository){
        this.pojoRepository =pojoRepository;
    }

    public Primitive addPrimitive(String pojoId ,AddAttributeDTO addAttributeDTO) throws Exception {
        Optional<Pojo> pojo = pojoRepository.findById(pojoId);
        if(pojo.isPresent()){
            Primitive primitive = new Primitive(addAttributeDTO.getName(), new PrimitiveDataType(addAttributeDTO.getType()), addAttributeDTO.getVisibility());
            pojo.get().getAttributes().add(primitive);
            pojoRepository.save(pojo.get());
            return primitive;
        }
        throw new Exception("The Pojo you want to access does not exist");
    }

    public Reference addReference(String pojoId , AddAttributeDTO addAttributeDTO) throws Exception {
        Optional<Pojo> pojo = pojoRepository.findById(pojoId);
        if(pojo.isPresent()){
            Optional<Pojo> exisitingPojoOfAttribute =  pojoRepository.findById(addAttributeDTO.getType());
            Reference reference;
            if(exisitingPojoOfAttribute.isPresent()){
                reference = new Reference(addAttributeDTO.getName(), addAttributeDTO.getType(), addAttributeDTO.getVisibility(),pojo.get());
            }else{
              reference =  new Reference(addAttributeDTO.getName(), addAttributeDTO.getType(), addAttributeDTO.getVisibility(),new Pojo(addAttributeDTO.getType(),null));
            }
            pojo.get().getAttributes().add(reference);
            pojoRepository.save(pojo.get());
            return reference;
        }
        throw new Exception("The Pojo you want to access does not exist");
    }


}
