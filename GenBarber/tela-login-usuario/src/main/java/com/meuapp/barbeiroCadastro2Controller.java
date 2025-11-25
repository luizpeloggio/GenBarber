package com.meuapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent; // Importação essencial para onAction em botões

// O controlador deve implementar Initializable
// NOTA: Se este é o controlador da TELA DE ENDEREÇO, o nome está confuso (Passo 2). 
// Manteremos, mas é recomendável renomear no futuro.
public class barbeiroCadastro2Controller implements Initializable {

    @FXML
    private ComboBox<String> estadoComboBox;

    @FXML
    private ComboBox<String> cidadeComboBox;

    /**
     * Este método é chamado após o carregamento do FXML para inicializar os
     * elementos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1. Criar a lista de Estados (exemplo simplificado)
        ObservableList<String> estados = FXCollections.observableArrayList(
                "Acre",
                "Alagoas",
                "Amapá",
                "Amazonas",
                "Bahia",
                "Ceará",
                "Espírito Santo",
                "Goiás",
                "Maranhão",
                "Mato Grosso",
                "Mato Grosso do Sul",
                "Minas Gerais",
                "Pará",
                "Paraíba",
                "Paraná",
                "Pernambuco",
                "Piauí",
                "Rio de Janeiro",
                "Rio Grande do Norte",
                "Rio Grande do Sul",
                "Rondônia",
                "Roraima",
                "Santa Catarina",
                "São Paulo",
                "Sergipe",
                "Tocantins",
                "Distrito Federal");

        // 2. Adicionar os itens ao ComboBox
        estadoComboBox.setItems(estados);

        // Opcional: Adicionar um listener para carregar as cidades quando o estado for
        // selecionado
        estadoComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Aqui você chamaria um método que carrega as cidades
                // com base no 'newValue' (Estado selecionado).
                cidadeComboBox.setDisable(false);

                // Exemplo de atualização (apenas para o RN):
                if (newValue.startsWith("Rio Grande do Norte")) {
                    ObservableList<String> cidades = FXCollections.observableArrayList(
                            "Caicó", "Currais Novos", "Mossoró", "Natal", "Parnamirim", "Santa Cruz");
                    cidadeComboBox.setItems(cidades);
                } else {
                    // Limpar e desabilitar para outros estados não implementados
                    cidadeComboBox.getItems().clear();
                    cidadeComboBox.setDisable(true);
                    cidadeComboBox.setPromptText("Selecione um estado válido");
                }
            }
        });
    }

    /**
     * RENOMEADO: Método para prosseguir para a próxima etapa do cadastro (ex:
     * Serviços).
     * 
     * @param event Recebe o evento de ação do botão.
     */
    @FXML
    private void cadastroBarbeiro3(ActionEvent event) throws IOException {
        String estadoSelecionado = estadoComboBox.getSelectionModel().getSelectedItem();
        String cidadeSelecionada = cidadeComboBox.getSelectionModel().getSelectedItem();

        System.out.println("Navegando: Estado selecionado: " + estadoSelecionado + ", Cidade: " + cidadeSelecionada);

        // NAVEGAÇÃO: Chamada para o próximo FXML (Passo 3: Serviços/Horários)
        App.setRoot("barbeiro-cadastro3"); // SUBSTITUA PELO NOME DO SEU PRÓXIMO FXML
    }

    @FXML
    private void cadastroBarbeiro(ActionEvent event) throws IOException {
        App.setRoot("barbeiro-cadastro");
    }
}