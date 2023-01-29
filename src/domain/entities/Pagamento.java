package domain.entities;

public class Pagamento {

    private SituacaoDebito situacaoDebito;
    private Double valorSolicitacao;
    private Double valorEfetivacao;

    public boolean isPagamentoTotal() {
        return situacaoDebito.equals(SituacaoDebito.TOTAL);
    }

    public Double getValorPerda() {
        return valorSolicitacao - valorEfetivacao;
    }
}
