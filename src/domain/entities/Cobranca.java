package domain.entities;

import java.util.Optional;

public class Cobranca {

    private boolean cobrancaParcial;
    private boolean cobrancaProvisionada;
    private String idFatura;
    private Optional<Pagamento> pagamento;
    private Optional<Rejeicao> rejeicao;

    public Optional<Cobranca> retornaCobrancaParcial() {
        Optional<Cobranca> cobranca = Optional.empty();

        if (rejeicao.get().isInsuficienciaFundos() && !cobrancaParcial) {
            cobranca = Optional.of(geraCobrancaParcial());
        }

        return cobranca;
    }

    public boolean gerouProvisao(Cliente cliente) {
               // se for cobrança total e foi provisionada
        return (!cobrancaParcial && cobrancaProvisionada) || // OU
               // se for cobrança parcial e a cobrança total dela foi provisionada
               (cobrancaParcial && getCobrancaOriginal(cliente).isCobrancaProvisionada());
    }

    public String getIdFatura() {
        return idFatura;
    }

    public boolean isCobrancaParcial() {
        return cobrancaParcial;
    }

    public boolean isCobrancaProvisionada() {
        return cobrancaProvisionada;
    }

    public boolean isPagamentoTotal() {
        return pagamento.get().isPagamentoTotal();
    }

    public Double getValorPerda() {
        return pagamento.get().getValorPerda();
    }

    private Cobranca geraCobrancaParcial() {
        return new Cobranca(); // gera cobrança parcial
    }

    private Cobranca getCobrancaOriginal(Cliente cliente) {
        return cliente.getCobrancas().stream()
                .filter(cobranca -> cobranca.getIdFatura().equals(idFatura) && !cobranca.isCobrancaParcial())
                .findFirst().get();
    }
}
