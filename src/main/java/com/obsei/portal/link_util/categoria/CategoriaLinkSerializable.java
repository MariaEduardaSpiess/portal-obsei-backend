package com.obsei.portal.link_util.categoria;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CategoriaLinkSerializable extends JsonSerializer<CategoriaLink> {

    @Override
    public void serialize(CategoriaLink categoriaLink, JsonGenerator jsonGen, SerializerProvider serProv)
            throws IOException, JsonProcessingException {
        jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", categoriaLink.getId());
        jsonGen.writeStringField("nome", categoriaLink.getNome());
        jsonGen.writeEndObject();

    }
}
