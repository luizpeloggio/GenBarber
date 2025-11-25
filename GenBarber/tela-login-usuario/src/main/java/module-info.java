module com.meuapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Permite que o FXMLLoader acesse classes no pacote
    opens com.meuapp to javafx.fxml;

    // Permite exportar para outras partes do app
    exports com.meuapp;
}
