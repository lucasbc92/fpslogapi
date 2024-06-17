package br.com.magazineluiza.fpslogapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Kill {
    private String killer;
    private String victim;
}