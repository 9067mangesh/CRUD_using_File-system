package com.crud.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud.demo.Entity.Models;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SystemService {

	private final String filePath = "models.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Models> getAllModels() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Models.class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read data from file", e);
        }
    }

    public Optional<Models> getModelById(int id) {
        return getAllModels().stream().filter(model -> model.getId()==id).findFirst();
    }

    public Models createModel(Models model) {
        List<Models> models = getAllModels();
        model.setId(models.size()+1);
        models.add(model);
        writemodelsToFile(models);
        return model;
    }
   
    public Models updateModel(int id, Models modelDetails) {
        List<Models> models = getAllModels();
        Models model = models.stream().filter(i -> i.getId()==id).findFirst().orElseThrow(() -> new RuntimeException("Data not found"));
        model.setName(modelDetails.getName());
        model.setDescription(modelDetails.getDescription());
        writemodelsToFile(models);
        return model;
    }

    public void deleteModel(int id) {
        List<Models> models = getAllModels();
        models.removeIf(model -> model.getId()==id);
        writemodelsToFile(models);
    }

    private void writemodelsToFile(List<Models> models) {
        try {
            objectMapper.writeValue(new File(filePath), models);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write Data to file", e);
        }
    }
}
