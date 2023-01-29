package domain.entities;

public class Rejeicao {

    private MotivoRejeicao motivoRejeicao;

    public boolean isInsuficienciaFundos() {
        return motivoRejeicao.equals(MotivoRejeicao.INSUFICIENCIA_FUNDOS);
    }
}
