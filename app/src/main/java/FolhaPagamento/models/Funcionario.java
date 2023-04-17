package FolhaPagamento.models;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.util.regex.Matcher;

public class Funcionario {
    private String nome;
    private String cpf;
    private String cargo;
    private BigDecimal salario;
    private LocalDate dataAdmissao;
    private int cargaHoraria;
    private boolean periculosidade;
    private Insalubridade insalubridade;

    public enum Insalubridade {
        BAIXO,
        MEDIO,
        ALTO
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        Pattern pattern = Pattern.compile("\\d{3}.\\d{3}.\\d{3}-\\d{2}");
        Matcher matcher = pattern.matcher(cpf);

        try {
            if (matcher.matches())
                this.cpf = cpf;
            else
                throw new Exception("O CPF inserido não está formatado ou não é válido.");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public boolean getPericulosidade() {
        return periculosidade;
    }

    public void setPericulosidade(boolean periculosidade) {
        this.periculosidade = periculosidade;
    }

    public Insalubridade getInsalubridade() {
        return insalubridade;
    }

    public void setInsalubridade(Insalubridade insalubridade) {
        this.insalubridade = insalubridade;
    }
}
