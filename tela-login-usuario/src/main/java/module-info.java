module com.meuapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Permite que o FXMLLoader acesse controladores dentro de com.meuapp
    opens com.meuapp to javafx.fxml;

    // Permite que controladores dentro de com.meuapp.model sejam carregados via
    // FXML (se houver)
    opens com.meuapp.model to javafx.fxml;

    // Exporta os pacotes para que as classes possam ser usadas
    exports com.meuapp;
    exports com.meuapp.model;
}
