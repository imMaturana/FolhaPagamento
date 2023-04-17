package FolhaPagamento.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import FolhaPagamento.models.Funcionario.Insalubridade;

public class Calculadora {
    public BigDecimal salarioHora(BigDecimal salario, int cargaHoraria) {
        int jornadaSemanal = cargaHoraria * 5;
        int jornadaMensal = jornadaSemanal * 5;

        return salario.divide(new BigDecimal(Integer.toString(jornadaMensal)), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal periculosidade(BigDecimal salario) {
        return salario.multiply(new BigDecimal("0.3"));
    }

    public BigDecimal insalubridade(BigDecimal salario, Insalubridade grauRisco) {
        BigDecimal adicional;

        switch (grauRisco) {
            case ALTO:
                adicional = new BigDecimal("0.4");
                break;
            case MEDIO:
                adicional = new BigDecimal("0.2");
                break;
            case BAIXO:
            default:
                adicional = new BigDecimal("0.1");
                break;
        }

        return salario.multiply(adicional);
    }

    public BigDecimal valeTransporte(BigDecimal salario, BigDecimal valor) {
        final BigDecimal DESCONTO_MAXIMO = new BigDecimal("0.6");

        if (salario.multiply(DESCONTO_MAXIMO).compareTo(valor) == 1) {
            return salario.subtract(salario.multiply(DESCONTO_MAXIMO));
        }

        return salario.subtract(valor);
    }

    public BigDecimal valeAlimentacao(BigDecimal valorDiario, Month mes) {
        Year anoAtual = Year.now();

        LocalDate start = LocalDate.of(anoAtual.getValue(), mes, 1);
        LocalDate end = LocalDate.of(anoAtual.getValue(), mes, mes.length(anoAtual.isLeap()));

        long numSemanas = ChronoUnit.WEEKS.between(start, end);

        return valorDiario.multiply(new BigDecimal(numSemanas * 5));
    }

    public BigDecimal inss(BigDecimal salario) {
        BigDecimal desconto;

        if (salario.compareTo(new BigDecimal("1302.00")) <= 0) {
            desconto = new BigDecimal("0.075");
        } else if (salario.compareTo(new BigDecimal("2571.29")) <= 0) {
            desconto = new BigDecimal("0.09");
        } else if (salario.compareTo(new BigDecimal("3856.94")) <= 0) {
            desconto = new BigDecimal("0.12");
        } else {
            desconto = new BigDecimal("0.14");
        }

        return salario.multiply(desconto);
    }

    public BigDecimal fgts(BigDecimal salario) {
        BigDecimal desconto = new BigDecimal("0.08");

        return salario.multiply(desconto);
    }

    public BigDecimal irrf(BigDecimal salario) {
        BigDecimal salarioBase = salario.subtract(inss(salario));
        BigDecimal valorIRRF;

        if (salarioBase.compareTo(new BigDecimal("1903.98")) <= 0) {
            valorIRRF = new BigDecimal("0");
        } else if (salarioBase.compareTo(new BigDecimal("2826.65")) <= 0) {
            valorIRRF = salarioBase
                .multiply(new BigDecimal("0.075"))
                .subtract(new BigDecimal("142.80"));
        } else if (salarioBase.compareTo(new BigDecimal("3751.05")) <= 0) {
            valorIRRF = salarioBase
                .multiply(new BigDecimal("0.15"))
                .subtract(new BigDecimal("354.80"));
        } else if (salarioBase.compareTo(new BigDecimal("4664.68")) <= 0) {
            valorIRRF = salarioBase
                .multiply(new BigDecimal("0.225"))
                .subtract(new BigDecimal("636.13"));
        } else {
            valorIRRF = salarioBase
                .multiply(new BigDecimal("0.275"))
                .subtract(new BigDecimal("869.36"));
        }

        return valorIRRF;
    }
}
