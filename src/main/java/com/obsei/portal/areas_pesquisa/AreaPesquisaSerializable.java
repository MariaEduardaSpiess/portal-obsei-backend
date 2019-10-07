package com.obsei.portal.areas_pesquisa;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AreaPesquisaSerializable extends JsonSerializer<AreaPesquisa> {

    @Override
    public void serialize(AreaPesquisa areaPesquisa, JsonGenerator jsonGen, SerializerProvider serProv)
            throws IOException, JsonProcessingException {
        jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", areaPesquisa.getId());
        jsonGen.writeStringField("nome", areaPesquisa.getNome());
        jsonGen.writeEndObject();

    }
}
