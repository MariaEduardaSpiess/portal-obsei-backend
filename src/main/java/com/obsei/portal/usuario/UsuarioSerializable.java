package com.obsei.portal.usuario;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UsuarioSerializable extends JsonSerializer<Usuario> {

    @Override
    public void serialize(Usuario usuario, JsonGenerator jsonGen, SerializerProvider serProv)
            throws IOException, JsonProcessingException {
        jsonGen.writeStartObject();
        jsonGen.writeNumberField("id", usuario.getId());
        jsonGen.writeStringField("username", usuario.getUsername());
        jsonGen.writeStringField("password", usuario.getUsername());
        jsonGen.writeEndObject();

    }
}
