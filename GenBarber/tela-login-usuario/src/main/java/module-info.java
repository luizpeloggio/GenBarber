module com.meuapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    // Permite que o FXMLLoader acesse classes no pacote
    opens com.meuapp to javafx.fxml;

    // Permite exportar para outras partes do app
    exports com.meuapp;
    exports com.meuapp.model;
    exports com.meuapp.conexao;
    exports com.meuapp.dao;
}
