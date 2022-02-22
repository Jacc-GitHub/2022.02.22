package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.controller.Catalogo;

import br.com.letscode.cookbook.domain.Ingrediente;
import br.com.letscode.cookbook.domain.Receita;
import br.com.letscode.cookbook.domain.Rendimento;

import br.com.letscode.cookbook.enums.Categoria;
import br.com.letscode.cookbook.enums.ModoPreparo;
import br.com.letscode.cookbook.enums.TipoMedida;
import br.com.letscode.cookbook.enums.TipoRendimento;

import br.com.letscode.cookbook.view.CatalogoView;

import java.util.Locale;

public class EditReceitaView {
    private Receita receita;
    private Receita ative;
    private int currentIndex;

    public EditReceitaView(Receita receita, String menuAtivo) {
        if(menuAtivo.equals("Edit")) {
            this.receita = new Receita(receita);
        } else if (menuAtivo.equals("Add")) {
                this.receita = new Receita(receita.getNome(), receita.getCategoria());
        }
    }

    private boolean showMenuReceita() {
        String[] options = new String[9];
        StringBuilder sb = new StringBuilder("#".repeat(100));

        sb.append("%n").append("Informe a alteração que deseja realizar");
        sb.append("%n").append("#".repeat(100));
        sb.append("%n").append("  N : Alterar Nome");
        options[0] = "N";
        sb.append("%n").append("  C : Alterar Categoria");
        options[1] = "C";
        sb.append("%n").append("  T : Alterar Tempo de preparo");
        options[2] = "T";
        sb.append("%n").append("  R : Alterar Rendimento");
        options[3] = "R";
        sb.append("%n").append("  +I : Incluir Ingrediente");
        options[4] = "+I";

        if (receita.getIngredientes().size() != 0) {
            sb.append("%n").append("  -I : Remover Ingrediente");
            options[5] = "-I";
        }

        sb.append("%n").append("  +P : Incluir Preparo");
        options[6] = "+P";

        if (receita.getPreparo().size() != 0) {
            sb.append("%n").append("  -P : Remover Preparo");
            options[7] = "-P";
        }
        sb.append("%n").append("# ".repeat(15)).append("%n");
        sb.append("%n").append("  X : Sair");
        options[8] = "X";
        sb.append("%n").append("#".repeat(100)).append("%n");


        String opcao = ConsoleUtils.getUserOption(sb.toString(), options).toUpperCase(Locale.getDefault());
        switch (opcao) {
            case "N":
                editNome(); //"  N : Alterar Nome  %n"
                break;
            case "C":
                editCategoria(); //"  C : Alterar Categoria  %n"
                break;
            case "T":
                editTempoPreparo(); //"  T : Alterar Tempo de preparo  %n"
                break;
            case "R":
                editRendimento(); //"  R : Alterar Rendimento  %n"
                break;
            case "+I":
                addIngrediente(); //"  +I : Incluir Ingrediente  %n"
                break;
            case "-I":
                delIngrediente(); //"  -I : Remover Ingrediente  %n"
                break;
            case "+P":
                addPreparo(); //"  +P : Incluir Preparo  %n"
                break;
            case "-P":
                delPreparo(); //"  -P : Remover Preparo  %n"
                break;
            case "X":
                System.out.println("Ok, saindo sem salvar!");
                return false;
            default:
                System.out.println("Opção inválida!!!");
        }
        return true;
    }

    private boolean showMenuPreparos() {
        String[] options = new String[3];
        StringBuilder sb = new StringBuilder("#".repeat(100));

        sb.append("%n").append("  + : Adicionar Preparo %n");
        options[0] = "+";

        if (receita.getPreparo().size() != 0) {
            sb.append("  - : Remover Preparo %n");
            options[1] = "-";
        }

        sb.append("%n").append("# ".repeat(15)).append("%n");
        sb.append("%n").append("  X : Sair do Preparo %n");
        options[2] = "X";
        sb.append("%n").append("#".repeat(100)).append("%n");

        String opcao = ConsoleUtils.getUserOption(sb.toString(), options).toUpperCase(Locale.getDefault());
        switch (opcao) {
            case "+":
                addPreparo();
                break;
            case "-":
                delPreparo();
                break;
            case "X":
                System.out.println("Ok, saindo do Preparo!");
                return false;
            default:
                System.out.println("Opção Inválida!!!");
        }
        return true;
    }

    private void preparo() {
        do {
            new ReceitaView(receita).fullView(System.out);
        } while (showMenuPreparos());
    }

    private void ingrediente() {
        do {
            new ReceitaView(receita).fullView(System.out);
        } while (showMenuReceita());
    }


    private void editNome() {
        String name = ConsoleUtils.getUserInput("Qual o novo nome da receita?");
        if ((name != null) && (!name.isBlank())) {
            receita.setNome(name);
        }
    }

    private void editTempoPreparo() {
        do {
            String time = ConsoleUtils.getUserInput("Qual o novo tempo de preparo da receita?");
            if (!time.isBlank()) {
                try {
                    double value = Double.parseDouble(time);
                    receita.setTempoPreparo(value);
                    return;
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println("Tempo de preparo invalido.");
                }
            } else {
                break;
            }
        } while (true);
    }

    private void editCategoria() {
        StringBuilder sb = new StringBuilder("Qual a nova categoria da receita?\n");
        String[] options = new String[Categoria.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, Categoria.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        Categoria categoria = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                categoria = Categoria.values()[i];
                break;
            }
        }
        receita.setCategoria(categoria);
    }

    public void editRendimento() {
        int valueMin;
        int valueMax;
        do {
            String min = ConsoleUtils.getUserInput("Qual o rendimento minimo da receita?");
            try {
                if (min != null) {
                    valueMin = Integer.parseInt(min);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Rendimento minimo invalido.");
            }
        } while (true);
        do {
            String max = ConsoleUtils.getUserInput("Qual o rendimento maximo da receita?");
            try {
                if (max != null) {
                    valueMax = Integer.parseInt(max);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Rendimento maximo invalido.");
            }
        } while (true);

        TipoRendimento tipoRendimento = editTipoRendimento();
        if (valueMin == valueMax) {
            receita.setRendimento(new Rendimento(valueMin, tipoRendimento));
        } else {
            receita.setRendimento(new Rendimento(valueMin, valueMax, tipoRendimento));
        }
    }

    private TipoRendimento editTipoRendimento() {
        StringBuilder sb = new StringBuilder("Qual o tipo de rendimento da receita?\n");
        String[] options = new String[TipoRendimento.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoRendimento.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        TipoRendimento tipoRendimento = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                tipoRendimento = TipoRendimento.values()[i];
                break;
            }
        }
        return tipoRendimento;
    }

    private void addIngrediente() {

        String nome = ConsoleUtils.getUserInput("Qual o nome do Ingrediente?");
        Double quantidade = ConsoleUtils.getUserDouble("Qual a quantidade?");

        StringBuilder sb = new StringBuilder("Qual o tipo de medida do Ingrediente?\n");
        String[] options = new String[TipoMedida.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoMedida.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        TipoMedida tipoMedida = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                tipoMedida = TipoMedida.values()[i];
                break;
            }
        }

        Ingrediente ingrediente = new Ingrediente(nome, quantidade, tipoMedida);
        receita.setIngrediente(ingrediente);

    }

    private void delIngrediente() {
        StringBuilder sb = new StringBuilder("Qual ingrediente para excluir?\n");
        String[] options = new String[receita.getIngredientes().size()];
        
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, receita.getIngredientes().get(i).getNome()));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                receita.delIngrediente(i);
                break;
            }
        }
    }

    private void addPreparo() {

        String preparo = ConsoleUtils.getUserInput("Digite o preparo");

        if (receita.getPreparo().size() == 0) {
            receita.setPreparo(preparo);
        } else {
            StringBuilder sb = new StringBuilder("Em qual posieao deseja incluir o preparo?\n");
            String[] options = new String[receita.getPreparo().size() + 1];
            for (int i = 0; i < options.length-1; i++) {
                options[i] = String.valueOf(i);
                sb.append(String.format("%d - %s%n", i, receita.getPreparo().get(i)));
            }
            sb.append(String.format("%d - %s%n",receita.getPreparo().size(),""));
            options[receita.getPreparo().size()] = String.valueOf(receita.getPreparo().size());
            String opcao = ConsoleUtils.getUserOption(sb.toString(), options);

            receita.setPreparo(preparo, Integer.parseInt(opcao));
        }
    }

    private void delPreparo() {
        StringBuilder sb = new StringBuilder("Qual preparo voce quer excluir?\n");
        String[] options = new String[receita.getPreparo().size()];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, receita.getPreparo().get(i)));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                receita.delPreparo(i);
                break;
            }
        }
    }

    private void rendimento() {
        StringBuilder sb = new StringBuilder("Qual o tipo de Rendimento?\n");
        String[] options = new String[TipoRendimento.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoRendimento.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        TipoRendimento tipoRendimento = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                tipoRendimento = TipoRendimento.values()[i];
                break;
            }
        }
        int rendimentoMin = ConsoleUtils.getUserInt("Qual o rendimento mínimo da Receita?");
        int rendimentoMax = ConsoleUtils.getUserInt("Qual o rendimento máximo da Receita?");
        Rendimento rendimento = new Rendimento(rendimentoMin, rendimentoMax, tipoRendimento);
        receita.setRendimento(rendimento);
    }

    private void tempoPreparo() {
        double tempo = ConsoleUtils.getUserDouble("Qual o tempo de preparo, em minutos?");
        receita.setTempoPreparo(tempo);
    }

    public Receita edit() {
        do {
            new ReceitaView(receita).fullView(System.out);
        } while (showMenuReceita());

        String opcao = ConsoleUtils.getUserOption("Você deseja salvar a receita " + receita.getNome() + "?\nS - Sim   N - Não", "S", "N");
        if ("S".equalsIgnoreCase(opcao)) {
            return receita;
        } else {
            return null;
        }
    }
}
