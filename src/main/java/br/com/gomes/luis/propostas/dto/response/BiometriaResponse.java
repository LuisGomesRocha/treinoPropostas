package br.com.gomes.luis.propostas.dto.response;

public class BiometriaResponse {

    private String id;
    private String biometria;

    public BiometriaResponse(String id, String biometria){
        this.id = id;
        this.biometria = biometria;
    }

    public String getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }
}
