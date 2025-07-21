package com.projectgoch.clienttest.pojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({"actor", "movies"})
public record ActorsFilms(String actor, List<String> movies) {
}
